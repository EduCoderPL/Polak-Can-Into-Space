package gamestates;

import java.awt.*;


public class Menu {

    public Button playButton = new Button(250, 150, 200, 50, "Play");
    public Button helpButton = new Button(250, 250, 200, 50, "Instructions");
    public Button quitButton = new Button(250, 350, 200, 50, "Quit");


    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Poland can into Space", 100, 100);

        playButton.draw(g2d);
        helpButton.draw(g2d);
        quitButton.draw(g2d);


    }
}
