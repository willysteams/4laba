package ledza.devices;

public class Console extends Device{

    public Console() {
        setType("Console");
    }

    @Override
    public String toString() {
        return "Console{" +
                "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                "}\n";
    }
}
