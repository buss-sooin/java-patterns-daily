package designpatterns.creational.factorymethod;

public class FactoryMethodExampleRunner {
    public static void main(String[] args) {
        FactoryMethod factory;

        factory = new KakaoPayFactory();
        factory.process(10000);

        factory = new CreditCardFactory();
        factory.process(50000);
    }
}