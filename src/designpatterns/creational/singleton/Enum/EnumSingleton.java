package designpatterns.creational.singleton.Enum;

import designpatterns.creational.singleton.Enum.factory.ConfigFactory;
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
        ConfigFactory factory = new YamlConfigLoaderFactory();
        this.values = factory.load();
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
