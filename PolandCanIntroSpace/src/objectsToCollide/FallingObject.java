package objectsToCollide;

import mainPackage.ISpawnedObject;

import java.awt.*;


public abstract class FallingObject extends ObjectWithCollision implements  ISpawnedObject  {

    protected boolean onCollideDestroy;

    public FallingObject(double positionX, double positionY) {
        super(positionX, positionY);
    }

    public void draw(Graphics2D g){
        super.draw(g);
    }

    public boolean isOnCollideDestroy() {
        return onCollideDestroy;
    }

    public Rectangle returnRectangle(){
        return super.returnRectangle();
    }
}
