package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI {
    static JFrame frame;
    public static JPanel startScreen, gameScreen, endScreen;
    public static Color white = new Color(248,240,227);
    public static Color black = new Color(20,20,20);
    public static Color background = new Color(104,31,0);
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
    public static void swapScreen(JPanel next){
        frame.remove();
        frame.add(next);
        next.updateUI();
    }
}
