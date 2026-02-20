package designpatterns.creational.abstractfactory;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void check() {
        System.out.println("Windows 체크박스 체크");
    }

}
