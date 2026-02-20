package designpatterns.creational.singleton.Holder;

public class ConfigLoaderFactory {

    /*
     * 팩토리 메서드 도입 시 사용할 수 있는 구조 (아직 구현하지 않음)
     *
     * public class ConfigLoaderFactory {
     *     public static ConfigLoader createDefaultLoader() {
     *         // 프로그래머가 명시적으로 결정
     *         return new YamlConfigLoader();
     *         // 또는 조건에 따라
     *         // return new DbConfigLoader();
     *     }
     * }
     *
     * HolderSingleton 생성 시점에 아래처럼 사용 가능
     * ConfigLoader loader = ConfigLoaderFactory.createDefaultLoader();
     */

}
