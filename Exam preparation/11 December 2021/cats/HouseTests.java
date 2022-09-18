package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HouseTests {

    private House house;
    private List<Cat> cats;

    @Before

    public void setUp () {
        this.house = new House("murrdor",10);
        this.cats = new ArrayList<>();
        Cat Tom = new Cat("Tom");
        Cat Puffy = new Cat("Puffy");
        Cat Bob = new Cat("Bob");
        Cat Gremy = new Cat("Gremy");
        Cat Chrissy = new Cat("Chrissy");
        Cat Chocho = new Cat("Chocho");
        Cat Donka = new Cat("Donka");
        cats.add(Tom);
        cats.add(Puffy);
        cats.add(Bob);
        cats.add(Gremy);
        cats.add(Chrissy);
        cats.add(Chocho);
        cats.add(Donka);
        int count = 0;
        for (Cat cat : cats) {
           this.house.addCat(cats.get(count)); count++;
        }
    }


    @Test
    public void testGetName () {
        Assert.assertEquals(this.house.getName(),"murrdor");
    }

    @Test
    public void testGetCapacity () {
        Assert.assertEquals(this.house.getCapacity(),10);
    }

    @Test (expected = NullPointerException.class)
    public void testSetNameFail () {
        House wrongNameHouse = new House(null,5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetCapacityFail () {
        House wrongSizeHouse = new House("catHouse",-1);
    }

    @Test
    public void testGetCount () {
        Assert.assertEquals(this.house.getCount(),cats.size());
    }

    @Test
    public void testAddCatSuccess () {
        Cat Tonya = new Cat("Tonya");
        this.house.addCat(Tonya);
        cats.add(Tonya);
        Assert.assertEquals(cats.size(),house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)

    public void testAddCatFail () {
       House noSpace = new House("noSpace",0);
       noSpace.addCat(new Cat("Kitty"));
    }

    @Test
    public void testRemoveCatSuccess () {
        Cat Tonya = new Cat("Tonya");
        this.house.addCat(Tonya);
        this.house.removeCat("Tonya");
        Assert.assertEquals(cats.size(),house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)

    public void testRemoveCatFail () {
        this.house.removeCat("Tonya");
    }

    @Test(expected = IllegalArgumentException.class)

    public void testCatForSaleFail () {
        this.house.catForSale("Tonya");
    }

    @Test

    public void testCatForSaleSuccess () {
        this.house.catForSale("Tom");
        Assert.assertEquals(this.house.catForSale("Tom"),cats.get(0));
        Assert.assertFalse(this.house.catForSale("Tom").isHungry());
    }

    @Test

    public void testGetStatistics () {
        List <String> catNames = cats.stream().map(Cat::getName).collect(Collectors.toList());
        String catsWithNames = String.join(", ",catNames);
        Assert.assertEquals(this.house.statistics(),String.format("The cat %s is in the house murrdor!",catsWithNames));
    }







}
