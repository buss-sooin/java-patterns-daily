package designpatterns.structural.decorator.logging;

public class RealLoggingService implements LoggingService {

    @Override
    public String performOperation(String input) {
        return "작업 결과: " + input.toUpperCase();
    }

}