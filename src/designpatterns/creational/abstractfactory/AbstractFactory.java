package designpatterns.creational.abstractfactory;

/**
 * Abstract Factory 패턴 예제 (인터페이스 버전)
 * - 관련된 제품군 전체를 한 번에 생성
 * - 공통 로직은 default 메서드로 인터페이스에 구현 (추상 클래스 불필요)
 * - 클라이언트는 GUIFactory만 알면 UI 전체 스타일 전환 가능
 */
public interface AbstractFactory {

    // 제품 생성 메서드들 (추상 메서드)
    Button createButton();
    Checkbox createCheckbox();
    Window createWindow();

    // 공통 로직 (default 메서드로 구현)
    default void paintUI() {
        validateTheme();  // 공통 검증
        createButton().paint();
        createCheckbox().check();
        createWindow().open();
    }

    // 공통 검증 로직 (default 메서드)
    default void validateTheme() {
        System.out.println("[공통] 테마 유효성 검사 완료");
    }

}
