package designpatterns.structural.proxy.logging;

import java.time.Instant;

/**
 * LoggingProxy - 로깅 프록시
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하여, 호출 전후에 로깅을 추가한다.
 * 메서드 실행 시간, 파라미터, 결과 등을 기록해 디버깅과 모니터링을 강화한다.
 *
 * 특징:
 * - 호출 전후 투명한 로깅 추가
 * - 실행 시간 측정 포함
 * - 예외 발생 시 로그 기록
 * - 실무에서 메서드 실행 시간/파라미터 로깅에 자주 사용
 * - AOP 한계 (self-invocation 등) 보완
 * - 스프링 AOP CGLIB vs AspectJ: CGLIB는 final/private/static 제한, AspectJ는 위빙으로 더 넓은 범위 적용 가능
 * - 인터페이스 없어 상속 사용: Effective Java Item 18처럼 상속 대신 컴포지션(wrapping) 추천, 오버라이드 제약 줄임
 */
public class LoggingProxy extends RealLoggingService {

    private final RealLoggingService realService;

    public LoggingProxy(RealLoggingService realService) {
        this.realService = realService;
    }

    @Override
    public String performOperation(String input) {
        long start = Instant.now().toEpochMilli();
        System.out.println("입력 파라미터: " + input);

        String result;
        try {
            result = realService.performOperation(input);
            System.out.println("결과: " + result);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
            throw e;
        } finally {
            long duration = Instant.now().toEpochMilli() - start;
            System.out.println("실행 시간: " + duration + "ms");
        }
        return result;
    }

}