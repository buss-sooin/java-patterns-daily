package designpatterns.structural.decorator.async;

public class RealStockService implements StockService {

    @Override
    public void decreaseStock(Long productId, int qty) {
        // 실제 복잡한 재고 차감 로직 (DB 업데이트, 재고 검증 등)
        System.out.println("실제 재고 차감: productId=" + productId + ", qty=" + qty);
    }

}
