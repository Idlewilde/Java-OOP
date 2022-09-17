package Shapes;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        this.calculateArea();
        this.calculatePerimeter();
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    protected void calculatePerimeter() {
        setPerimeter(this.radius*2*Math.PI);
    }

    @Override
    protected void calculateArea() {
        setArea(Math.PI*this.radius*this.radius);
    }
}
