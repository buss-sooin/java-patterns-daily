package designpatterns.structural.proxy.composite;

public class RealMonitoringService implements MonitoringService {

    @Override
    public String collectData(String input) {
        return "데이터 수집: " + input;
    }

}
