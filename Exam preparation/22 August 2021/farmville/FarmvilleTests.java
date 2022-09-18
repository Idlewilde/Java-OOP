package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FarmvilleTests {
    private Farm farm;
    private List<Animal> animals;

    @Before

    public void setUp(){
        this.farm=new Farm("Farm",10);
        Animal cow = new Animal("cow",10.2);
        Animal rabbit = new Animal("rabbit",20.2);
        Animal dog = new Animal("dog",6.2);
        Animal cat = new Animal("cat",5);
        Animal sheep = new Animal("sheep",40.7);
        Animal pig = new Animal("pig",0.2);
        Animal horse = new Animal("horse",6.1);
        this.animals= Arrays.asList(cow,rabbit,dog,cat,sheep,pig,horse);
        for (Animal animal : animals) {
            this.farm.add(animal);
        }
    }

    @Test
    public void testCount(){
        Assert.assertEquals(this.farm.getCount(),this.animals.size());
    }

    @Test
    public void testGetName(){
        Assert.assertEquals(this.farm.getName(),"Farm");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFail(){
        Farm farm = new Farm ("noSpace",0);
        farm.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFailExists(){
        this.farm.add(new Animal("cow",5));
    }
@Test
    public void testRemoveTrue(){
        Assert.assertTrue(this.farm.remove("cat"));
}

    @Test
    public void testRemoveFalse(){
        Assert.assertFalse(this.farm.remove("monkey"));

    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWrongValue(){
Farm farm = new Farm(null,1);    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityWrongValue(){
        Farm farm = new Farm("NoSpace",-1);    }
}
