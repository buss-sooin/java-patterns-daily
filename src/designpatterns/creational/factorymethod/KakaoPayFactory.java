package designpatterns.creational.factorymethod;

public class KakaoPayFactory implements FactoryMethod {
    @Override
    public Payment createPayment() {
        return new KakaoPayPayment();
    }
}
