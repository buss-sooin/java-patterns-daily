package designpatterns.creational.simplefactory;

import designpatterns.creational.PaymentType;

public class SimpleFactoryExampleRunner {
    public static void main(String[] args) {
        PaymentFactoryProvider provider = new PaymentFactoryProvider();

        Payment payment = provider.get(PaymentType.KAKAO);
        payment.process(10000);

        payment = provider.get(PaymentType.CREDIT);
        payment.process(50000);

        // 기본값 테스트
        payment = provider.get(PaymentType.PAYCO); // PAYCO 없으면 DEFAULT
        payment.process(30000);
    }
}