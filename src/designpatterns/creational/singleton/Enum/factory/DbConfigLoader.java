package designpatterns.creational.singleton.Enum.factory;

import designpatterns.creational.singleton.Enum.factory.ConfigLoader;

import java.util.Map;

/**
 * 예시 구현체 2: DB에서 로드 (JPA나 JDBC 사용 가능)
 */
public class DbConfigLoader implements ConfigLoader {

    @Override
    public Map<String, String> load() {
        // 실제 DB 연결 및 쿼리 수행 (JDBC 예시)
        // 여기서는 시뮬레이션
        return Map.of(
                "secret.token", "secure-token-987654",
                "payment.gateway.key", "pg_live_key_xxxx"
        );
    }

}
