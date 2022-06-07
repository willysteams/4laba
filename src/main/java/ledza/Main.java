package ledza;

import ledza.devices.*;
import ledza.factory.AbstractFactory;
import ledza.factory.DeviceFactory;
import ledza.plugin.Plugin;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Plugin> loadPlugins(){

        Path pluginsDir = Paths.get("plugin");
        ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

        List<String> plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        ModuleLayer layer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        return Plugin.getServices(layer);


    }

    public static void getHelp(){
        System.out.println("Enter number:");
        System.out.println("1) Load from file");
        System.out.println("2) Save in file");
        System.out.println("3) Print List");
        System.out.println("4) Delete element from List");
        System.out.println("5) Add element to List");
        System.out.println("6) Edit element");
        System.out.println("7) Choose plugin");
        System.out.println("8) Get Help");
        System.out.println("9) Exit");
    }

    public static void deleteElement(List<Device> devices){
        for (int i =0;i<devices.size();i++){
            System.out.print(i+1 + ") ");
            System.out.print(devices.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose element number:");
        Integer num = Integer.parseInt(scanner.next());

        if (num > devices.size() || num < 1){
            System.out.println("Error number");
            return;
        }
        devices.remove(num-1);
        System.out.println("Successful deletion");
    }

    public static void addElement(List<Device> devices){

        System.out.println("Test");

        JsonSerializer jsonSerializer = JsonSerializer.getInstance();
        List<String> types = new ArrayList<>(jsonSerializer.typesToTypeMap.keySet());

        for (int i = 0; i<types.size();i++){
            System.out.println(i+1 + ") " + types.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        String choosedType;
        try{
            Integer num =  Integer.parseInt(scanner.next());
            choosedType = types.get(num-1);
        }
        catch (Exception e){
            System.out.println("Error number");
            return;
        }
        DeviceFactory deviceFactory = new DeviceFactory();
        AbstractFactory factory = deviceFactory.factoryMap.get(choosedType);
        Device device = factory.createObj();
        devices.add(device);
    }

    public static void editElement(List<Device> devices){
        for (int i = 0; i<devices.size();i++){
            System.out.println(i+1 + ") " + devices.get(i));
        }

        System.out.print("Choose device: ");
        Scanner scanner = new Scanner(System.in);

        Device device;
        try {
            Integer num = Integer.parseInt(scanner.nextLine().strip());
            device = devices.get(num-1);
        }
        catch (Error error){
            System.out.println("Error num");
            return;
        }

        List<String> values = new ArrayList<>();

        System.out.println(device.getAllFields());
        for (String field : device.getAllFields()){
            System.out.print(field + ": ");
            String temp = scanner.nextLine();
            values.add(temp);
        }
        device.setAllFields(values);
    }

    public static Plugin chosePlugin(List<Plugin> plugins){
        System.out.println("Choose plugin");
        for (int i = 0; i< plugins.size();i++){
            System.out.println(i+1 + ") " + plugins.get(i).getSignature());
        }
        Scanner scanner = new Scanner(System.in);
        Integer num = Integer.parseInt(scanner.nextLine().strip()) - 1;
        System.out.println("You choosed " + plugins.get(num).getSignature());
        return plugins.get(num);
    }

    public static void main(String[] args) throws IOException, DecoderException {

        FileProcessorFacade fileProcessor = new FileProcessorFacade();
        List<Device> devices = new ArrayList<>();

        Plugin plugin = null;
        List<Plugin> plugins = loadPlugins();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Start program!");
        Main.getHelp();

        while (true){
            System.out.print("Enter number: ");
            Integer number = Integer.parseInt(scanner.next());

            if (number == 1)
                devices = fileProcessor.loadFromFile(plugins);
            else if (number == 2) {
                fileProcessor.saveInFile(devices, plugin);
            } else if (number == 3)
                System.out.println(devices);
            else if (number == 4)
                deleteElement(devices);
            else if (number == 5)
                addElement(devices);
            else if (number == 6)
                editElement(devices);
            else if (number == 7){
                plugin = chosePlugin(plugins);
            }
            else if (number == 8)
                getHelp();
            else if (number == 9)
                break;
        }
    }
}
