package ledza.devices;

import java.util.List;

public class Notebook extends Computer{
    private Integer batteryPower;

    public Integer getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(Integer batteryPower) {
        this.batteryPower = batteryPower;
    }

    public Notebook() {
        this.setType("Notebook");
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                ", isThereGPU=" + isThereGPU() +
                ", batteryPower=" + batteryPower +
                "}\n";
    }

    @Override
    public List<String> getAllFields() {
        List<String> res = super.getAllFields();
        res.add("batteryPower");
        return res;
    }

    @Override
    public void setAllFields(List<String> fieldsValues) {
        super.setAllFields(fieldsValues);
        Integer size = super.getAllFields().size();

        batteryPower = !fieldsValues.get(size).equals("") ? Integer.parseInt(fieldsValues.get(size)) : batteryPower;
    }
}