package designpatterns.creational.abstractfactory;

/**
 * Abstract Factory 패턴 사용 예시 Runner
 * - Windows와 macOS 테마를 팩토리만 바꿔서 전체 UI 제품군 전환 확인
 */
public class AbstractFactoryExampleRunner {

    public static void main(String[] args) {
        // 1. Windows 테마 전체 사용
        AbstractFactory windowsFactory = new WindowsFactory();
        Application winApp = new Application(windowsFactory);
        System.out.println("=== Windows 테마 UI 생성 ===");
        winApp.createUI();

        // 2. macOS 테마로 한 번에 전환 (클라이언트 코드 수정 없이 팩토리만 교체)
        AbstractFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        System.out.println("\n=== macOS 테마 UI 생성 ===");
        macApp.createUI();
    }

    // 클라이언트 클래스 (제품군 전체를 사용하는 곳)
    static class Application {
        private final AbstractFactory factory;

        Application(AbstractFactory factory) {
            this.factory = factory;
        }

        void createUI() {
            factory.paintUI();  // 공통 로직 호출 (default 메서드)
        }
    }

}