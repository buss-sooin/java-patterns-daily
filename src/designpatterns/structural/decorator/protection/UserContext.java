package designpatterns.structural.decorator.protection;

public class UserContext {

    private final String role;
    private final Long userId;

    public UserContext(String role, Long userId) {
        this.role = role;
        this.userId = userId;
    }

    public boolean hasRole(String required) {
        return role.equals(required);
    }

    public boolean isOwnerOf(String data) {
        // DB나 토큰에서 소유권 검증
        return true;
    }

}
