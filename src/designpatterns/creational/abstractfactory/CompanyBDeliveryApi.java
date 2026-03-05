package designpatterns.creational.abstractfactory;

public class CompanyBDeliveryApi implements DeliveryApi {

    @Override
    public void sendOrder(String orderData) {
        System.out.println("Company B: 주문 전송 - " + orderData);
    }

    @Override
    public void startDelivery(String trackingNumber) {
        System.out.println("Company B: 배송 시작 - " + trackingNumber);
    }

    @Override
    public void updateDelivery(String status) {
        System.out.println("Company B: 배송 업데이트 - " + status);
    }

    @Override
    public void completeDelivery(String completionData) {
        System.out.println("Company B: 배송 완료 - " + completionData);
    }

}