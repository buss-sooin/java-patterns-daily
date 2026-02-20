package designpatterns.creational.singleton.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum Singleton - EnumSingleton (설정 정보 관리)
 *
 * 특징:
 * - 클래스 로딩 시점에 단 한 번만 인스턴스가 생성됨 (eager)
 * - JVM 수준에서 thread-safe, serialization-safe, reflection-safe 보장
 * - 보안 민감 정보(API 키, 토큰 등) 관리에 가장 적합
 * - 초기화 비용이 거의 없을 때 적합
 */
public enum EnumSingleton {

    INSTANCE;

    private final Map<String, String> values;

    // 생성 시점에 외부에서 주입받은 loader 사용
    // (프로덕션에서는 팩토리 메서드나 기본값으로 초기화)
    private EnumSingleton() {
        // 실제로는 아래 주석처럼 팩토리 메서드를 통해 loader를 결정해야 함
        // ConfigLoader loader = ConfigLoaderFactory.createDefaultLoader();
        // this.values = loader.load();

        // 임시로 기본 구현체 직접 사용 (팩토리 메서드 도입 전 단계)
        ConfigLoader loader = new YamlConfigLoader();
        Map<String, String> temp = loader.load();
        this.values = Map.copyOf(temp);
    }

    public String get(String key) {
        return values.get(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        return values.getOrDefault(key, defaultValue);
    }

    public Map<String, String> getAll() {
        return new HashMap<>(values);
    }

}
