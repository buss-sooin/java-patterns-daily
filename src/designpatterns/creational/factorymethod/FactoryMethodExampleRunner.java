package designpatterns.creational.factorymethod;

public class FactoryMethodExampleRunner {
    public static void main(String[] args) {
        FactoryMethod factory;

        // 카카오페이 결제 방식 선택
        factory = new KakaoPayFactory();
        factory.process(10000);  // 출력: 카카오페이 결제: 10000원

        // 신용카드 결제 방식으로 변경 (팩토리만 교체)
        factory = new CreditCardFactory();
        factory.process(50000);  // 출력: 신용카드 결제: 50000원
    }
}