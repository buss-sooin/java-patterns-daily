package designpatterns.creational.factorymethod;

// Concrete Product
public class KakaoPayPayment implements Payment {
    @Override
    public void process(int amount) {
        System.out.println("카카오페이 결제: " + amount + "원");
    }
}
