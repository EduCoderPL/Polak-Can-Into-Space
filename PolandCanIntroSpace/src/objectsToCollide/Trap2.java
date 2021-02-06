package objectsToCollide;

import enums.ControlDirection;

import java.awt.*;

public class Trap2 extends FallingObject {

    private ControlDirection moveDirection;

    public Trap2(double positionX, double positionY) {
        super(positionX, positionY);
        onCollideDestroy = true;
        moveDirection = ControlDirection.LEFT;
        width = 30;
        height = 30;
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        super.draw(g);
    }

    @Override
    public void interactRocket(Rocket rocket) {
        rocket.collide();
    }

    @Override
    public void update(){
        if(positionX< 50) {
            moveDirection = ControlDirection.RIGHT;
        }
        if(positionX > 650){
            moveDirection = ControlDirection.LEFT;
        }
        if(moveDirection == ControlDirection.RIGHT){
            positionX += 1;
        }
        if(moveDirection == ControlDirection.LEFT)
        {
            positionX -= 1;
        }
    }
}
