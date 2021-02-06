package gamestates;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnimatedGif extends JFrame {

    public AnimatedGif() {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();

            Random random = new Random();
            Icon imgIcon = new ImageIcon(this.getClass().getResource("end.gif"));
            JLabel label = new JLabel(imgIcon);
            label.setBounds(50, 50, 460, 14); // You can use your own values
            this.setLocation(random.nextInt((int)width - 350), random.nextInt((int)height - 350));
            this.getContentPane().add(label);
            setSize(350, 350);
            setVisible(true);
        }
        catch(Exception e){
            System.out.print("Error of image: \n" + e);

        }
    }
}
