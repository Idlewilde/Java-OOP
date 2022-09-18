package catHouse.entities.cat;

public abstract class BaseCat implements Cat{
    private String name;
    private String breed;
    private int kilograms;
    private double price;

    protected BaseCat(String name, String breed, double price) {
        setName(name);
        setBreed(breed);
        setPrice(price);
    }

    @Override
    public void setName(String name) {
        if(name==null||name.trim().isEmpty()){
            throw new NullPointerException("Cat name cannot be null or empty.");}
        this.name = name;
    }

    protected void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    private void setBreed(String breed) {
        if(breed==null||breed.trim().isEmpty()){
            throw new NullPointerException("Cat breed cannot be null or empty.");}
        this.breed = breed;
    }

    private void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException("Cat price cannot be below or equal to 0.");}
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void eating (){}
}
