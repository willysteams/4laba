package ledza.factory;

import ledza.devices.*;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class DeviceFactory {

    public Map<String, AbstractFactory> factoryMap;

    public DeviceFactory() {
        factoryMap = new HashMap<>();
        factoryMap.put("Computer", new ComputerFactory());
        factoryMap.put("Console", new ConsoleFactory());
        factoryMap.put("Electronic Book", new ElectronicBookFactory());
        factoryMap.put("Notebook", new NotebookFactory());
        factoryMap.put("Phone", new PhoneFactory());
        factoryMap.put("Watch", new WatchFactory());
    }
}
