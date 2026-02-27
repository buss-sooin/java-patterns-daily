package designpatterns.structural.proxy.caching;

public interface AddressApiClient {
    Address validateAndNormalize(String rawInput);
}