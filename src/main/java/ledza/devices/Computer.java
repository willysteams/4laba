package ledza.devices;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Device{

    private boolean isThereGPU;

    public boolean isThereGPU() {
        return isThereGPU;
    }

    public void setThereGPU(boolean thereGPU) {
        isThereGPU = thereGPU;
    }

    public Computer() {
        setType("Computer");
    }

    @Override
    public String toString() {
        return "Computer{" +
                 "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                ", isThereGPU=" + isThereGPU +
                "}\n";
    }

    @Override
    public List<String> getAllFields() {
        List<String> res = super.getAllFields();
        res.add("isThereGPU");
        return res;
    }

    @Override
    public void setAllFields(List<String> fieldsValues) {
        super.setAllFields(fieldsValues);
        Integer size = super.getAllFields().size();

        isThereGPU = !fieldsValues.get(size).strip().equals("") ? Boolean.parseBoolean(fieldsValues.get(size).strip()) : isThereGPU;
    }
}
