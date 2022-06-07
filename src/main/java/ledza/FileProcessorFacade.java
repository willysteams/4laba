package ledza;

import ledza.devices.Device;
import ledza.plugin.Plugin;
import org.apache.commons.codec.DecoderException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileProcessorFacade {

    private final JsonSerializer jsonSerializer = JsonSerializer.getInstance();

    public void saveInFile(List<Device> devices, Plugin plugin) throws IOException {

        String text = jsonSerializer.toJsonString(devices);

        if (plugin != null)
            text = plugin.getSignature() + ":" + plugin.encode(text);
        else
            text = "null:" + text;

        FileOutputStream file = new FileOutputStream("devices.json");
        file.write(text.getBytes(StandardCharsets.UTF_8));
        file.close();
    }

    public List<Device> loadFromFile(List<Plugin> existingPlugins) throws IOException, DecoderException {

        Plugin currentPlugin = null;

        FileInputStream file = new FileInputStream("devices.json");
        byte[] encodedBytes = file.readAllBytes();
        String encodedString = new String(encodedBytes);

        try {
            currentPlugin = choosePlugin(encodedString, existingPlugins);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        String decodedJson = encodedString.split(":", 2)[1];

        if (currentPlugin != null){
            decodedJson = currentPlugin.decode(decodedJson);
        }
        return jsonSerializer.loadDevicesFromJson(decodedJson.getBytes(StandardCharsets.UTF_8));
    }

    private String getSignature(String encodedString){
        return encodedString.split(":")[0];
    }

    private Plugin choosePlugin(String encodedString, List<Plugin> plugins) throws Exception {
        String signature = getSignature(encodedString);

        if (signature.equals("null"))
            return null;

        for (Plugin plugin: plugins){
            if (signature.equals(plugin.getSignature())){
                return plugin;
            }
        }

        throw new Exception("Unknown plugin was used");

    }

}
