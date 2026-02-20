package designpatterns.creational.abstractfactory;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("Windows 버튼 렌더링");
    }

}
