package designpatterns.creational.singleton.Enum;

import designpatterns.creational.singleton.Enum.factory.ConfigFactory;
import designpatterns.creational.singleton.Enum.factory.DBConfigLoaderFactory;
import designpatterns.creational.singleton.Enum.factory.YamlConfigLoaderFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum Singleton - EnumSingleton (설정 정보 관리)
 *
 * 의도:
 * 클래스의 인스턴스가 오직 하나만 존재하도록 보장하고, 이에 대한 전역적인 접근점을 제공한다.
 * 애플리케이션 전체에서 공유되어야 하는 상태나 리소스(설정 정보, 연결 풀 등)를
 * 일관되게 관리할 수 있게 한다.
 *
 * 특징:
 * - 클래스 로딩 시점에 단 한 번만 인스턴스가 생성됨 (eager initialization)
 * - JVM 수준에서 thread-safe, serialization-safe, reflection-safe 보장
 * - 보안 민감 정보(API 키, 토큰 등) 관리에 가장 적합
 * - 초기화 비용이 거의 없을 때 적합
 * - Java의 Enum 특성을 활용한 Singleton 구현으로, Joshua Bloch가 Effective Java에서
 *   "싱글톤을 구현하는 가장 좋은 방법"으로 권장
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
