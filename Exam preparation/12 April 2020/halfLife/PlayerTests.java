package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {

    private Player player;
    private Gun gun;

    @Before

    public void setUp (){
        this.player=new Player("Tonya",10);
        this.gun=new Gun("Colt",50);
    }

    @Test

    public void testGetUsername () {
        Assert.assertEquals("Tonya",this.player.getUsername());
    }

    @Test

    public void testGetHealth () {
        Assert.assertEquals(10,this.player.getHealth());
    }

    @Test

    public void testSetUsername () {
        Player player1= new Player("X",1);
        Assert.assertEquals("X",player1.getUsername());
    }

    @Test(expected = NullPointerException.class)

    public void testSetUsernameNull () {
        Player player = new Player(null,1);
    }

    @Test

    public void testSetHealth() {
        Player player1= new Player("X",1);
        Assert.assertEquals(1,player1.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)

    public void testSetHealthFail () {
        Player player = new Player("X",-1);
    }

    @Test

    public void testGetGuns () {
        player.addGun(gun);
        Assert.assertTrue(player.getGuns().contains(gun));
        Assert.assertEquals(1,player.getGuns().size());
    }

    @Test(expected = UnsupportedOperationException.class)

    public void testGetGunsClearList () {
        player.getGuns().clear();
    }

    @Test(expected = IllegalStateException.class)

    public void testTakeDamageDeadBeforeAttack () {
        player.takeDamage(10);
        player.takeDamage(1);
    }

    @Test

    public void testTakeDamageDeadAfterAttack () {
        player.takeDamage(11);
        Assert.assertEquals(0,player.getHealth());
    }

    @Test

    public void testTakeDamageAliveAfterAttack () {
        player.takeDamage(5);
        Assert.assertEquals(5, player.getHealth(), 0.0);
    }


    @Test

    public void testAddGun () {
        player.addGun(gun);
        Assert.assertEquals(player.getGun("Colt"),gun);
        Assert.assertTrue(player.getGuns().contains(gun));
        Assert.assertEquals(player.getGuns().size(),1);
    }

    @Test(expected = NullPointerException.class)

    public void testAddGunNull () {
        player.addGun(null);
    }

    @Test

    public void testRemoveGunTrue () {
        player.addGun(gun);
        Assert.assertEquals(true,player.removeGun(gun));
        Assert.assertFalse(player.getGuns().contains(gun));
    }

    @Test

    public void testRemoveGunFalse () {
        Gun testGun = new Gun("Z",5);
        Assert.assertEquals(false,player.removeGun(testGun));
        Assert.assertFalse(player.getGuns().contains(testGun));
    }





}
