package designpatterns.structural.decorator.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CachingDecorator - 서비스 레이어 캐싱 데코레이터
 *
 * 의도:
 * 기존 서비스에 동적으로 캐싱 기능을 추가한다.
 * @Cacheable 한계(복잡 키, 동적 TTL, 캐시 무효화)를 극복한다.
 *
 * 특징:
 * - 정적 TTL로 단순하게 구현 (실무 예제용)
 * - 캐시 무효화 메서드 제공
 * - 실무에서 사용자 프로필, 설정 정보 등 반복 조회에 자주 사용
 * - computeIfAbsent로 thread-safe 캐싱 구현
 * - 캐시 미스 시에만 DB 조회 실행 (캐시 관통 방지)
 * - 실제로는 Caffeine 또는 Redis 같은 라이브러리로 대체
 *
 * 동적 TTL 역할:
 * 실무에서는 사용자 등급, 데이터 유형, 요청 조건에 따라 TTL을 다르게 적용해야 함.
 * 예: VIP는 10분, 일반은 5분. CacheConfig 클래스를 통해 동적 TTL 주입 추천.
 *
 * 컴포짓 (프록시)와의 차이:
 * 코드 구조는 매우 유사하지만, 패턴을 가르는 핵심은 '의도'다.
 * - Decorator: 기존 객체에 새로운 기능을 동적으로 추가 (기능 확장)
 * - Composite: 부분과 전체를 동일하게 다루는 계층 구조 (트리 형태)
 */
public class CachingDecorator implements UserProfileService {

    private final UserProfileService realService;
    private final Map<Long, CacheEntry<UserProfile>> cache = new ConcurrentHashMap<>();

    public CachingDecorator(UserProfileService realService) {
        this.realService = realService;
    }

    @Override
    public UserProfile getProfile(Long userId) {
        // computeIfAbsent는 ConcurrentHashMap의 핵심 안전 장치입니다.
        // 여러 스레드가 동시에 같은 키로 호출해도 내부적으로 한 번만 DB 조회를 실행합니다.
        // (캐시 관통 방지 + thread-safe)
        return cache.computeIfAbsent(userId, key -> {
            System.out.println("캐시 미스 → DB 조회");
            UserProfile profile = realService.getProfile(key);
            return new CacheEntry<>(profile, 300_000); // 5분 TTL
        }).value;
    }

    // 실무 필수: 캐시 무효화
    public void invalidate(Long userId) {
        cache.remove(userId);
        System.out.println("캐시 무효화: userId=" + userId);
    }

    private record CacheEntry<T>(T value, long expiryTime) {
        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

}