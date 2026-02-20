package designpatterns.creational.factorymethod;

public class CreditCardFactory implements FactoryMethod {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
