package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Collection <Astronaut> crew;


    @Before
    public void setUp(){
        this.spaceship=new Spaceship("Galactica",15);

        Astronaut astronaut1 = new Astronaut("Kara Thrace",10.2);
        Astronaut astronaut2 = new Astronaut("Captain Adama",11.2);
        Astronaut astronaut3 = new Astronaut("N6",15.2);
        Astronaut astronaut4 = new Astronaut("Lee Adama",20.7);
        Astronaut astronaut5 = new Astronaut("Gaius Baltar",50.8);
        Astronaut astronaut6 = new Astronaut("Laura Roslin",70.3);
        Astronaut astronaut7 = new Astronaut("Boomer",10.0);
        this.crew = Arrays.asList(astronaut1,astronaut2,astronaut3,astronaut4,astronaut5,astronaut6,astronaut7);
        for (Astronaut astronaut : crew) {
            this.spaceship.add(astronaut);
        }
    }

    @Test

    public void testGetCount () {
        Assert.assertEquals(crew.size(),this.spaceship.getCount());
    }

    @Test

    public void testGetName() {
        Assert.assertEquals("Galactica",this.spaceship.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFail(){
        Spaceship spaceship = new Spaceship("NotEnoughSpace",0);
        Astronaut astronaut=new Astronaut("Tonya",10.00);
        spaceship.add(astronaut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFailAstronautExists(){
        Astronaut astronaut=new Astronaut("Kara Thrace",10.2);
        this.spaceship.add(astronaut);
    }

    @Test
    public void testRemoveSuccess(){
        this.spaceship.remove("Kara Thrace");
        Assert.assertTrue(this.spaceship.getCount()==6);
    }

    @Test
    public void testRemoveFail(){
        this.spaceship.remove("Chochko");
        Assert.assertFalse(this.spaceship.remove("Chochko"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityFail(){
        Spaceship spaceship = new Spaceship("nono",-1);
    }


    @Test(expected = NullPointerException.class)
    public void testNameFail(){
        Spaceship spaceship = new Spaceship("",5);
    }
}
