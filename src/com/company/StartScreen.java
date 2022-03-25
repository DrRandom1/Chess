package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel implements ActionListener {
    JButton[] numPlayers = new JButton[2];
    JButton[] colorSelect = new JButton[2];
    JButton play;
    JPanel title, selection;
    JLabel titleWord, numPlayer, colorChoose;
    boolean onePlayer, pOneWhite;
    public StartScreen(){
        this.setBackground(GUI.background);
        this.setLayout(new GridLayout(3,1));

        onePlayer = true;
        pOneWhite = true;

        //TitleWord made
        title = new JPanel();
        titleWord = new JLabel("CHESS",SwingConstants.CENTER);
        titleWord.setFont(new Font(Font.SERIF,Font.ITALIC, 100));
        titleWord.setForeground(GUI.white);
        title.setLayout(new BorderLayout());
        title.add(titleWord, BorderLayout.CENTER);
        title.setBackground(GUI.background);

        this.add(title);

        selection = new JPanel();
        selection.setLayout(new GridLayout(2,3));
        numPlayer = new JLabel("One or two players?", SwingConstants.CENTER);
        numPlayer.setFont(new Font(Font.SERIF,Font.ITALIC, 30));
        selection.add(numPlayer);
        numPlayers[0] = new JButton("One");
        numPlayers[1] = new JButton("Two");
        colorSelect[0] = new JButton("White");
        colorSelect[1] = new JButton("Black");
        for(int i = 0; i < 2; i++) {
            numPlayers[i].addActionListener(this);
            colorSelect[i].addActionListener(this);
            numPlayers[i].setFont(new Font(Font.SERIF,Font.ITALIC, 30));
            colorSelect[i].setFont(new Font(Font.SERIF,Font.ITALIC, 30));
            switch(i){
                case(0): numPlayers[0].setBackground(GUI.black);
                         colorSelect[0].setBackground(GUI.black);
                case(1): numPlayers[1].setBackground(GUI.white);
                         colorSelect[1].setBackground(GUI.white);
            }
        }
        selection.add(numPlayers[0]);
        selection.add(numPlayers[1]);
        colorChoose = new JLabel("Which color is Player 1?", SwingConstants.CENTER);
        colorChoose.setFont(new Font(Font.SERIF,Font.ITALIC, 30));
        selection.add(colorChoose);
        selection.add(colorSelect[0]);
        selection.add(colorSelect[1]);

        this.add(selection);

        play = new JButton("Play");
        play.addActionListener(this);
        play.setBackground(GUI.white);

        this.add(play);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == numPlayers[0]){
            numPlayers[1].setBackground(GUI.white);
            numPlayers[0].setBackground(GUI.black);
            onePlayer = true;
        }
        if (e.getSource() == numPlayers[1]){
            numPlayers[0].setBackground(GUI.white);
            numPlayers[1].setBackground(GUI.black);
            onePlayer = false;
        }
        if (e.getSource() == colorSelect[0]){
            colorSelect[1].setBackground(GUI.white);
            colorSelect[0].setBackground(GUI.black);
            pOneWhite = true;
        }
        if (e.getSource() == colorSelect[1]){
            colorSelect[0].setBackground(GUI.white);
            colorSelect[1].setBackground(GUI.black);
            pOneWhite = false;
        }
        if(e.getSource() == play){
            GUI.gameScreen = new GameScreen();
            GUI.swapScreen(GUI.gameScreen);
        }
    }
}
