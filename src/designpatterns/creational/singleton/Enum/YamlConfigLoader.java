package designpatterns.creational.singleton.Enum;

import java.util.Map;

/**
 * 예시 구현체 1: YAML 파일에서 로드
 */
class YamlConfigLoader implements ConfigLoader {

    @Override
    public Map<String, String> load() {
        // 실제로는 SnakeYAML, Jackson 등으로 YAML 파싱
        // 여기서는 시뮬레이션
        return Map.of(
                "api.key", "abc123xyz",
                "database.url", "jdbc:mysql://localhost:3306/app",
                "app.env", System.getenv().getOrDefault("APP_ENV", "dev")
        );
    }

}
