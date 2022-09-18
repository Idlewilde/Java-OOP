package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputerManagerTests {

    ComputerManager computerManager;
    Computer computer;
    List<Computer> computers;

    @Before

    public void setUp(){
        this.computerManager=new ComputerManager();
        this.computer=new Computer("Lenovo", "ThinkPad" , 722.11);
        Computer comp1 = new Computer("Dell", "ZXY" , 832.11);
        Computer comp2 = new Computer("HP", "DEW" , 1832.11);
        Computer comp3 = new Computer("ASUS", "QAZ" , 532.11);
        Computer comp4 = new Computer("ACER", "CVF" , 2032.11);
        Computer comp5 = new Computer("SAMSUNG", "FRT" , 232.11);
        this.computers=new ArrayList<>(Arrays.asList(comp1,comp2,comp3,comp4,comp5));
        this.computerManager.addComputer(comp1);
        this.computerManager.addComputer(comp2);
        this.computerManager.addComputer(comp3);
        this.computerManager.addComputer(comp4);
        this.computerManager.addComputer(comp5);
    }

    @Test

    public void testGetComputers(){
        Assert.assertEquals(computers,computerManager.getComputers());
        Assert.assertEquals(computers.size(),computerManager.getComputers().size());
    }

    @Test(expected = UnsupportedOperationException.class)

    public void testGetComputersUnmodifiable(){
        computerManager.getComputers().clear();
    }

    @Test

    public void testGetComputersCount(){
        Assert.assertEquals(computerManager.getCount(),computers.size(),computerManager.getComputers().size());
        computerManager.addComputer(this.computer);
        Assert.assertEquals(computerManager.getCount(),computers.size()+1);
    }

    @Test

    public void testAddComputerSuccess(){
        int countBefore = computerManager.getCount();
        computerManager.addComputer(this.computer);

        Assert.assertTrue(computerManager.getComputers().contains(this.computer));
        int countAfter = computerManager.getCount();
        Assert.assertEquals(countAfter,countBefore+1);


    }

    @Test(expected = IllegalArgumentException.class)

    public void testAddExisting(){
        computerManager.addComputer(this.computer);
        computerManager.addComputer(this.computer);
    }

    @Test

    public void testRemoveComputerSuccess(){
        computerManager.addComputer(this.computer);
        int countBefore = computerManager.getCount();
        Computer removed = computerManager.removeComputer("Lenovo","ThinkPad");
        int countAfter = computerManager.getCount();
        Assert.assertEquals(countAfter,countBefore-1);
        Assert.assertEquals(this.computer,removed);
        Assert.assertFalse(computerManager.getComputers().contains(removed));
    }

    @Test

    public void getComputerSuccess(){
        computerManager.addComputer(this.computer);
      Assert.assertEquals(this.computer,computerManager.getComputer("Lenovo","ThinkPad"));
    }

    @Test(expected = IllegalArgumentException.class)

    public void getNotExistingComputer(){
        computerManager.getComputer("Blabla","Bla");
    }

    @Test(expected = IllegalArgumentException.class)

    public void getListByManufacturerNull(){
        computerManager.getComputersByManufacturer(null);
    }

    @Test

    public void getListByManufacturer(){
        computerManager.addComputer(this.computer);
        List <Computer> Lenovo = computerManager.getComputersByManufacturer("Lenovo");
        Assert.assertTrue(Lenovo.size()==1);
        Assert.assertTrue(Lenovo.contains(this.computer));
    }


}