package ledza.factory;

import ledza.devices.Device;
import ledza.devices.ElectronicBook;

public class ElectronicBookFactory implements AbstractFactory{
    @Override
    public Device createObj() {
        return new ElectronicBook();
    }
}
