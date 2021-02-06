package objectsToCollide;

import java.awt.*;

public class Money extends FallingObject {

    public Money(double positionX, double positionY) {
        super(positionX, positionY);
        onCollideDestroy = true;
        width = 50;
        height = 50;
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.fillOval((int)positionX, (int)positionY, width, height);
    }

    @Override
    public void interactRocket(Rocket rocket) {
        rocket.addMoney(500);
    }

    @Override
    public void update(){
        return;
    }
}
