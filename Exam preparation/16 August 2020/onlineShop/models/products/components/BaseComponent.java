package onlineShop.models.products.components;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {

    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance,int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation=generation;
    }

    @Override
    public int getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(String.format(OutputMessages.COMPONENT_TO_STRING,generation));
        return sb.toString().trim();
    }
}
