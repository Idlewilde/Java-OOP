package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Aquarium aquarium;

    @Before
    public void setUp() {
        this.aquarium = new Aquarium("Dzen", 50);
    }

    @Test
    public void getName() {
        Assert.assertEquals("Dzen", this.aquarium.getName());
    }

    @Test
    public void setNameSuccess() {
        Aquarium aquarium = new Aquarium("Test", 1);
        Assert.assertEquals("Test", aquarium.getName());

    }

    @Test(expected = NullPointerException.class)
    public void setNameNull() {
        Aquarium aquarium = new Aquarium(" ", 1);

    }

    @Test
    public void testGetCapacity (){
        Assert.assertEquals(50,aquarium.getCapacity());
    }

    @Test
    public void testSetCapacity(){
        Assert.assertEquals(50,new Aquarium("test",50).getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityFail() {
        Aquarium aquarium = new Aquarium("Test", -1);

    }

    @Test
    public void testGetCount(){
        Fish fish = new Fish("Test");
        this.aquarium.add(fish);
        Assert.assertEquals(this.aquarium.getCount(),1);
    }

    @Test
    public void testAddFish(){
        Fish fish = new Fish("Test");
        this.aquarium.add(fish);
        Assert.assertEquals(this.aquarium.getCount(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishFail(){
        Aquarium aquarium = new Aquarium("Test",1);
        Fish fish = new Fish("Test");
        Fish fish2 = new Fish("Test1");
        aquarium.add(fish);
        aquarium.add(fish2);

    }

    @Test
    public void removeFish(){
        Aquarium aquarium = new Aquarium("Test",5);
        Fish fish = new Fish("Test");
        Fish fish2 = new Fish("Rest");
        aquarium.add(fish);
        aquarium.add(fish2);
        Assert.assertEquals(2, aquarium.getCount());
        aquarium.remove("Rest");
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFail (){
        aquarium.remove("Bla");
    }

    @Test
    public void testSellFishSuccess(){
        Fish test = new Fish("Test");
       this.aquarium.add(test);
       Fish fish = this.aquarium.sellFish("Test");
       Assert.assertEquals(fish,test);
       Assert.assertFalse(fish.isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFail(){
        this.aquarium.sellFish("Lala");
    }

    @Test
    public void testReport(){
        Fish fish1 = new Fish("Sharky");
        Fish fish2 = new Fish("Perko");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        String expected = "Fish available at Dzen: Sharky, Perko";
        Assert.assertEquals(expected,this.aquarium.report());

    }





}

