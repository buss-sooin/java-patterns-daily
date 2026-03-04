package designpatterns.structural.proxy.lazy;

import java.util.concurrent.atomic.AtomicReference;

/**
 * GrpcChannelProxy - gRPC 채널 지연 로딩 프록시
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하여, 생성 비용을 필요할 때로 미룬다.
 * gRPC 채널 초기화 비용을 첫 RPC 호출 시점으로 지연시켜 앱 시작 시간을 단축한다.
 *
 * 특징:
 * - AtomicReference로 thread-safe 초기화 체크
 * - 채널 생성은 첫 getChannel() 시에만
 * - 스프링에서는 앱 시작 시점(@PostConstruct) 초기화
 * - 첫 사용자 응답 지연을 피하기 위해 시작 시 미리 로드
 * - 초기화 후 재사용으로 비용 1회만 발생
 * - @PreDestroy에 shutdown() 호출
 */
public class GrpcChannelProxy {
    private final String host;
    private final int port;
    private final AtomicReference<MockManagedChannel> channelRef = new AtomicReference<>(null);

    public GrpcChannelProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public MockManagedChannel getChannel() {
        return channelRef.updateAndGet(channel -> {
            if (channel == null) {
                System.out.println("gRPC 채널 지연 초기화: " + host + ":" + port);
                return new MockManagedChannel(host, port);
            }
            return channel;
        });
    }

    public void shutdown() {
        MockManagedChannel channel = channelRef.get();
        if (channel != null) {
            channel.shutdown();
        }
    }
}