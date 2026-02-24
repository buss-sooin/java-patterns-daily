package designpatterns.creational.abstractfactory;

import java.util.function.Supplier;

/**
 * Abstract Factory 패턴 사용 예시 Runner
 * - Windows와 macOS 테마를 팩토리만 바꿔서 전체 UI 제품군 전환 확인
 */
public class AbstractFactoryExampleRunner {

    public static void main(String[] args) {
        UIFactoryProvider provider = new UIFactoryProvider();

        AbstractFactory factory = provider.get(UiTheme.MAC);
        factory.paintUI();

        // factory 가 무거울 경우 Lazy 호출
        Supplier<AbstractFactory> win = () -> provider.get(UiTheme.WINDOWS);
        AbstractFactory winFactory = win.get();       // 여기서 new AbstractFactory() 실행 (lazy)
        winFactory.createButton().paint();            // 정상 호출
    }

}