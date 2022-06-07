package ledza.devices;

import java.util.List;

public class Phone extends Device{

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Phone() {
        setType("Phone");
    }

    @Override
    public String toString() {
        return "Phone{" +
                "type='" + getType() + '\'' +
                ", Storage=" + getStorage() +
                ", RAM=" + getRAM() +
                ", phoneNumber='" + phoneNumber + '\'' +
                "}\n";
    }

    @Override
    public List<String> getAllFields() {
        List<String> res =  super.getAllFields();
        res.add("phoneNumber");
        return res;
    }

    @Override
    public void setAllFields(List<String> fieldsValues) {
        super.setAllFields(fieldsValues);
        Integer size = super.getAllFields().size();

        try {
            phoneNumber = !fieldsValues.get(size).equals("") ? fieldsValues.get(size) : phoneNumber;
        }
        catch (Exception e){
            System.out.println("Error value");
        }
    }
}
