package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {
    private Shop shop;
    private Goods good;
    private Goods good1;


    @Before
    public void setUp(){
        this.shop=new Shop();
         this.good = new Goods("Beans","FESD874");
         this.good1 = new Goods("Salad","HFDS879");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldReturnUnmodifiableCollection() {
        this.shop.getShelves().clear();
    }


    @Test
    public void testGetShelves () {
        Assert.assertEquals(12,shop.getShelves().size());
        Assert.assertNull(this.shop.getShelves().get("Shelves1"));
    }

    @Test
    public void testAddGoods () throws OperationNotSupportedException {
        String result = shop.addGoods("Shelves12",good);
        String expected = "Goods: FESD874 is placed successfully!";
        Assert.assertEquals(result,expected);
        Assert.assertEquals(shop.getShelves().get("Shelves12"),good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsFailShelfNull () throws OperationNotSupportedException {
        shop.addGoods("Shelves15",good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsFailShelfTaken () throws OperationNotSupportedException {
        shop.addGoods("Shelves11",good);
        shop.addGoods("Shelves11",good1);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsFailGoodsArranged () throws OperationNotSupportedException {
        shop.addGoods("Shelves12",good);
        shop.addGoods("Shelves11",good);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShelfNonExistent () throws OperationNotSupportedException {
        shop.removeGoods("Shelves15",good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsGoodsNotOnShelf () throws OperationNotSupportedException {
        Goods goods = new Goods("Beans","FESD874");
        Goods goods2 = new Goods("Salad","HFDS879");
        shop.addGoods("Shelves12",goods);
        shop.removeGoods("Shelves12",goods2);
    }

    @Test
    public void testRemoveSuccess () throws OperationNotSupportedException {
        shop.addGoods("Shelves12",good);
        String result = shop.removeGoods("Shelves12",good);
        Assert.assertNull(shop.getShelves().get("Shelves12"));
        Assert.assertEquals(result,"Goods: FESD874 is removed successfully!");
            }

    @Test
    public void testGetName () throws OperationNotSupportedException {
        shop.addGoods("Shelves12",good);
        Assert.assertEquals("Beans",shop.getShelves().get("Shelves12").getName());
    }





}