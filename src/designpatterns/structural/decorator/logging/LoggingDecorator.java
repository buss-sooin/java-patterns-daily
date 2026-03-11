package designpatterns.structural.decorator.logging;

import java.time.Instant;
import java.time.Duration;

/**
 * LoggingDecorator - 로깅 + 예외 + 성능 측정 복합 데코레이터
 *
 * 의도:
 * 실제 객체 호출 전후에 로깅, 예외 처리, 성능 측정을 추가한다.
 * 모니터링 SaaS에서 자주 사용되는 복합 데코레이터 패턴.
 *
 * 특징:
 * - 실행 전 파라미터 로깅
 * - 실행 후 결과 + 시간 로깅
 * - 예외 발생 시 예외 정보 로깅
 * - try-catch-finally로 안정적 처리
 *
 * 스프링 실무 적용:
 * - @Service로 등록하여 싱글톤 빈으로 사용
 * - 실제 로깅은 SLF4J + Logback 또는 Log4j2로 교체
 * - @Around AOP와 결합 가능 (self-invocation 문제 발생 시 직접 Proxy 사용)
 */
public class LoggingDecorator implements LoggingService {

    private final LoggingService realService;

    public LoggingDecorator(LoggingService realService) {
        this.realService = realService;
    }

    @Override
    public String performOperation(String input) {
        long start = Instant.now().toEpochMilli();
        System.out.println("[로깅] 입력 파라미터: " + input);

        String result;
        try {
            result = realService.performOperation(input);
            System.out.println("[로깅] 결과: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("[예외 로깅] " + e.getClass().getName() + " - " + e.getMessage());
            throw e;
        } finally {
            long duration = Instant.now().toEpochMilli() - start;
            System.out.println("[성능 측정] 실행 시간: " + duration + "ms");
        }
    }

}