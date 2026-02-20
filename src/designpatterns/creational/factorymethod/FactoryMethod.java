package designpatterns.creational.factorymethod;

/**
 * Factory Method 패턴 예제
 * - 객체 생성을 서브클래스에 위임
 * - 하나의 추상 클래스에 공통 로직과 팩토리 메서드 집중 (중간 추상 클래스 불필요)
 * - 클라이언트는 구체 클래스를 모르고 추상 타입만 사용
 */
public interface FactoryMethod {

    // 팩토리 메서드 (서브클래스가 구현)
    Payment createPayment();

    // 공통 로직 (default 메서드로 구현)
    default void process(int amount) {
        validateAmount(amount);
        Payment payment = createPayment();  // 서브클래스가 결정
        payment.process(amount);
    }

    // 공통 검증 로직
    default void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다.");
        }
    }

}


