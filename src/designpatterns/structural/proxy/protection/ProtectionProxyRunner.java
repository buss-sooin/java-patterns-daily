package designpatterns.structural.proxy.protection;

public class ProtectionProxyRunner {

    public static void main(String[] args) {
        ProtectedService service = new ProtectionProxy(new RealProtectedService(), new UserContext("ADMIN"));
        service.sensitiveOperation("안전한 데이터");

        ProtectedService noAuth = new ProtectionProxy(new RealProtectedService(), new UserContext("USER"));
        try {
            noAuth.sensitiveOperation("데이터");
        } catch (SecurityException e) {
            System.out.println("접근 거부: " + e.getMessage());
        }
    }

}
