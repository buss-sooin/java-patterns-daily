package designpatterns.structural.proxy.composite;

import java.util.Arrays;

/**
 * ExceptionHandlingProxy - 예외처리 프록시 (파일 로깅)
 *
 * 의도:
 * 실제 객체 호출 중 예외 발생 시 파일 로깅.
 * 모니터링 SaaS에서 예외 추적 강화.
 *
 * 특징:
 * - BufferedWriter로 메모리 효율적 파일 로깅
 * - 예외 발생 시만 파일 쓰기 (IO 최소화)
 * - 재시도 로직 추가 가능
 */
public class ExceptionHandlingProxy implements MonitoringService {

    private final MonitoringService realService;
    private final FileLogger fileLogger; // BufferedWriter mock

    public ExceptionHandlingProxy(MonitoringService realService, FileLogger fileLogger) {
        this.realService = realService;
        this.fileLogger = fileLogger;
    }

    @Override
    public String collectData(String input) {
        try {
            return realService.collectData(input);
        } catch (Exception e) {
            fileLogger.logToFile("예외: " + e.getMessage() + " | 스택: " + Arrays.toString(e.getStackTrace())); // 파일 로깅
            throw e;
        }
    }

}
