package designpatterns.creational.singleton.Enum.factory;

import java.util.Map;

/**
 * 설정 로드 인터페이스
 * - 다양한 소스(DB, YAML, Properties, Env, 외부 Config Server 등)를 추상화
 * - 스프링 없이도 사용 가능하며, 나중에 스프링 빈으로 쉽게 교체 가능
 */
public interface ConfigLoader {

    Map<String, String> load();

}
