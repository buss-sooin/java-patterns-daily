package designpatterns.creational.factorymethod;

/**
 * Factory Method - FactoryMethod (결제 팩토리 인터페이스)
 *
 * 의도:
 * 객체 생성을 서브클래스에 위임하여 클라이언트가 구체 클래스에 의존하지 않게 한다.
 * 새로운 결제 방식 추가 시 기존 코드 수정 없이 서브클래스만 추가하면 확장 가능하다.
 *
 * 특징:
 * - createPayment()를 서브클래스가 구현
 * - default 메서드로 공통 로직(process, validate) 중앙화
 * - 클라이언트는 FactoryMethod 타입만 알면 됨
 * - OCP 준수: 새로운 방식 추가 시 Factory 클래스 하나만 생성
 * - Simple Factory보다 확장성과 테스트 용이성 높음
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


