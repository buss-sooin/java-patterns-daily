package designpatterns.structural.proxy.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AddressCachingProxy - 주소 API 호출 캐싱 프록시
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하기 위해 대리자를 둔다.
 * 원본 객체 호출 전후에 추가 동작(캐싱, 로깅, 권한 등)을 끼워넣을 수 있게 한다.
 *
 * 특징:
 * - 1차 캐시 Key: 원본 입력 주소 (비정형 그대로)
 * - Value: 정제된 주소명 (또는 실패 표시)
 * - 2차 캐시 Key: 정제된 주소명
 * - Value: API 최종 Address 객체
 * - 실패 케이스도 캐싱 (반복 실패 방지)
 * - TTL로 메모리 관리
 * - ConcurrentHashMap으로 멀티스레드 안전성 확보
 *
 * 실무 적용:
 * 외부 API(카카오 주소 등) 호출 지연·비용 절감을 위해 사용.
 * 비정형 입력 반복 시 1차 캐시로 정제 로직 생략, 정제 후 결과는 2차 캐시로 API 호출 생략.
 * invalidate()는 주소 DB 변경 시 반드시 호출 (캐시 오염 방지 필수).
 * ConcurrentHashMap 대신 Caffeine 같은 캐시 라이브러리 사용 추천 (자동 TTL·크기 제한·통계 제공).
 */
public class AddressCachingProxy implements AddressApiClient {

    private final AddressApiClient realClient;
    private final AddressNormalizer normalizer; // 정제 로직

    // 1차 캐시: 원본 입력 → 정제된 주소명 (또는 실패)
    private final Map<String, String> normCache = new ConcurrentHashMap<>();
    private static final long NORM_TTL = 7 * 24 * 60 * 60 * 1000L; // 7일

    // 2차 캐시: 정제된 주소 → API 결과
    private final Map<String, Address> apiCache = new ConcurrentHashMap<>();
    private static final long API_TTL = 30 * 24 * 60 * 60 * 1000L; // 30일

    public AddressCachingProxy(AddressApiClient realClient, AddressNormalizer normalizer) {
        this.realClient = realClient;
        this.normalizer = normalizer;
    }

    @Override
    public Address validateAndNormalize(String rawInput) {
        if (rawInput == null || rawInput.isBlank()) {
            return null;
        }

        // 1차 캐시 확인
        String normalized = normCache.computeIfAbsent(rawInput, key -> {
            String result = normalizer.normalize(key);
            return result != null && !result.isBlank() ? result : "FAIL";
        });

        if ("FAIL".equals(normalized)) {
            return null; // 정제 실패
        }

        // 2차 캐시 확인
        return apiCache.computeIfAbsent(normalized, key -> {
            System.out.println("API 호출: " + key);
            return realClient.validateAndNormalize(key);
        });
    }

    // 캐시 무효화 (실무 필수)
    public void invalidate(String rawInput) {
        normCache.remove(rawInput);
        // 정제된 값도 모르니 2차는 별도 무효화 필요 시 추가 로직
    }

}