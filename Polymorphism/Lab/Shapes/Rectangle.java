package Shapes;

public class Rectangle extends Shape{
    private Double height;
    private Double width;

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
        this.calculateArea();
        this.calculatePerimeter();
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(this.height*2+this.width*2);
    }

    @Override
    protected void calculateArea() {
        setArea(this.height*this.width);
    }
}
