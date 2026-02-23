package designpatterns.creational.builder;

import java.util.function.Consumer;

/**
 * FluentBuilder 패턴 예제
 *
 * 의도:
 * 복잡한 객체를 단계적으로 구성할 수 있게 하면서, 생성 과정과 표현을 분리한다.
 * 클라이언트가 객체의 내부 표현을 알지 못해도 다양한 표현을 생성할 수 있게 한다.
 *
 * 특징:
 * - Step Builder 패턴: 필수 파라미터의 설정 순서를 컴파일 타임에 강제한다.
 *   일반적인 Builder가 내부 클래스로 구현되는 것과 달리, 인터페이스(ApiUrlStep, ApiKeyStep, FinalStep)를
 *   통해 단계별로 사용 가능한 메서드를 제한하여 타입 안전성을 보장한다.
 *
 * - Nested Builder: RetryConfig와 같은 복잡한 하위 설정을 Consumer<Builder>를 통해
 *   계층적으로 구성할 수 있다. 이를 통해 관련 설정을 논리적으로 그룹핑하고
 *   각 설정 모듈의 독립성과 재사용성을 유지한다.
 *
 * - Functional Builder: apply() 메서드를 통해 조건부 설정이나 동적 설정을
 *   람다식으로 표현할 수 있어 유연성을 제공한다.
 *
 * - Wither 패턴: 불변 객체의 일부 필드만 변경한 새로운 인스턴스를 생성할 수 있어
 *   함수형 프로그래밍 스타일과 잘 어울린다.
 */
public final class FluentBuilder {

    private final String apiUrl;
    private final String apiKey;
    private final int retryCount;
    private final boolean enableLogging;
    private final RetryConfig retryConfig;

    FluentBuilder(Steps builder) {
        this.apiUrl = builder.apiUrl;
        this.apiKey = builder.apiKey;
        this.retryCount = builder.retryCount;
        this.enableLogging = builder.enableLogging;
        this.retryConfig = builder.retryConfig != null ? builder.retryConfig.build() : null;
    }

    // Wither 패턴: 기존 객체 복사 후 일부만 변경 (immutable 수정 대체)
    public FluentBuilder with(Consumer<Steps> modifier) {
        Steps b = new Steps();
        b.apiUrl(this.apiUrl);
        b.apiKey(this.apiKey);
        b.retryCount(this.retryCount);
        b.enableLogging(this.enableLogging);

        if (this.retryConfig != null) {
            b.retryConfig(rc -> rc
                    .maxRetries(this.retryConfig.getMaxRetries())
                    .backoffMultiplier(this.retryConfig.getBackoffMultiplier()));
        }

        modifier.accept(b);
        return b.build();
    }

    public String getApiUrl() { return apiUrl; }
    public String getApiKey() { return apiKey; }
    public int getRetryCount() { return retryCount; }
    public boolean isEnableLogging() { return enableLogging; }
    public RetryConfig getRetryConfig() { return retryConfig; }

    @Override
    public String toString() {
        return "FluentBuilder{" +
                "apiUrl='" + apiUrl + '\'' +
                ", apiKey='" + (apiKey != null ? "****" + apiKey.substring(Math.max(0, apiKey.length() - 4)) : "null") + '\'' +
                ", retryCount=" + retryCount +
                ", enableLogging=" + enableLogging +
                ", retryConfig=" + retryConfig +
                '}';
    }

    public static ApiUrlStep builder() {
        return new Steps();
    }
}