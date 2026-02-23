package designpatterns.creational.builder;

import java.util.function.Consumer;

public interface FinalStep {
    FinalStep retryCount(int retryCount);
    FinalStep enableLogging(boolean enableLogging);
    FinalStep retryConfig(Consumer<RetryConfig.RetryBuilder> config);
    FinalStep apply(Consumer<FinalStep> consumer);
    FluentBuilder build();
}