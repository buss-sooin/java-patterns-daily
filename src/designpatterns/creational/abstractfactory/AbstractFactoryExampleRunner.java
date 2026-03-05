package designpatterns.creational.abstractfactory;

public class AbstractFactoryExampleRunner {

    public static void main(String[] args) {
        DeliveryApiFactory factory = DeliveryApiFactory.getFactory("COMPANY_A");
        DeliveryApi api = factory.createDeliveryApi();
        api.processFullFlow("주문 데이터", "추적번호");

        factory = DeliveryApiFactory.getFactory("COMPANY_B");
        api = factory.createDeliveryApi();
        api.processFullFlow("주문 데이터", "추적번호");
    }

}