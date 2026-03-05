package designpatterns.creational.abstractfactory;

public class CompanyBFactory implements DeliveryApiFactory {

    @Override
    public DeliveryApi createDeliveryApi() {
        return new CompanyBDeliveryApi();
    }

}