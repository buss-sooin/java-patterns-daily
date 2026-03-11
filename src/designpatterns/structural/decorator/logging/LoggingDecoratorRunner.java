package designpatterns.structural.decorator.logging;

public class LoggingDecoratorRunner {

    public static void main(String[] args) {
        LoggingService service = new LoggingDecorator(new RealLoggingService());

        System.out.println("=== 정상 호출 ===");
        service.performOperation("test data");

        System.out.println("\n=== 예외 발생 테스트 ===");
        try {
            service.performOperation(null);
        } catch (Exception e) {
            System.out.println("예외 처리됨");
        }
    }

}