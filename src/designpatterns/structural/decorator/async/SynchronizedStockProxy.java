package designpatterns.structural.decorator.async;

/**
 * SynchronizedStockProxy - 동기 프록시
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하여, 멀티스레드 환경에서 동시성 문제를 방지한다.
 * 메서드 호출을 직렬화(synchronized)해서 안전하게 보호한다.
 *
 * 특징:
 * - synchronized로 메서드 실행을 한 번에 하나씩만 처리
 * - 기존 비즈니스 로직을 한 줄도 수정하지 않고 안전하게 감쌈
 * - 전후에 로깅, 타임아웃, 재시도 등 자유롭게 추가 가능
 *
 * 실무 적용:
 * 공유 자원(재고, 캐시, 카운터)을 다루는 핵심 비즈니스 로직에 사용.
 * 프록시의 가치: 기존 코드를 건드리지 않고 동기화 + 로깅 + 권한 등을 추가할 수 있음.
 * 인터페이스 버전으로 구현하므로 컴포지션(위임) 방식 사용 → 상속보다 확장성·유연성 좋음.
 * 인터페이스 없는 레거시 클래스인 경우 상속으로 wrapping 가능
 * (Effective Java Item 18: 상속 대신 컴포지션 우선 권장, 하지만 레거시 호환 시 상속 사용을 엄히 고려)
 */
public class SynchronizedStockProxy implements StockService {

    private final StockService realService;
    private final Object lock = new Object();   // 별도 락 객체 추천

    public SynchronizedStockProxy(StockService realService) {
        this.realService = realService;
    }

    @Override
    public void decreaseStock(Long productId, int qty) {
        synchronized (lock) {                    // ← 동기화 구간
            System.out.println("[동기화 시작] productId=" + productId);

            realService.decreaseStock(productId, qty);   // 실제 비즈니스 로직 호출

            System.out.println("[동기화 종료] productId=" + productId);
        }
    }

}
