package designpatterns.creational.factorymethod;

/**
 * Simple Factory - PaymentFactory (결제 방식 생성)
 *
 * 의도:
 * 객체 생성 로직을 한 곳에 집중시켜 캡슐화한다.
 * 클라이언트가 구체 클래스에 직접 의존하지 않고 타입 문자열로만 객체를 얻을 수 있게 한다.
 *
 * 특징:
 * - static 메서드 하나로 모든 생성 처리 (인스턴스 없이 사용)
 * - switch나 Map으로 타입 매핑
 * - Factory Method의 가장 간소화된 형태
 * - 확장 시 메서드만 추가하면 되지만 OCP 위반 가능성 있음
 * - 실무에서 프로토타입, 간단한 타입 기반 생성, 초기 학습용으로 자주 사용
 */
public interface Payment {
    void process(int amount);
}
