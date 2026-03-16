package designpatterns.structural.decorator.protection;

public class ProtectionDecoratorRunner {

    public static void main(String[] args) {
        ProtectedService service = new ProtectionDecorator(
                new RealProtectedService(),
                new UserContext("PAYMENT_MANAGER", 1001L)
        );

        System.out.println(service.processSensitiveData("결제 데이터 50000원"));

        ProtectedService noAuth = new ProtectionDecorator(
                new RealProtectedService(),
                new UserContext("USER", 1001L)
        );

        try {
            noAuth.processSensitiveData("결제 데이터");
        } catch (SecurityException e) {
            System.out.println("접근 거부: " + e.getMessage());
        }
    }

}
