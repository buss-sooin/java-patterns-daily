package designpatterns.creational.abstractfactory;

/**
 * Abstract Factory - AbstractFactory (UI 제품군 생성)
 *
 * 의도:
 * 관련된 객체들의 제품군을 생성하는 인터페이스를 제공한다.
 * 클라이언트가 구체적인 제품 클래스에 의존하지 않고 전체 테마(Windows/macOS)를 한 번에 교체할 수 있게 한다.
 *
 * 특징:
 * - 여러 제품(Button, Checkbox, Window)을 한 번에 생성
 * - default paintUI()로 공통 로직 중앙화
 * - 팩토리만 교체하면 전체 UI 스타일 전환 (OCP 강력 준수)
 * - 테마/플랫폼별 제품군이 많을 때 가장 적합
 * - 인터페이스 + default 메서드로 추상 클래스 없이 구현 가능
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
