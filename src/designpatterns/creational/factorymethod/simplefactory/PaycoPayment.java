package designpatterns.creational.factorymethod.simplefactory;

public class PaycoPayment implements Payment {
    @Override
    public void process(int amount) {
        System.out.println("페이코 결제: " + amount + "원");
    }
}
