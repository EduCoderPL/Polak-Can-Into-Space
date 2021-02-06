package gamestates;

import mainPackage.Gameplay;

import java.awt.*;

public class Shop {

    public Button buyFuelBanner;
    public Button buyFuel;

    public Button buyEngineBanner;
    public Button buyEngine;

    public Button buySteeringBanner;
    public Button buySteering;

    public Button returnButton;


    Gameplay gameplay;
    public Shop(Gameplay gameplay){
        this.gameplay = gameplay;
        buyFuelBanner = new Button(50, 150, 200, 50, "Fuel level: "+ gameplay.upgradeLevels[0]);
        buyFuel = new Button(300, 150, 300, 50, ("Buy fuel: "+ (gameplay.upgradeLevels[0] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[0]])));

        buyEngineBanner = new Button(50, 250, 200, 50, "Engine level: "+ gameplay.upgradeLevels[1]);
        buyEngine = new Button(300, 250, 300, 50, ("Buy Engine tank: "+ (gameplay.upgradeLevels[1] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[1]])));

        buySteeringBanner = new Button(50, 350, 200, 50, "Steering level: "+ gameplay.upgradeLevels[2]);
        buySteering = new Button(300, 350, 300, 50, "Steering level: "+ (gameplay.upgradeLevels[2] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[2]]));

        returnButton = new Button (200, 450,200, 50, "Play");
    }

    public void render(Graphics g, Gameplay gameplay){

        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Money: " + gameplay.getMoney(), 522, 60);

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Shop: ", 100, 100);
        g2d.setColor(Color.black);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.setColor(Color.WHITE);

        buyFuelBanner = new Button(50, 150, 200, 50, "Fuel level: "+ gameplay.upgradeLevels[0]);
        buyFuel = new Button(300, 150, 300, 50, ("Buy fuel tank: "+ (gameplay.upgradeLevels[0] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[0]])));

        buyEngineBanner = new Button(50, 250, 200, 50, "Engine level: "+ gameplay.upgradeLevels[1]);
        buyEngine = new Button(300, 250, 300, 50, ("Buy engine: "+ (gameplay.upgradeLevels[1] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[1]])));

        buySteeringBanner = new Button(50, 350, 220, 50, "Steering level: "+ gameplay.upgradeLevels[2]);
        buySteering = new Button(300, 350, 300, 50, "Steering level: "+ (gameplay.upgradeLevels[2] == 3 ? "MAX" : Gameplay.moneyPriceList[gameplay.upgradeLevels[2]]));


        buyFuelBanner.draw(g2d);
        buyEngineBanner.draw(g2d);
        buySteeringBanner.draw(g2d);

        buyFuel.draw(g2d);
        buyEngine.draw(g2d);
        buySteering.draw(g2d);

        returnButton.draw(g2d);

    }
}
