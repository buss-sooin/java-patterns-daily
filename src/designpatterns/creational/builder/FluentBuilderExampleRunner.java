package designpatterns.creational.builder;

public class FluentBuilderExampleRunner {
    public static void main(String[] args) {
        FluentBuilder config = FluentBuilder.builder("https://api.example.com/v1")
                .apiKey("sk_live_abc123xyz")
                .retryCount(5)
                .enableLogging(true)
                .build();

        System.out.println("API URL: " + config.getApiUrl());
        System.out.println("API Key: " + config.getApiKey());
        System.out.println("Retry Count: " + config.getRetryCount());
        System.out.println("Logging Enabled: " + config.isEnableLogging());
    }
}