package ledza.factory;

import ledza.devices.Console;
import ledza.devices.Device;

public class ConsoleFactory implements AbstractFactory{
    @Override
    public Device createObj() {
        return new Console();
    }
}
