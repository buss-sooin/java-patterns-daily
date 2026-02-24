package designpatterns.creational.simplefactory;

import designpatterns.creational.PaymentType;
import java.util.Map;
import java.util.function.Supplier;

/**
 * [Payment/UI]FactoryProvider - [결제/테마] 팩토리 선택 Provider
 *
 * 의도:
 * 런타임에 타입(또는 테마)에 따라 적절한 팩토리를 중앙에서 제공한다.
 * 클라이언트가 구체 팩토리에 직접 의존하지 않고 Provider를 통해 일관되게 얻을 수 있게 한다.
 *
 * 특징:
 * - Enum 키 + Map으로 타입 안전한 매핑
 * - Supplier 사용으로 지연 생성(lazy initialization)
 * - 기본값 제공으로 안전성 확보
 * - 새로운 타입 추가 시 Map 한 줄만 추가
 * - 테스트 시 Mock Provider 주입 용이
 */
public class PaymentFactoryProvider {
    private static final Map<PaymentType, Supplier<Payment>> FACTORIES = Map.of(
            PaymentType.CREDIT, CreditCardPayment::new,
            PaymentType.KAKAO, KakaoPayPayment::new,
            PaymentType.PAYCO, PaycoPayment::new
    );

    private static final Supplier<Payment> DEFAULT = CreditCardPayment::new;

    public Payment get(PaymentType type) {
        return FACTORIES.getOrDefault(type, DEFAULT).get();
    }
}