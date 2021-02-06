package objectsToCollide;

import java.awt.*;

public class Accelerator extends FallingObject {


    public Accelerator(double positionX, double positionY) {
        super(positionX, positionY);
        onCollideDestroy = false;
        width = 50;
        height = 100;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.GRAY);
        super.draw(g);
    }

    @Override
    public void interactRocket(Rocket rocket) {
        rocket.setVelocity(rocket.getVelocity() -0.02);
    }

    @Override
    public void update(){
    }
}
