package designpatterns.structural.decorator.protection;

public class RealProtectedService implements ProtectedService {

    @Override
    public String processSensitiveData(String data) {
        return "민감 데이터 처리 완료: " + data;
    }

}
