package designpatterns.creational.singleton.Holder;

import java.util.HashMap;
import java.util.Map;

/**
 * Holder Idiom Singleton - HolderSingleton (설정 정보 관리)
 *
 * 특징:
 * - 최초 getInstance() 호출 시점에 인스턴스 생성 (진짜 lazy)
 * - JVM 클래스 초기화 락으로 thread-safe 보장 (synchronized/volatile 불필요)
 * - 초기화 비용이 크거나 사용 여부 불확실할 때 적합
 * - Enum과 달리 lazy 초기화 가능
 */
public final class HolderSingleton {

    private final Map<String, String> values;

    private HolderSingleton() {
        // EnumSingleton 의 동적 데이터 로딩을 패턴화 한 것처럼 경우에 따라 셋팅 방식을 고려
        ConfigLoader loader = new YamlConfigLoader();
        Map<String, String> temp = loader.load();
        this.values = Map.copyOf(temp);
    }

    // static inner class (Holder) - 최초 참조 시 초기화
    private static class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
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

    /**
     * 싱글톤 객체는 복제할 수 없습니다.
     * clone() 호출 시도 시 무조건 예외를 던져 복제 방지
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("싱글톤 객체는 복제할 수 없습니다.");
    }

    @Override
    public String toString() {
        return "HolderSingleton{valuesCount=" + values.size() +
                ", sampleKey='" + values.getOrDefault("api.url", "없음") + "'}";
    }

}
