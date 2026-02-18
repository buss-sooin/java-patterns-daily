package designpatterns.creational.singleton.Enum;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigLoader 인터페이스를 통해 DB/YAML/환경변수 등 다양한 소스에서 설정을 로드할 수 있도록 설계.
 * Enum 싱글톤 내부에서 인터페이스 구현체를 직접 생성하여 사용.
 *
 * 장점:
 * - 스프링 없이도 완전히 독립적으로 동작
 * - 나중에 스프링 환경으로 옮길 때 ConfigLoader 구현체만 교체하거나 @Component로 변경 가능
 * - 테스트 시 Mock ConfigLoader 주입 가능 (생성자에 파라미터로 받는 방식으로 확장 용이)
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



