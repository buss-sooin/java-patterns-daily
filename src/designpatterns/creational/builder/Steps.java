package designpatterns.creational.builder;

import java.util.function.Consumer;

// Steps를 public으로 유지하고 getter 추가하는 방식으로도 구현 가능
final class Steps implements ApiUrlStep, ApiKeyStep, FinalStep {

    String apiUrl;
    String apiKey;
    int retryCount = 3;
    boolean enableLogging = false;
    RetryConfig.RetryBuilder retryConfig;

    @Override
    public ApiKeyStep apiUrl(String apiUrl) {
        if (apiUrl == null || apiUrl.isBlank()) {
            throw new IllegalArgumentException("apiUrl 필수");
        }

        this.apiUrl = apiUrl;
        return this;
    }

    @Override
    public FinalStep apiKey(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("apiKey 필수");
        }

        this.apiKey = apiKey;
        return this;
    }

    @Override
    public FinalStep retryCount(int retryCount) {
        if (retryCount < 0) throw new IllegalArgumentException("retryCount >= 0");
        this.retryCount = retryCount;
        return this;
    }

    @Override
    public FinalStep enableLogging(boolean enableLogging) {
        this.enableLogging = enableLogging;
        return this;
    }

    // Nested Builder 호출 지점
    @Override
    public FinalStep retryConfig(Consumer<RetryConfig.RetryBuilder> config) {
        if (retryConfig == null) retryConfig = RetryConfig.builder();
        config.accept(retryConfig);
        return this;
    }

    // Functional Builder 호출 지점
    @Override
    public FinalStep apply(Consumer<FinalStep> consumer) {
        consumer.accept(this);
        return this;
    }

    @Override
    public FluentBuilder build() {
        return new FluentBuilder(this);
    }
}