package designpatterns.structural.proxy.composite;

/**
 * LoggingProxy - 로깅 프록시 (메시지 큐)
 *
 * 의도:
 * 실제 객체 호출 후 결과 로깅 (메시지 큐 비동기 전송).
 * 모니터링 SaaS에서 결과 추적 강화.
 *
 * 특징:
 * - Kafka 등 큐로 비동기 로깅 (메모리 효율)
 * - 큐 전송 실패 시 재시도/버퍼링
 */
public class LoggingProxy implements MonitoringService {

    private final MonitoringService realService;
    private final MessageQueueSender queueSender; // KafkaSender mock

    public LoggingProxy(MonitoringService realService, MessageQueueSender queueSender) {
        this.realService = realService;
        this.queueSender = queueSender;
    }

    @Override
    public String collectData(String input) {
        String result = realService.collectData(input);
        queueSender.sendAsync("result-log", result); // 비동기 큐 전송
        return result;
    }

}
