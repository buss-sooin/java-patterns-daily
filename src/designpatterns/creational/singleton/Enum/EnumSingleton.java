package designpatterns.creational.singleton.Enum;

import designpatterns.creational.singleton.Enum.factory.ConfigFactory;
import designpatterns.creational.singleton.Enum.factory.DBConfigLoaderFactory;
import designpatterns.creational.singleton.Enum.factory.YamlConfigLoaderFactory;

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

    private EnumSingleton() {
        // 전체 데이터 메모리가 1MB 이상이거나 데이터 로드 유형이 3가지 이상일때 패턴화 고려 (지금은 예시라 2개로만 구현)
        // JVM 옵션으로 -Denv=prod or dev 전달
        String env = System.getProperty("env", "dev");
        ConfigFactory factory = switch (env) {
            case "dev"   -> new YamlConfigLoaderFactory();
            case "prod"  -> new DBConfigLoaderFactory();
            default      -> throw new IllegalArgumentException();
        };

        this.values = Map.copyOf(factory.load());
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
