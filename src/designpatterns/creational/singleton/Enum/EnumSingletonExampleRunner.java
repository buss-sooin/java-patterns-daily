package designpatterns.creational.singleton.Enum;

public class EnumSingletonExampleRunner {
    public static void main(String[] args) {
        EnumSingleton config = EnumSingleton.INSTANCE;
        System.out.println("API URL: " + config.get("api.url"));
        System.out.println("API Key: " + config.get("api.key"));
        System.out.println("전체 설정 개수: " + config.getAll().size());
    }
}
