package PointInRectangle;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean Contains(int x,int y){
        if(x>=this.bottomLeft.getX()
                &&x<=this.topRight.getX()
                &&y>=this.bottomLeft.getY()
                &&y<=this.topRight.getY()){return true;}
        return false;
    }
}
