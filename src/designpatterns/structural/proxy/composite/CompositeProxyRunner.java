package designpatterns.structural.proxy.composite;

public class CompositeProxyRunner {

    public static void main(String[] args) {
        MonitoringService real = new RealMonitoringService();
        MonitoringService logging = new LoggingProxy(real, new MessageQueueSender());
        MonitoringService composite = new ExceptionHandlingProxy(logging, new FileLogger());

        composite.collectData("test"); // 정상: 큐 로깅
        try {
            composite.collectData(null); // 예외: 파일 로깅 + 큐 로깅
        } catch (Exception e) {
            System.out.println("처리됨");
        }
    }

}
