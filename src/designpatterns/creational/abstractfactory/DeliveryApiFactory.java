package designpatterns.creational.abstractfactory;

/**
 * DeliveryApiFactory - 배송 API 팩토리 인터페이스
 *
 * 의도:
 * 관련된 객체들의 제품군을 생성하는 인터페이스를 제공한다.
 * 클라이언트가 구체적인 제품 클래스에 의존하지 않고 전체 제품군을 한 번에 교체할 수 있게 한다.
 *
 * 특징:
 * - 회사별 API 제품군 생성
 * - 인터페이스 + default 메서드 사용
 * - 확장 용이: 새 회사 추가 시 팩토리 구현만
 */
public interface DeliveryApiFactory {

    DeliveryApi createDeliveryApi();

    // 회사별 팩토리 선택 로직 예시 (Provider 결합 추천)
    static DeliveryApiFactory getFactory(String company) {
        return switch (company.toUpperCase()) {
            case "COMPANY_A" -> new CompanyAFactory();
            case "COMPANY_B" -> new CompanyBFactory();
            default -> new CompanyAFactory(); // 기본
        };
    }

}