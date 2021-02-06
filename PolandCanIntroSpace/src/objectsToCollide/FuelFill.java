package objectsToCollide;

import java.awt.*;

public class FuelFill extends FallingObject {

    public FuelFill(double positionX, double positionY) {
        super(positionX, positionY);
        onCollideDestroy = true;
        width = 60;
        height = 30;
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.YELLOW);
        super.draw(g);
    }

    @Override
    public void interactRocket(Rocket rocket) {
        rocket.addFuel(10000);
    }

    @Override
    public void update(){
        return;
    }
}
