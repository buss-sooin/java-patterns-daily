package designpatterns.structural.proxy.protection;

/**
 * ProtectionProxy - 보호 프록시
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하여, 권한 체크나 검증 후에만 호출 허용한다.
 * 민감 작업을 보호하고 보안을 강화한다.
 *
 * 특징:
 * - 접근 전 권한/인증/입력 검증
 * - 실패 시 예외 던지기
 * - 실무에서 민감 API·데이터 보호에 자주 사용
 */
public class ProtectionProxy implements ProtectedService {

    private final ProtectedService realService;
    private final UserContext userContext;

    public ProtectionProxy(ProtectedService realService, UserContext userContext) {
        this.realService = realService;
        this.userContext = userContext;
    }

    @Override
    public void sensitiveOperation(String data) {
        if (!userContext.hasRole("ADMIN")) {
            throw new SecurityException("권한 없음");
        }
        if (data == null || data.contains("<script>")) {
            throw new IllegalArgumentException("잘못된 입력");
        }
        realService.sensitiveOperation(data);
    }

}
