package designpatterns.creational.singleton.Holder;

public class HolderSingletonExampleRunner {
    public static void main(String[] args) {
        HolderSingleton config = HolderSingleton.getInstance();
        System.out.println("API URL: " + config.get("api.url"));
        System.out.println("API Key: " + config.get("api.key"));
        System.out.println("Retry Count: " + config.getOrDefault("retry.count", "3"));
        System.out.println("전체 설정 개수: " + config.getAll().size());
    }
}