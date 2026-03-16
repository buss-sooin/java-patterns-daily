package designpatterns.structural.decorator.protection;

/**
 * ProtectionDecorator - 보호 데코레이터
 *
 * 의도:
 * 실제 객체에 대한 접근을 제어하여, 권한 체크나 검증 후에만 호출을 허용한다.
 * 민감 작업을 보호하고 보안을 강화한다.
 *
 * 특징:
 * - 접근 전 권한/인증/입력 검증을 수행
 * - 실패 시 예외를 던져 호출을 차단
 * - 복잡한 권한 로직은 PermissionChecker 클래스로 분리 (OCP 준수)
 * - 데코레이터 체인으로 다른 기능(로깅, 캐싱)과 결합 가능
 * - 스프링 Security @PreAuthorize 한계 보완
 *
 * 실무 적용:
 * Spring Security Role만으로는 부족한 복합 권한 체크(등급 + 시간대 + IP + 데이터 소유권 등)에서 직접 구현.
 * 레거시 코드나 인터페이스 없는 클래스에 상속 방식으로 적용 가능.
 * 컴포짓 프록시와 차이: 단순 권한 제어가 아닌, 여러 기능을 동적으로 추가하는 데코레이터 스타일 확장.
 */
public class ProtectionDecorator implements ProtectedService {

    private final ProtectedService realService;
    private final UserContext userContext;

    public ProtectionDecorator(ProtectedService realService, UserContext userContext) {
        this.realService = realService;
        this.userContext = userContext;
    }

    // 데코레이터는 접근 제어 역할만, 권한 로직이 복잡할 경우 별도 클래스로 권한 판단 로직만 담당만
    @Override
    public String processSensitiveData(String data) {
        // 1. 권한 체크 (실무에서 가장 중요)
        if (!userContext.hasRole("ADMIN") && !userContext.hasRole("PAYMENT_MANAGER")) {
            throw new SecurityException("권한 없음: ADMIN 또는 PAYMENT_MANAGER 필요");
        }

        // 2. 입력 검증 (XSS, SQL 인젝션 방지)
        if (data == null || data.isBlank() || data.contains("<script>")) {
            throw new IllegalArgumentException("잘못된 입력 데이터");
        }

        // 3. 데이터 소유권 체크 (실무에서 자주 추가)
        if (!userContext.isOwnerOf(data)) {
            throw new SecurityException("데이터 소유권 없음");
        }

        System.out.println("보호 데코레이터 통과: 권한·검증 OK");
        return realService.processSensitiveData(data);
    }

}
