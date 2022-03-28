package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel{

    JPanel board, title;

    public GameScreen(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        board = new JPanel();
        board.setBackground(Color.pink);

        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 1280;
        c.ipady = 670;
        this.add(board,c);

    }
}
