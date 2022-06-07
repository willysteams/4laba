package ledza.devices;

import java.util.List;

public class Watch extends Device{

    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Watch() {
        setType("Watch");
    }

    @Override
    public String toString() {
        return "Watch{" +
                "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                ", owner='" + owner + '\'' +
                "}\n";
    }

    @Override
    public List<String> getAllFields() {
        List<String> res = super.getAllFields();
        res.add("owner");
        return res;
    }

    @Override
    public void setAllFields(List<String> fieldsValues) {
        super.setAllFields(fieldsValues);
        Integer size = super.getAllFields().size();

        owner = !fieldsValues.get(size).strip().equals("") ? fieldsValues.get(size) : owner;
    }
}
