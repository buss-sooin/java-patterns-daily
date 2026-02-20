package designpatterns.creational.abstractfactory;

public class WindowsFactory implements AbstractFactory {

    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

    public Window createWindow() {
        return new WindowsWindow();
    }

}
