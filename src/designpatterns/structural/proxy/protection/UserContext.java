package designpatterns.structural.proxy.protection;

/**
 * UserContext - 사용자 권한 mock 클래스
 *
 * 의도:
 * 보호 프록시에 필요한 사용자 권한 정보를 제공한다.
 * 스프링 인증 빈을 흉내낸 mock으로, 실제 작업에서 권한 체크를 시뮬레이션한다.
 *
 * 특징:
 * - 역할(role) 기반 간단 체크
 * - 실제 스프링이라면 SecurityContextHolder.getContext().getAuthentication().getAuthorities() 등을 사용해 인증 빈에서 권한 추출.
 */
public class UserContext {

    private final String role;

    public UserContext(String role) { this.role = role; }
    public boolean hasRole(String required) { return role.equals(required); }

}
