package ledza.factory;

import ledza.devices.Device;
import ledza.devices.Phone;

public class PhoneFactory implements AbstractFactory{
    @Override
    public Device createObj() {
        return new Phone();
    }
}
