package objectsToCollide;

import java.awt.*;

public class Trap1 extends FallingObject {

    public Trap1(double positionX, double positionY) {
        super(positionX, positionY);
        onCollideDestroy = true;
        width = 30;
        height = 30;
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.MAGENTA);
        super.draw(g);
    }

    @Override
    public void interactRocket(Rocket rocket) {
        rocket.collide();
    }

    @Override
    public void update(){
        return;
    }
}
