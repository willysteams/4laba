package ledza.devices;

public class ElectronicBook extends Device{

    public ElectronicBook() {
        setType("Electronic Book");
    }

    @Override
    public String toString() {
        return "Console{" +
                "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                "}\n";    }
}
