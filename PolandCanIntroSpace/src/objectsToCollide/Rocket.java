package objectsToCollide;

import enums.ControlDirection;
import mainPackage.Gameplay;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Rocket extends ObjectWithCollision {


    private final Gameplay gameplay;
    private double velocity;
    private int rotation;
    private int fuel;
    private int thrust;
    public boolean isStart;
    private int fuelLevel;
    private int engineLevel;
    private int steeringLevel;
    private Rectangle rectangle;
    private double actualPositionY;

    private final int[] fuelLevelList = new int[]{20000, 40000, 80000, 130000};
    private final double[] engineLevelList = new double[]{0.98, 1.1, 1.3, 1.5};
    private final double[] steeringLevelList = new double[]{0.5, 1, 1.5, 2};


    public Rocket(int x, int y, Gameplay gameplay) {
        super(x, y);
        actualPositionY = 0;
        velocity = 0;
        thrust = 0;
        isStart = false;
        rotation = 0;
        this.gameplay = gameplay;
        fuelLevel = gameplay.upgradeLevels[0];
        engineLevel = gameplay.upgradeLevels[1];
        steeringLevel = gameplay.upgradeLevels[2];
        fuel = fuelLevelList[fuelLevel];
        rectangle = new Rectangle(x, y, 20, 50);

        width = 20;
        height = 50;
    }

    public Rocket(int x, int y, Gameplay gameplay, int fuelLevel, int engineLevel, int steeringLevel){
        this(x,y, gameplay);
        this.fuelLevel = fuelLevel;
        this.engineLevel = engineLevel;
        this.steeringLevel = steeringLevel;
    }
    public double getVelocity() {
        return velocity;
    }
    public int getFuel() { return fuel;}


    public void setVelocity(double velo) {
        velocity = velo;
    }
    public void setThrust(int thrust){
        this.thrust = thrust;
    }

    public void eatFuel(){
        if(thrust > 0) {
            fuel -= 10;
        }
        if (fuel < 0){
            fuel = 0;
        }
    }

    public void accelerate(){

        double acceleration = fuel > 0 ? 0.01 - (double) thrust * engineLevelList[engineLevel] /(900) : 0.01;
        eatFuel();
        velocity += acceleration;
        if (velocity < -5) {
            velocity = -5;
        }
        if (velocity > 1) {
            velocity = 1;
        }
    }

    public void moveVertical(){
        actualPositionY += velocity / 3;
        positionY = 450 + (int)(15 * velocity);
    }

    public double getActualPositionY(){
        return actualPositionY;
    }

    public void moveHorizontal(ControlDirection direction){
        if (direction == ControlDirection.RIGHT) {
            positionX +=  0.1 * steeringLevelList[steeringLevel] * (Math.abs(velocity) + 3);
            rotation = (int)(6 * steeringLevelList[steeringLevel] * (Math.abs(velocity) + 3));
        }
        else if (direction == ControlDirection.LEFT) {
            positionX -= 0.1 * steeringLevelList[steeringLevel] * (Math.abs(velocity) + 3);
            rotation = (int)(-6 * steeringLevelList[steeringLevel] * (Math.abs(velocity) + 3));
        }
        else {
            rotation = 0;
        }
    }

    public void update(){
        if(isStart) {
            accelerate();
            moveVertical();
        }

    }

    public void draw(Graphics2D g){
        g.setColor(Color.white);
        AffineTransform old = g.getTransform();
        g.rotate(Math.toRadians(rotation), (int)positionX + 10 , positionY + 25);
        rectangle = super.returnRectangle();
        g.fill(rectangle);

        //thruster
        if (thrust > 0 && fuel > 0) {
            g.setColor(Color.red);
            g.fillOval((int) getPositionX() - 5, (int)positionY + 50, 30, 30);
            g.fillOval((int) getPositionX(), (int)positionY + 70, 20, 20);
        }
        g.setTransform(old);
    }

    public void collide(){
        if(rotation>=0){
            positionX += 25;
        }
        else{
            positionX -= 25;
        }
        velocity += 1;
        velocity = velocity/3;
    }

    public void addFuel(int fuel) {
        this.fuel += fuel;
    }

    public void addMoney(int addMoney) {
        gameplay.addMoney(addMoney);
    }
}
