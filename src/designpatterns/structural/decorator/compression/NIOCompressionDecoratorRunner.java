package designpatterns.structural.decorator.compression;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOCompressionDecoratorRunner {

    public static void main(String[] args) throws Exception {
        Path outputPath = Paths.get("compressed_logs.gz");

        // 실제 전송 계층 모킹
        LogCollector real = new RealLogCollector();
        NIOCompressionDecorator decorator = new NIOCompressionDecorator(real, outputPath);

        // 대량 로그 시뮬레이션
        for (int i = 0; i < 2000; i++) {
            decorator.collect(new LogEvent("로그 메시지 " + i));
        }

        // 종료 시 남은 버퍼 강제 처리
        decorator.flushOnShutdown();

        System.out.println("파일 생성 완료: " + outputPath.toAbsolutePath());
    }

}