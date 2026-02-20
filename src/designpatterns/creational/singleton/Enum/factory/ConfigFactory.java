package designpatterns.creational.singleton.Enum.factory;

import java.util.Map;

public interface ConfigFactory {

    ConfigLoader LOADER();

    default Map<String, String> load() {
        return LOADER().load();
    }

}
