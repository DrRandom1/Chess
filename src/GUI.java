

import javax.swing.*;
import java.awt.*;

public class GUI {
    static JFrame frame;
    public static JPanel startScreen, gameScreen;
    public static JFrame endScreen;
    public static Color white = new Color(236, 196, 132);
    public static Color black = new Color(61, 6, 6);
    public static Color background = new Color(93, 30, 3);
    public static Color background2 = new Color(164, 104, 55);
    public GUI(){
        frame= new JFrame("chess");
        startScreen = new StartScreen();
        frame.add(startScreen);
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(false);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void swapScreen(JPanel current, JPanel next){
        frame.remove(current);
        frame.add(next);
        next.updateUI();
    }
}
