package designpatterns.creational.singleton.Enum.factory;

public class YamlConfigLoaderFactory implements ConfigFactory {

    @Override
    public ConfigLoader LOADER() {
        return new YamlConfigLoader();
    }

}
