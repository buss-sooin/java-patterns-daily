package designpatterns.creational.builder;

/**
 * FluentBuilder 패턴 예제
 * - 체이닝(Fluent Interface)을 강조한 빌더
 * - 필수: apiUrl
 * - 선택: apiKey, retryCount, enableLogging
 * - 실무 예: 상태 타입을 받아 내부에서 핸들러를 결정하는 구조에 활용 가능 (상태 패턴과 조합 시)
 * - 빌더가 상태 타입을 받아서 "이 상태에 맞는 핸들러를 내부적으로 결정"
 * - 예: 빌더 내부에서 StatusHandlerResolver 같은 결정자가 타입에 따라 핸들러를 선택
 *   (실제 상태 패턴 구현은 나중에 직접 하기로, 여기서는 껍데기 주석만)
 */
public final class FluentBuilder {

    private final String apiUrl;
    private String apiKey;
    private int retryCount = 3;
    private boolean enableLogging = false;

    // 상태 패턴 관련 껍데기 (아직 구현 전, 주석만)
    // private DeliveryStatusType statusType;
    // private StatusHandler statusHandler;

    private FluentBuilder(Builder builder) {
        this.apiUrl = builder.apiUrl;
        this.apiKey = builder.apiKey;
        this.retryCount = builder.retryCount;
        this.enableLogging = builder.enableLogging;

        // 상태 패턴 관련 (껍데기 주석만)
        // if (builder.statusType != null) {
        //     this.statusHandler = StatusHandlerResolver.resolve(builder.statusType);
        //     // 빌더 내부에서 타입에 따라 핸들러 결정 후 연결
        // }
    }

    public static Builder builder(String apiUrl) {
        return new Builder(apiUrl);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isEnableLogging() {
        return enableLogging;
    }

    @Override
    public String toString() {
        return "FluentBuilder{" +
                "apiUrl='" + apiUrl + '\'' +
                ", apiKey='" + (apiKey != null ? "****" + apiKey.substring(Math.max(0, apiKey.length() - 4)) : "null") + '\'' +
                ", retryCount=" + retryCount +
                ", enableLogging=" + enableLogging +
                '}';
    }

    public static class Builder {
        private final String apiUrl;
        private String apiKey;
        private int retryCount = 3;
        private boolean enableLogging = false;

        // 상태 패턴 관련 껍데기 (아직 구현 전, 주석만)
        // private DeliveryStatusType statusType;

        private Builder(String apiUrl) {
            if (apiUrl == null || apiUrl.isBlank()) {
                throw new IllegalArgumentException("apiUrl은 필수입니다.");
            }
            this.apiUrl = apiUrl;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder retryCount(int retryCount) {
            if (retryCount < 0) {
                throw new IllegalArgumentException("retryCount는 0 이상이어야 합니다.");
            }
            this.retryCount = retryCount;
            return this;
        }

        public Builder enableLogging(boolean enable) {
            this.enableLogging = enable;
            return this;
        }

        // 상태 패턴 관련 체이닝 메서드 (껍데기만, 실제 구현은 나중에)
        // public Builder statusType(DeliveryStatusType statusType) {
        //     this.statusType = statusType;
        //     return this;
        // }

        public FluentBuilder build() {
            return new FluentBuilder(this);
        }
    }

}