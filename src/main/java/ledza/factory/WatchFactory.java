package ledza.factory;

import ledza.devices.Device;
import ledza.devices.Watch;

public class WatchFactory implements AbstractFactory{
    @Override
    public Device createObj() {
        return new Watch();
    }
}
