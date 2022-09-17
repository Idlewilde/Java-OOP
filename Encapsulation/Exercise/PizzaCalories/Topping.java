package PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if(!toppingType.equals("Meat")
                &&!toppingType.equals("Veggies")
                &&!toppingType.equals("Cheese")
                &&!toppingType.equals("Sauce")){
            throw new IllegalArgumentException("Cannot place "+toppingType+" on top of your pizza.");
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {

        if(weight<1||weight>50){throw new IllegalArgumentException(this.toppingType+" weight should be in the range [1..50].");}

        this.weight = weight;
    }

    public double calculateCalories(){
        double caloriesTopping = this.weight*2;
        switch (toppingType){
            case "Meat":
                caloriesTopping=caloriesTopping*1.2;
                break;
            case "Veggies":
                caloriesTopping=caloriesTopping*0.8;
                break;
            case "Sauce":
                caloriesTopping=caloriesTopping*0.9;
                break;
            case "Cheese":
                caloriesTopping=caloriesTopping*1.1;
                break;
        }
        return caloriesTopping;
    }
}
