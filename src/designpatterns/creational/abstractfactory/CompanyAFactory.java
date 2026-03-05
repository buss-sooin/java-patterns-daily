package designpatterns.creational.abstractfactory;

public class CompanyAFactory implements DeliveryApiFactory {

    @Override
    public DeliveryApi createDeliveryApi() {
        return new CompanyADeliveryApi();
    }

}