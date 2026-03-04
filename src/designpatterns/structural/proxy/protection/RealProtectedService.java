package designpatterns.structural.proxy.protection;

public class RealProtectedService implements ProtectedService {

    @Override
    public void sensitiveOperation(String data) {
        System.out.println("민감 작업 수행: " + data);
    }

}
