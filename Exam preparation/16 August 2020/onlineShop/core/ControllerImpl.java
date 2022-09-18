package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.BaseComputer;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map <Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components=new LinkedHashMap<>();
        this.peripherals=new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if(computers.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);}
        Computer computer;
        switch (computerType){
            case "DesktopComputer":
                computer = new DesktopComputer(id,manufacturer,model,price);
                computers.put(id,computer);
                return String.format(OutputMessages.ADDED_COMPUTER,id);
            case "Laptop":
                computer = new Laptop(id,manufacturer,model,price);
                computers.put(id,computer);
                return String.format(OutputMessages.ADDED_COMPUTER,id);
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if(!computers.containsKey(computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}

        if(peripherals.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);}
        Peripheral peripheral;
        switch (peripheralType){
            case "Headset":
                peripheral = new Headset(id,manufacturer,model,price,overallPerformance,connectionType);
                peripherals.put(id,peripheral);
                computers.get(computerId).addPeripheral(peripheral);
                return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                peripherals.put(id,peripheral);
                computers.get(computerId).addPeripheral(peripheral);
                return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
            case "Monitor":
                peripheral = new Monitor(id,manufacturer,model,price, overallPerformance, connectionType);
                peripherals.put(id,peripheral);
                computers.get(computerId).addPeripheral(peripheral);
                return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
            case "Mouse":
                peripheral = new Mouse(id,manufacturer,model,price, overallPerformance, connectionType);
                peripherals.put(id,peripheral);
                computers.get(computerId).addPeripheral(peripheral);
                return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if(!computers.containsKey(computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}

        if(components.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);}
        Component component;
        switch (componentType){
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id,manufacturer,model,price,overallPerformance,generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            case "PowerSupply":
                component = new PowerSupply(id,manufacturer,model,price, overallPerformance, generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id,manufacturer,model,price, overallPerformance, generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            case "SolidStateDrive":
                component = new SolidStateDrive(id,manufacturer,model,price, overallPerformance, generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            case "VideoCard":
                component = new VideoCard(id,manufacturer,model,price, overallPerformance, generation);
                components.put(id,component);
                computers.get(computerId).addComponent(component);
                return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
    }

    ///until here it's done

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if(!computers.containsKey(computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}
        Peripheral toBeRemoved=null;
        Computer computer = computers.get(computerId);
        for (Peripheral peripheral : computer.getPeripherals()) {
            if(peripheral.getClass().getSimpleName().equals(peripheralType)){toBeRemoved=peripheral;}
        }
        if(toBeRemoved==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,peripheralType,computer.getClass().getSimpleName(),computerId));}
        computer.removePeripheral(peripheralType);
        peripherals.remove(toBeRemoved.getId());
        return String.format(OutputMessages.REMOVED_PERIPHERAL,peripheralType,toBeRemoved.getId());
    }


    @Override
    public String removeComponent(String componentType, int computerId) {
        if(!computers.containsKey(computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}
        Component toBeRemoved=null;
        Computer computer = computers.get(computerId);
        for (Component component : computer.getComponents()) {
            if(component.getClass().getSimpleName().equals(componentType)){toBeRemoved=component;}
        }
        if(toBeRemoved==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,componentType,computer.getClass().getSimpleName(),computerId));}
        computer.removeComponent(componentType);
        components.remove(toBeRemoved.getId());
        return String.format(OutputMessages.REMOVED_COMPONENT,componentType,toBeRemoved.getId());
    }

    @Override
    public String buyComputer(int id) {
        if(!computers.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}
        Computer computer = computers.get(id);
        computers.remove(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> withinBudget = new ArrayList<>();
        withinBudget = computers.values().stream().filter(e->e.getPrice()<=budget).sorted((e1,e2)->Double.compare(e2.getOverallPerformance(), e1.getOverallPerformance())).collect(Collectors.toList());
        if(withinBudget.isEmpty()){throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER,budget));}
        Computer toBeRemoved = withinBudget.get(0);
        computers.remove(toBeRemoved.getId());
        return toBeRemoved.toString();

    }

    @Override
    public String getComputerData(int id) {
        if(!computers.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);}

        return computers.get(id).toString();
    }
}
