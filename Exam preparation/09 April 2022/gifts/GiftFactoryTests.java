package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private List <Gift> gifts;
    Gift gift1;

    @Before

    public void setUp(){
        giftFactory=new GiftFactory();
        gift1=new Gift("doll",1);
        Gift gift2=new Gift("dog",10);
        Gift gift3=new Gift("dollar",100);
        List<Gift> gifts = Arrays.asList(gift1,gift2,gift3);
        this.giftFactory.createGift(gift1);
        this.giftFactory.createGift(gift2);
        this.giftFactory.createGift(gift3);
    }

    @Test

    public void testCount(){
        Assert.assertEquals(giftFactory.getCount(),3);
        giftFactory.removeGift("doll");
        Assert.assertEquals(giftFactory.getCount(),2);
    }

    @Test

    public void testCreationAddition(){
        Gift gift = new Gift("laptop", 5000);
        int getCountBefore = giftFactory.getCount();
        giftFactory.createGift(gift);
        Assert.assertEquals(giftFactory.getCount(),getCountBefore+1);
        Assert.assertTrue(giftFactory.getPresent("laptop")!=null);
        Assert.assertTrue(giftFactory.getPresents().contains(gift));
    }

    @Test

    public void testCreationString(){
        Gift gift = new Gift("laptop", 5000);
        Assert.assertEquals(giftFactory.createGift(gift),"Successfully added gift laptop with magic 5000.00.");
    }

    @Test(expected = IllegalArgumentException.class)

    public void testCreationException(){
        Gift gift = new Gift("dog",50);
        this.giftFactory.createGift(gift);
    }

    @Test(expected = NullPointerException.class)

    public void testRemoveNull(){
        this.giftFactory.removeGift("");
    }

    @Test

    public void testRemoveTrue(){
        int sizeBefore=this.giftFactory.getCount();
        boolean thisIsTrue = this.giftFactory.removeGift("doll");
        boolean thisIsFalse = this.giftFactory.removeGift("potato");
        Assert.assertTrue(thisIsTrue);
        Assert.assertFalse(thisIsFalse);
        Assert.assertFalse(this.giftFactory.getPresents().contains(gift1));
        Assert.assertEquals(this.giftFactory.getCount(),sizeBefore-1);
    }

    @Test

    public void testLeastMagicOk(){
        Assert.assertEquals(this.giftFactory.getPresentWithLeastMagic(),gift1);
    }

    @Test

    public void testLeastMagicNull(){
        GiftFactory giftFactoryNull=new GiftFactory();
        Assert.assertNull(giftFactoryNull.getPresentWithLeastMagic());
    }

    @Test

    public void testGetPresentOk(){
        Assert.assertEquals(this.giftFactory.getPresent("doll"),gift1);
    }

    @Test

    public void testGetPresentNull(){
        Assert.assertNull(giftFactory.getPresent("potato"));
    }

    @Test(expected = UnsupportedOperationException.class)

    public void testClearList(){
        giftFactory.getPresents().clear();
    }

 




}
