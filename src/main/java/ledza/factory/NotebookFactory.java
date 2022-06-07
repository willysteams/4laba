package ledza.factory;

import ledza.devices.Device;
import ledza.devices.Notebook;

public class NotebookFactory implements AbstractFactory{
    @Override
    public Device createObj() {
        return new Notebook();
    }
}
