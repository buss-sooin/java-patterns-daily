package designpatterns.structural.decorator.compression;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.GZIPOutputStream;

/**
 * NIOCompressionDecorator - NIO 기반 압축 + 전송 데코레이터
 *
 * 의도:
 * 데이터 수집 후 메모리 버퍼에 모은 뒤 NIO 채널로 압축하여 파일/소켓으로 전송한다.
 * 모니터링 SaaS에서 대량 로그·메트릭 적재 시 IO 효율 극대화.
 *
 * 특징:
 * - NIO FileChannel + ByteBuffer 사용 (비동기)
 * - GZIP 압축 후 전송 (많이 사용하는 형식)
 * - 버퍼 크기 초과 시 자동 flush & 압축
 * - 스프링 실무 적용: @Component + @PreDestroy로 채널 정리
 * - NIO 장점: IO 대비 스레드 효율 ↑, 대용량 처리 시 메모리 복사 최소화
 * - 단점: 순수 NIO는 코드 복잡 → 실무에선 Netty로 대체하는 경우가 대부분
 */
public class NIOCompressionDecorator implements LogCollector {
    private final LogCollector next;
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private static final int BATCH_SIZE = 1_048_576; // 1MB
    private final Path outputPath;

    public NIOCompressionDecorator(LogCollector next, Path outputPath) {
        this.next = next;
        this.outputPath = outputPath;
    }

    @Override
    public void collect(LogEvent event) {
        try {
            byte[] bytes = event.message().getBytes();
            buffer.write(bytes);

            if (buffer.size() >= BATCH_SIZE) {
                flushAndCompress();
            }
        } catch (Exception e) {
            System.err.println("버퍼링 실패: " + e.getMessage());
        }
    }

    private void flushAndCompress() throws Exception {
        byte[] data = buffer.toByteArray();
        buffer.reset();

        // GZIP 압축
        byte[] compressed = gzipCompress(data);

        // NIO로 파일에 쓰기 (실무에선 SocketChannel로 전송 가능)
        try (FileChannel channel = FileChannel.open(outputPath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.wrap(compressed);
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        }

        // 다음 데코레이터로 전달 (e.g. 큐 전송)
        next.collect(new LogEvent(new String(compressed)));
    }

    private byte[] gzipCompress(byte[] data) throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
            gzip.write(data);
            gzip.finish();
            return baos.toByteArray();
        }
    }

    // 앱 종료 시 남은 버퍼 처리
    public void flushOnShutdown() throws Exception {
        if (buffer.size() > 0) {
            flushAndCompress();
        }
    }
}