package mainPackage;

import enums.ControlDirection;
import enums.State;
import gamestates.*;
import gamestates.Menu;
import objectsToCollide.*;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int score;
    private int money;
    private int day;

    private Rocket rocket;
    public int[] upgradeLevels;
    public static int[] moneyPriceList = new int[]{1000, 3000, 8000, 99999};

    ArrayList<FallingObject> obstaclesList = new ArrayList<FallingObject>();

    ControlDirection direction = ControlDirection.NONE;

    Stopwatch acceleratorStopwatch = new Stopwatch();
    Stopwatch trapStopwatch = new Stopwatch();
    Stopwatch fuelFillStopwatch = new Stopwatch();
    Random generator = new Random();
    int limit;
    int accelerationLimit;
    int fuelFillLimit;

    public void useMoney(int cost) {
        money -= cost;
    }



    public static State state;
    public static Menu menu;
    public Shop shop;
    public static Help help;
    public static Win win;

    public Gameplay(){
        newGameplay();
    }

    public void paint(Graphics g){

        //background
        int heightColor =  Math.max((int)( 205 * rocket.getActualPositionY()/25000 + 255), 0);
        g.setColor(new Color(0, heightColor, heightColor));
        g.fillRect(0, 0, 700, 600);

        //clouds
        g.setColor(Color.BLACK);
        for (int i = ( - (int) rocket.getActualPositionY()) % 100 - 100; i< getWidth(); i = i + 100) {
            g.fillRect(-5, i, 50, 5);
        }
        g.setColor(Color.green);
        g.fillRect(0, 500 - (int)rocket.getActualPositionY(), 700, 70);//grass


        //Moon
        g.setColor(Color.gray);
        g.fillOval(350, -25000 - (int)rocket.getActualPositionY(), 500, 500);

        //Checkpoints
        g.setColor(Color.gray);
        g.fillOval(500, -5000 - (int)rocket.getActualPositionY(), 500, 100);
        g.setColor(Color.white);
        g.drawString("5000 meters: Taxes for oil", 522, -4950 - (int)rocket.getActualPositionY());

        g.setColor(Color.gray);
        g.fillOval(500, -15000 - (int)rocket.getActualPositionY(), 500, 100);
        g.setColor(Color.white);
        g.drawString("15000 meters: money invested in social programs", 522, -14950 - (int)rocket.getActualPositionY());


        if(state == State.GAME) {
            //bonuses
            for (FallingObject element : obstaclesList) {
                element.draw((Graphics2D) g);
            }
            // Rocket
            rocket.draw((Graphics2D) g);

            //strings:
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.PLAIN, 15));
            g.drawString("Score: " + -(int) (rocket.getActualPositionY()), 522, 20);
            g.drawString("Height: " + -(int) (rocket.getActualPositionY()), 522, 40);
            g.drawString("Money: " + money, 522, 60);
            g.drawString("Speed: " + (int) (-50 * (rocket.getVelocity())), 522, 80);
            g.drawString("Fuel: " + rocket.getFuel(), 522, 100);

            g.setColor(new Color(109, 1, 1));
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Day: " + day, 22, 40);

        } else if(state == State.MENU){
            menu.render(g);
        }
        else if (state == State.SHOP){
            shop.render(g, this);
        }
        else if (state == State.CRASH){
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Out of fuel", 240, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to go shopping.", 200, 330);

        }
        else if (state == State.INSTRUCTIONS) {
            help.render(g);
        }

        else if (state == State.WIN) {
            win.render(g);
        }
        repaint();
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if (state == State.GAME) {
                score = -(int) (rocket.getActualPositionY());
                manageObjects();
                rocket.moveHorizontal(direction);
                rocket.update();
                if (!checkIfWon()) {
                    checkIfFailed();
                }
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            rocket.setThrust(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            direction = ControlDirection.NONE;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            direction = ControlDirection.NONE;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(state == State.GAME) {
                rocket.isStart = true;
                rocket.setThrust(10);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            direction = ControlDirection.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            direction = ControlDirection.RIGHT;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (state == State.CRASH) {
                state = State.SHOP;
                money += score;
                score = 0;
                repaint();
            }
        }
    }

    private void manageObjects(){
        ArrayList<FallingObject> toRemove = new ArrayList<>();
        Rectangle rocketRectangle = new Rectangle((int)rocket.getPositionX(), (int)rocket.getPositionY(), 20, 50);

        for (FallingObject element : obstaclesList) {
            element.update();

            //Move Rectangles
            element.setPositionY(element.getInitialPositionY() - (int) rocket.getActualPositionY());

            if (rocketRectangle.intersects(element.returnRectangle())) {
                element.interactRocket(rocket);
                if(element.isOnCollideDestroy()){
                    toRemove.add(element);
                }
            }
        }

        for (FallingObject obj : obstaclesList) {
            if (obj.getPositionY() > 800) {
                toRemove.add(obj);
            }
        }
        obstaclesList.removeAll(toRemove);

        //add obstacles
        generateNewObjects();
    }

    private void checkIfFailed() {
        if(rocket.getVelocity() > 0 && state == State.GAME) {
            state = State.CRASH;
            rocket.isStart = false;
        }
    }

    private boolean checkIfWon() {

        if(rocket.getActualPositionY() <= -25000 && state == State.GAME) {
            finishGameplay();
            rocket.isStart = false;
            return true;
        }
        return false;
    }

    public void addMoney(int addMoney) {
        money += addMoney;
    }

    public int getMoney() {
        return money;
    }

    public void goToNextDay(){
        state = State.GAME;
        this.rocket = new Rocket(300, 450, this);
        day += 1;
        repaint();
    }

    public void finishGameplay(){
        state = State.WIN;
        win.playMusic("C:/Users/arkad/IdeaProjects/PolandCanIntroSpace/src/End.wav");
        this.rocket = new Rocket(300, 450, this);
        repaint();
    }

    public void newGameplay(){
            state = State.MENU;

            addKeyListener(this);
            this.addMouseListener(new MouseInput(this));

            setFocusable(true);
            setFocusTraversalKeysEnabled(false);

            upgradeLevels = new int[]{0, 0, 0};
            rocket = new Rocket(300, 450, this);

            int delay = 8;
            Timer timer = new Timer(delay, this);

            limit = 5 + generator.nextInt(7);
            accelerationLimit = 7 + generator.nextInt(7);
            fuelFillLimit = 12 + generator.nextInt(10);
            timer.start();
            acceleratorStopwatch.start();
            trapStopwatch.start();

            menu = new Menu();
            shop = new Shop(this);
            help = new Help();
            win = new Win();

            score = 0;
            money = 0;
            day = 1;
    }

    private void generateNewObjects(){
        if(rocket.isStart) {

            if ((int) trapStopwatch.getElapsedTimeSeconds() >= limit) {
                FallingObject newObject;
                if (generator.nextInt(2) == 1) {
                    newObject = new Trap1(50 + generator.nextInt(600), -50 + rocket.getActualPositionY());
                } else {
                    newObject = new Trap2(50 + generator.nextInt(600), -50 + rocket.getActualPositionY());
                }
                obstaclesList.add(newObject);
                trapStopwatch.start();
                limit = 5 + generator.nextInt(7);
            }
            //add accelerators
            if ((int) acceleratorStopwatch.getElapsedTimeSeconds() >= accelerationLimit) {
                Accelerator newObject = new Accelerator(50 + generator.nextInt(600), -150 + rocket.getActualPositionY());
                obstaclesList.add(newObject);
                acceleratorStopwatch.start();
                accelerationLimit = 7 + generator.nextInt(10);
            }
            //bonus
            if ((int) fuelFillStopwatch.getElapsedTimeSeconds() >= fuelFillLimit) {
                FallingObject newObject;
                if (generator.nextInt(2) == 1) {
                    newObject = new FuelFill(50 + generator.nextInt(600), -150 + rocket.getActualPositionY());
                } else {
                    newObject = new Money(50 + generator.nextInt(600), -150 + rocket.getActualPositionY());
                }
                obstaclesList.add(newObject);
                fuelFillStopwatch.start();
                fuelFillLimit = 12 + generator.nextInt(10);
            }
        }
    }
}
