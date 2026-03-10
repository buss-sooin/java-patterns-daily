package designpatterns.structural.proxy.logging;

public class RealLoggingService {

    public String performOperation(String input) {
        return "작업 결과: " + input.toUpperCase();
    }

}