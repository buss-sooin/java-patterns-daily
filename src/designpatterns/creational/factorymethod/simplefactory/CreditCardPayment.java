package designpatterns.creational.factorymethod.simplefactory;

public class CreditCardPayment implements Payment {
    @Override
    public void process(int amount) {
        System.out.println("신용카드 결제: " + amount + "원");
    }
}
