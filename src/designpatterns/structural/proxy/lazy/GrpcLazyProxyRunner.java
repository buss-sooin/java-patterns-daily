package designpatterns.structural.proxy.lazy;

public class GrpcLazyProxyRunner {
    public static void main(String[] args) {
        GrpcChannelProxy proxy = new GrpcChannelProxy("localhost", 9090);

        System.out.println("프록시 생성 완료 (채널 아직 초기화 안 됨)");

        MockManagedChannel channel = proxy.getChannel();
        System.out.println("채널 획득 완료");

        MockManagedChannel same = proxy.getChannel();
        System.out.println("같은 채널 재사용");

        proxy.shutdown();
    }
}