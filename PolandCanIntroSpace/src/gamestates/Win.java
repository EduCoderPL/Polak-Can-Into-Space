package gamestates;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Win {


    public Button returnButton = new Button(350, 350, 150, 50, "Return");
    private Clip clip;

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 15);
        g.setFont(fnt0);
        g.setColor(new Color(217, 0, 37));
        drawString(g, "Let me be honest with you: it`s impossible. \n" +
                "Did you really believe that Poland can into space?\n" +
                "It was everything fake.\n" +
                "Do you think that moon is at 25km far from Earth? Srsly?!\n" +
                "\n" +
                "It was simulation created only to give you hope for better future,\n" +
                "to let you believe you can be somebody greater than minimal salary worker.\n" +
                "But maybe in future our community will change... \n" +
                "\n" +
                "And then... \n" +
                "\n" +
                "Somebody will prove...\n" +
                "that POLAK CAN INTO SPACE!!!", 100, 100);

        returnButton.draw(g2d);
    }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += (g.getFontMetrics().getHeight()+ 1));
    }

    public void playMusic(String musicLocation){
        try{
            File musicPath = new File (musicLocation);

            if(musicPath.exists()){
                System.out.println(musicPath);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                for (int i=0;i<18;i++) {
                    TimeUnit.MILLISECONDS.sleep(75);
                    new AnimatedGif();
                }
            }
            else{
                System.out.println("Can`t find file");
            }
        }
        catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void stopClip(){
        clip.stop();
    }
}
