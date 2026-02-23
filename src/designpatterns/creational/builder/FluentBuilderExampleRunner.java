package designpatterns.creational.builder;

public class FluentBuilderExampleRunner {
    public static void main(String[] args) {
        // 사용 예시
        FluentBuilder config = FluentBuilder.builder()
                .apiUrl("https://api.example.com") // Step: apiUrl 필수
                .apiKey("my-secret-key") // Step: apiKey 필수
                .retryCount(5) // 자유 체이닝
                .enableLogging(true)
                .retryConfig(rc -> rc // Nested Builder
                        .maxRetries(3)
                        .backoffMultiplier(1.5))
                .apply(builder -> { // Functional Builder
                    // if 문으로 선택할 옵션에 대한 조건 추가 가능
                    builder.enableLogging(true);
                })
                .build();
        System.out.println(config);

        // Wither로 설정 변경
        FluentBuilder modified = config.with(b ->
                b.retryCount(10).enableLogging(false)
        );
        System.out.println(modified);
    }
}