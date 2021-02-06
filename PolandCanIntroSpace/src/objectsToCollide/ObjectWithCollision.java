package objectsToCollide;

import java.awt.*;

public abstract class ObjectWithCollision {

    protected double initialPositionY;
    protected double positionX;
    protected double positionY;
    protected int width, height;

    public ObjectWithCollision(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.initialPositionY = positionY;
    }

    public void draw(Graphics2D g){
        g.fillRect((int)positionX, (int)positionY, width, height);
    }

    public double getInitialPositionY() {
        return initialPositionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double y) {
        this.positionY = y;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle returnRectangle(){
        return new Rectangle((int)positionX, (int)positionY, width, height);
    }
}
