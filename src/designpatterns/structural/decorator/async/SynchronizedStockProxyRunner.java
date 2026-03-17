package designpatterns.structural.decorator.async;

public class SynchronizedStockProxyRunner {

    public static void main(String[] args) {
        StockService real = new RealStockService();
        StockService proxy = new SynchronizedStockProxy(real);

        // 여러 스레드에서 동시에 호출 시도
        for (int i = 0; i < 5; i++) {
            new Thread(() -> proxy.decreaseStock(1001L, 1)).start();
        }
    }

}
