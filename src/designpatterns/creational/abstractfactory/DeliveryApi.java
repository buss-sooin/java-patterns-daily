package designpatterns.creational.abstractfactory;

/**
 * DeliveryApi - 배송 API 공통 인터페이스
 *
 * 의도:
 * 관련된 객체들의 제품군을 생성하는 인터페이스를 제공한다.
 * 클라이언트가 구체적인 제품 클래스에 의존하지 않고 전체 제품군을 한 번에 교체할 수 있게 한다.
 *
 * 특징:
 * - 배송 흐름(주문, 시작, 업데이트, 완료) 통합
 * - 회사별 API 규격 변형 캡슐화
 * - default 메서드로 공통 로직 중앙화
 * - 확장 시 새로운 팩토리만 추가
 */
public interface DeliveryApi {

    void sendOrder(String orderData);
    void startDelivery(String trackingNumber);
    void updateDelivery(String status);
    void completeDelivery(String completionData);

    // 공통 로직 예시
    default void processFullFlow(String orderData, String trackingNumber) {
        sendOrder(orderData);
        startDelivery(trackingNumber);
        updateDelivery("배송 중");
        completeDelivery("배송 완료");
    }

}