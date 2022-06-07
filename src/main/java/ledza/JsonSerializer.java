package ledza;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ledza.devices.*;

import java.io.*;
import java.io.Console;
import java.util.*;

public class JsonSerializer {

    private static volatile JsonSerializer singleton;

    public Map<String, Class> typesToTypeMap;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String serialize(Device obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public Object deserialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Object.class);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String toJsonString(List<Device> devices) throws IOException {
        return objectMapper.writeValueAsString(devices);
    }

    public List<Device> loadDevicesFromJson(byte[] bytes) throws IOException {

        ArrayNode jsonNodes = (ArrayNode) objectMapper.readTree(bytes);
        Iterator<JsonNode> iterator = jsonNodes.iterator();

        List<Device> devices = new ArrayList<>();

        while (iterator.hasNext()){
            JsonNode jsonNode = iterator.next();
            String type = jsonNode.get("type").asText();
            Device device = (Device) objectMapper.convertValue(jsonNode, typesToTypeMap.get(type));

            devices.add(device);
        }
        return devices;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private void fillJsonSerializer(){
        typesToTypeMap = new HashMap<>();
        typesToTypeMap.put("Computer", Computer.class);
        typesToTypeMap.put("Console", Console.class);
        typesToTypeMap.put("Electronic Book", ElectronicBook.class);
        typesToTypeMap.put("Notebook", Notebook.class);
        typesToTypeMap.put("Phone", Phone.class);
        typesToTypeMap.put("Watch", Watch.class);
    }

    private JsonSerializer() {
        fillJsonSerializer();
    }

    public static JsonSerializer getInstance(){
        if (singleton == null){
            synchronized (JsonSerializer.class){
                if (singleton == null){
                    singleton = new JsonSerializer();
                }
            }
        }
        return singleton;
    }

}
