package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel{
    public GameScreen(){
        JPanel title = new JPanel();
        JLabel titleWord = new JLabel("CHESS",SwingConstants.CENTER);
        titleWord.setFont(new Font(Font.SERIF,Font.ITALIC, 100));
        titleWord.setForeground(GUI.white);
        title.setLayout(new BorderLayout());
        title.add(titleWord, BorderLayout.CENTER);
        title.setBackground(GUI.background);

        this.add(title);


    }
}
