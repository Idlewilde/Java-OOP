package viceCity.models.guns;

public class Rifle extends BaseGun{
    public Rifle(String name) {
        super(name, 50, 500);
    }


    @Override
    public int fire() {
        if (getBulletsPerBarrel() ==0&&getTotalBullets()>0) {
                setTotalBullets(getTotalBullets()-50);setBulletsPerBarrel(50);}
       else if(getBulletsPerBarrel()>0){setBulletsPerBarrel(getBulletsPerBarrel() - 5);return 5;}
       return 5;
    }
}
