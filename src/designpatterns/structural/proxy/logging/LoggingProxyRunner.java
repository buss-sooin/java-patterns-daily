package designpatterns.structural.proxy.logging;

public class LoggingProxyRunner {

    public static void main(String[] args) {
        RealLoggingService real = new RealLoggingService();
        LoggingProxy proxy = new LoggingProxy(real);

        proxy.performOperation("test data"); // 로깅 출력

        try {
            proxy.performOperation(null); // 예외 로깅 테스트
        } catch (Exception e) {
            System.out.println("예외 처리됨");
        }
    }

}