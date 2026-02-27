package designpatterns.creational.factorymethod;

public class FactoryMethodExampleRunner {
    public static void main(String[] args) {
        PaymentFactoryProvider provider = new PaymentFactoryProvider();

        FactoryMethod factory = provider.get(PaymentType.KAKAO);
        factory.process(10000);

        factory = provider.get(PaymentType.CREDIT);
        factory.process(50000);

        // 구현체가 없는 타입 → 기본값 (CreditCard)
        factory = provider.get(PaymentType.PAYCO);
        factory.process(30000);
    }
}