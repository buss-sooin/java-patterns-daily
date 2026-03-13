package designpatterns.structural.decorator.compression;

public class RealLogCollector implements LogCollector {

    @Override
    public void collect(LogEvent event) {
        // 실제로 Kafka/S3 등에 전송
        System.out.println("실제 전송: " + event);
    }

}
