package designpatterns.creational.builder;

public final class RetryConfig {

    private final int maxRetries;
    private final double backoffMultiplier;

    private RetryConfig(RetryBuilder builder) {
        this.maxRetries = builder.maxRetries;
        this.backoffMultiplier = builder.backoffMultiplier;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public double getBackoffMultiplier() {
        return backoffMultiplier;
    }

    @Override
    public String toString() {
        return "RetryConfig{" +
                "maxRetries=" + maxRetries +
                ", backoffMultiplier=" + backoffMultiplier +
                '}';
    }

    public static RetryBuilder builder() {
        return new RetryBuilder();
    }

    public static class RetryBuilder {
        private int maxRetries = 3;
        private double backoffMultiplier = 2.0;

        public RetryBuilder maxRetries(int maxRetries) {
            if (maxRetries < 0) {
                throw new IllegalArgumentException("maxRetries >= 0");
            }

            this.maxRetries = maxRetries;
            return this;
        }

        public RetryBuilder backoffMultiplier(double multiplier) {
            if (multiplier <= 0) {
                throw new IllegalArgumentException("backoffMultiplier > 0");
            }

            this.backoffMultiplier = multiplier;
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(this);
        }
    }
}