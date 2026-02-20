package designpatterns.creational.singleton.Holder;

import java.util.Map;

public interface ConfigLoader {
    Map<String, String> load();
}
