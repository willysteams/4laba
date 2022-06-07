package ledza.devices;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Device {

    private String type;
    private Integer Storage;
    private Integer RAM;


    public Integer getStorage() {
        return Storage;
    }

    public void setStorage(Integer storage) {
        Storage = storage;
    }

    public Integer getRAM() {
        return RAM;
    }

    public void setRAM(Integer RAM) {
        this.RAM = RAM;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "type='" + type + '\'' +
                ", Storage=" + Storage +
                ", RAM=" + RAM ;
    }

    public void change(){
        System.out.println("CHANGING!!!");
    }

    @JsonIgnore
    public List<String> getAllFields(){
        List<String> res = new ArrayList<>();
        res.add("Storage");
        res.add("RAM");
        return res;
    }

    public void setAllFields(List<String> fieldsValues){
        try{
            Storage = !fieldsValues.get(0).strip().equals("") ? Integer.parseInt(fieldsValues.get(0).strip()) : Storage;
            RAM = !fieldsValues.get(1).strip().equals("") ? Integer.parseInt(fieldsValues.get(1).strip()) : RAM;
        }
        catch (Exception e){
            System.out.println("Error value");

        }
    }
}

