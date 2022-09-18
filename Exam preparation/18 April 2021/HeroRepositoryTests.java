package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HeroRepositoryTests {
    private HeroRepository data;

    @Before
    public void setUp() {
        this.data=new HeroRepository(); }

    @Test

    public void testGetCount (){
        Assert.assertEquals(this.data.getCount(),0);
        data.create(new Hero("XXX",5));
        Assert.assertEquals(this.data.getCount(),1);
    }

    @Test(expected = NullPointerException.class)

    public void testAddNull(){
        this.data.create(null);
    }

    @Test(expected = IllegalArgumentException.class)

    public void testAddHeroExists(){
    Hero hero = new Hero("Ragnar",10);
    this.data.create(hero);
    this.data.create(hero);
    }

    @Test

    public void testAddSuccess(){
    Hero marta = new Hero("Marta",50000);
    String expected = "Successfully added hero Marta with level 50000";
    Assert.assertEquals(data.create(marta),expected);
        Assert.assertEquals(data.getHero("Marta"),marta);
    }


    @Test(expected = NullPointerException.class)

    public void testRemoveNull(){
        data.remove(null);
    }

    @Test

    public void testRemoveTrue(){
        Hero marta = new Hero("Marta",50000);
        data.create(marta);
        Assert.assertTrue(data.remove("Marta"));
        Assert.assertFalse(data.getHeroes().contains(marta));
    }

    @Test

    public void testRemoveFalse(){
        Assert.assertFalse(data.remove("Zzzz"));
    }


    @Test

    public void testHeroHighestLevel(){
        Hero marta = new Hero("Marta",50000);
        Hero h1 = new Hero("ZZ",1);
        Hero h2 = new Hero("BB",2);
        Hero h3 = new Hero("AA",3);
        Hero h4 = new Hero("DD",4);
        data.create(marta);
        data.create(h1);
        data.create(h2);
        data.create(h3);
        data.create(h4);
        Assert.assertEquals(data.getHeroWithHighestLevel(),marta);
        data.remove(marta.getName());
        Assert.assertEquals(data.getHeroWithHighestLevel(),h4);
    }

    @Test

    public void testGetExistingHero(){
        Hero marta = new Hero("Marta",50000);
        data.create(marta);
        Assert.assertEquals(data.getHero("Marta"),marta);
    }

    @Test

    public void testGetMissingHero(){
        Assert.assertEquals(data.getHero("Xxx"),null);
    }

    @Test(expected = UnsupportedOperationException.class)

    public void testUnmodifiable(){
        data.getHeroes().clear();
    }


    @Test

    public void testGetHeroes(){
        Assert.assertEquals(data.getHeroes().size(),0);
        Hero h1 = new Hero("ZZ",1);
        Hero h2 = new Hero("BB",2);
        Hero h3 = new Hero("AA",3);
        Hero h4 = new Hero("DD",4);
        data.create(h1);
        data.create(h2);
        data.create(h3);
        data.create(h4);
        List<Hero> list = new ArrayList<>();
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
        Assert.assertTrue(data.getHeroes().containsAll(list));


    }

}
