package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GarageTests {

    private Garage garage;
    private List <Car> cars;

    @Before
    public void setUp(){
        garage=new Garage();
        Car car1 = new Car("a",1,1.1);
        Car car2 = new Car("a",11,12.1);
        Car car3 = new Car("b",1111,15.1);
        Car car4 = new Car("b",222,16.1);
        cars =Arrays.asList(car1,car2,car3,car4);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        garage.addCar(car4);
    }

    @Test
    public void testGetCars(){
        Assert.assertEquals(garage.getCars(),cars);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCarsClear(){
        garage.getCars().clear();
    }

    @Test
    public void testGetCount(){
        Assert.assertEquals(garage.getCount(),cars.size());
        garage.addCar(new Car("d",5,5));
        Assert.assertEquals(garage.getCount(),cars.size()+1);
    }

    @Test
    public void testMaxSpeed(){
        List <Car> max = cars.stream().filter(e->e.getMaxSpeed()>100).collect(Collectors.toList());
        Assert.assertEquals(max,garage.findAllCarsWithMaxSpeedAbove(100));
        for (Car car : garage.findAllCarsWithMaxSpeedAbove(100)) {
            Assert.assertTrue(car.getMaxSpeed()>100);
        }


    }

    @Test
    public void addCar(){
        Car car = new Car("a1",1,1);
        garage.addCar(car);
        Assert.assertTrue(garage.getCars().contains(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCarFail(){

        garage.addCar(null);

    }

    @Test
    public void mostExpensive(){
        Car car = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car,garage.getCars().stream().sorted((e2,e1)->Double.compare(e1.getPrice(),e2.getPrice())).limit(1).findFirst().orElse(null));
        Garage empty = new Garage();
        Assert.assertEquals(empty.getTheMostExpensiveCar(),null);
    }

    @Test
    public void byBrand(){
        List <Car> a = garage.findAllCarsByBrand("a");
        Assert.assertEquals(a,cars.stream().filter(e->e.getBrand().equals("a")).collect(Collectors.toList()));
    }





}