package gamestates;

import java.awt.*;

public class Help {


    public Button returnButton = new Button(350, 350, 150, 50, "Return");


    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt0);
        g.setColor(Color.white);
        drawString(g, "You start with basic rocket. \n" +
                "Your goal is to reach 25000 meters to land on moon.\n" +
                "You must earn money per reached height and avoid obstacles.\n" +
                "Steering: Left, Up and Right arrows.\n" +
                "\n" +
                "\n\n" +
                "Gray rectangles mean thrusters; \n" +
                "Gold rectangles mean Fuel;\n" +
                "Gold circles mean 500 money;\n" +
                "Pink squares mean enemy to avoid;\n" +
                "Black squares mean movable enemy to avoid\n", 100, 30);

        returnButton.draw(g2d);
    }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += (g.getFontMetrics().getHeight()+ 1));
    }
}
