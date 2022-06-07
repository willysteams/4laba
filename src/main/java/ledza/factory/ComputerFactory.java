package ledza.factory;

import ledza.devices.Computer;
import ledza.devices.Device;

public class ComputerFactory implements AbstractFactory {

    @Override
    public Device createObj() {
        return new Computer();
    }
}
