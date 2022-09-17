package DiscountCalculator;

public class PriceCalculator {
    public static double calculatePrice (double price, int nights, Season season, Discount discount ){
        double priceBeforeDiscount = price*nights* season.getValue();
        double discountSum = priceBeforeDiscount*(discount.getValue()/100.0);
        return priceBeforeDiscount-discountSum;
    }}

