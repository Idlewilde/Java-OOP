package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;
import com.sun.source.tree.LiteralTree;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHouse implements House{
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection <Cat> cats;

    public BaseHouse(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.toys=new ArrayList<>();
        this.cats=new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        if(name==null||name.trim().isEmpty()){throw new NullPointerException("House name cannot be null or empty.");}
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }

    @Override
    public Collection<Cat> getCats() {
        return cats;
    }

    public int sumSoftness(){
        return getToys().stream().mapToInt(Toy::getSoftness).sum();
    }

    public void addCat(Cat cat){
        if(this.cats.size()>=this.capacity){throw new IllegalStateException("Not enough capacity for this cat.");}
        this.cats.add(cat);
    }

    public void removeCat(Cat cat){
        this.cats.remove(cat);
    }

    @Override
    public void buyToy (Toy toy){
        this.toys.add(toy);
    }

    public void feeding(){
        for (Cat cat : cats) {
            cat.eating();
        }
    }

    public String getStatistics(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s %s:",this.name,this.getClass().getSimpleName()))
                    .append(System.lineSeparator());
        if(cats.isEmpty()){stringBuilder.append("Cats: none");}
        else{stringBuilder.append("Cats: ");
            for (Cat cat : cats) {
                stringBuilder.append(cat.getName()).append(" ");
            }}
        String soFar =stringBuilder.toString().trim();
        StringBuilder two = new StringBuilder(soFar);
            two.append(System.lineSeparator())
                    .append("Toys: ")
                    .append(toys.size())
                    .append(" Softness: ")
                    .append(sumSoftness())
                    .append(System.lineSeparator());
            return two.toString().trim();
    }
}
