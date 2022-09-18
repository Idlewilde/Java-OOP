package fairyShop.models;

import fairyShop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;


    public BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
        this.instruments=new ArrayList<>();
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    protected void setName(String name) {
        if(name==null||name.trim().isEmpty()){throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);}
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public void work() {
        this.energy=Math.max(this.energy-10,0);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy > 0;
    }
}
