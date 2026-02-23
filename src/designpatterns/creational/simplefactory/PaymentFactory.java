package designpatterns.creational.simplefactory;

/**
 * Simple Factory
 * - Concrete Product 클래스들은 일반 클래스
 * - 결정 로직만 static 메서드로 공개
 * - 인스턴스 없이 타입만 넘겨서 객체 생성
 */
public class PaymentFactory {

    public static Payment createPayment(String type) {
        return switch (type.toUpperCase()) {
            case "CREDIT" -> new CreditCardPayment();
            case "KAKAO"  -> new KakaoPayPayment();
            case "PAYCO"  -> new PaycoPayment();
            default       -> throw new IllegalArgumentException("지원하지 않는 결제 방식: " + type);
        };
    }

    public static void main(String[] args) {
        Payment payment = PaymentFactory.createPayment("kakao");
        payment.process(10000);

        payment = PaymentFactory.createPayment("credit");
        payment.process(50000);
    }

}
