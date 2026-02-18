package designpatterns.creational.singleton.Holder;

/**
 * Holder Idiom Singleton (Bill Pugh 방식)
 * - 최초 getInstance() 호출 시점에 인스턴스 생성 (lazy)
 * - JVM 클래스 초기화 메커니즘 활용 → synchronized 불필요
 */
public class HolderSingleton {

    // private 생성자
    private HolderSingleton() {
        System.out.println("HolderSingleton 인스턴스 생성됨");
    }

    // static inner class (참조될 때 로드됨)
    private static class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    // 전역 접근점
    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }

    // 예시 메서드
    public void sayHello() {
        System.out.println("Hello from HolderSingleton");
    }
}