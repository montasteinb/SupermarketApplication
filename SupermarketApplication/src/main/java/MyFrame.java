import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame(){

        this.setTitle("SUPERMARKET");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(520, 520);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("shoplogo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(120,60,160));
    }

}
