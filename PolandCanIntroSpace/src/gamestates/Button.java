package gamestates;

import java.awt.*;

public class Button {

    private int posX;
    private int posY;
    private int width;
    private int height;
    private Rectangle rect;
    private Color color;
    private Font font;
    private String string;


    public Button(int posX, int posY, int width, int height, String string, int textSize) {
        this(posX, posY, width, height, string);
        this.font = new Font("arial", Font.BOLD, textSize);
    }

    public Button(int posX, int posY, int width, int height, String string) {
        this.color = Color.white;
        this.font = new Font("arial", Font.BOLD, 25);
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.string = string;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.darkGray);
        Rectangle rect = new Rectangle(posX, posY, width, height);
        g.setFont(this.font);
        g.fill(rect);
        g.setColor(Color.white);
        g.drawString(string, rect.x + 19, rect.y + 30);
    }

    public boolean checkIfClicked(int x, int y){
        if(posX <= x && x<= posX + width){
            return posY <= y && y <= posY + height;
        }
        return false;
    }

    public void setString(String string) {
        this.string = string;
    }
}
