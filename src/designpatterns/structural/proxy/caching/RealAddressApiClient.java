package designpatterns.structural.proxy.caching;

public class RealAddressApiClient implements AddressApiClient {
    @Override
    public Address validateAndNormalize(String rawInput) {
        System.out.println("외부 API 호출: " + rawInput);
        // 실제 카카오 API 호출 시뮬레이션 (지연 500ms)
        try { Thread.sleep(500); } catch (Exception ignored) {}
        return new Address(rawInput, "상세주소", "12345");
    }
}