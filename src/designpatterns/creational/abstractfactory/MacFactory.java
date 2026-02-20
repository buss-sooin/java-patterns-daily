package designpatterns.creational.abstractfactory;

public class MacFactory implements AbstractFactory {

    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

    public Window createWindow() {
        return new MacWindow();
    }

}
