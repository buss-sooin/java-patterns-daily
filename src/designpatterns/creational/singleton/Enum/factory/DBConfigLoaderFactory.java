package designpatterns.creational.singleton.Enum.factory;

public class DBConfigLoaderFactory implements ConfigFactory {

    @Override
    public ConfigLoader LOADER() {
        return new DbConfigLoader();
    }

}
