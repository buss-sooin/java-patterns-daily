package designpatterns.creational.singleton.Holder;

import java.util.Map;

public class YamlConfigLoader implements ConfigLoader {
    @Override
    public Map<String, String> load() {
        // 실제 YAML 파싱 로직 대신 시뮬레이션
        return Map.of(
                "api.key", "abc123xyz",
                "database.url", "jdbc:mysql://localhost:3306/app",
                "app.env", System.getenv().getOrDefault("APP_ENV", "dev")
        );
    }
}
