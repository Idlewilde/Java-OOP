package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            return super.getOverallPerformance() + components.stream().mapToDouble(Product::getOverallPerformance).average().getAsDouble();
        }
    }

    @Override
    public double getPrice() {

        return super.getPrice()
                + components.stream().mapToDouble(Product::getPrice).sum()
                + peripherals.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void addComponent(Component component) {
        List<Component> filtered = new ArrayList<>();
        filtered = components.stream().filter(e -> e.getClass().getSimpleName().equals(component.getClass().getSimpleName())).collect(Collectors.toList());
        if (filtered.size() > 0) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component toRemove = null;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                toRemove = component;
            }
        }
        if (toRemove == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), getId()));
        } else {
            components.remove(toRemove);
            return toRemove;
        }
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        List<Peripheral> filtered = new ArrayList<>();
        filtered = peripherals.stream().filter(e -> e.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())).collect(Collectors.toList());
        if (filtered.size() > 0) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral toRemove = null;
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                toRemove = peripheral;
            }
        }
        if (toRemove == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), getId()));
        } else {
            peripherals.remove(toRemove);
            return toRemove;
        }
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.PRODUCT_TO_STRING,
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer(), getModel(), getId()))
                .append(System.lineSeparator());
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, components.size())).append(System.lineSeparator());
        for (Component component : components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }
        double overall = 0;
        if (peripherals.size() > 0) {
            overall = this.peripherals.stream().mapToDouble(Product::getOverallPerformance).average().getAsDouble();
        }
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(), overall)).append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
