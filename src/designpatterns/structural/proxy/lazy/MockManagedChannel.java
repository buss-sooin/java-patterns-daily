package designpatterns.structural.proxy.lazy;

/**
 * MockManagedChannel - gRPC ManagedChannel 모의 객체
 *
 * 실제 gRPC 라이브러리 없이 테스트용으로 사용.
 * ManagedChannel의 최소 인터페이스(생성, shutdown)만 흉내냄.
 * 실무에서는 io.grpc.ManagedChannel을 사용하며,
 * ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()로 생성.
 */
public class MockManagedChannel {
    private final String host;
    private final int port;

    public MockManagedChannel(String host, int port) {
        this.host = host;
        this.port = port;
        System.out.println("Mock 채널 생성됨: " + host + ":" + port);
    }

    public void shutdown() {
        System.out.println("Mock 채널 shutdown: " + host + ":" + port);
    }
}