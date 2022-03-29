package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends JPanel{

    JPanel playBoard, title;
    Board board;
    public GameScreen(){
        board = new Board();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        playBoard = new chessBoard();
        playBoard.setBackground(GUI.background);

        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 1280;
        c.ipady = 670;
        this.add(playBoard,c);

    }

    public class chessBoard extends JPanel implements MouseListener {
        int startx = 150;
        int starty = 40;
        int width = 50;
        public chessBoard(){
            addMouseListener(this);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            boolean color = false;
            for (int i = 0; i < 8; i++) {
                color = !color;
                for (int j = 0; j < 8; j++) {
                    if (color) {
                        g.setColor(Color.black);
                        color = !color;
                    } else {
                        g.setColor(Color.WHITE);
                        color = !color;
                    }
                    g.fillRect(startx + (i * width), starty + (j * width), width, width);
                }
            }
        }
        public void posClicked(int x, int y){

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(x == startx +(i * width) && y == starty + (j * width)){

                    }
                }
            }
        }
        public void mouseClicked(MouseEvent e) {
            posClicked(e.getX(), e.getY());
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
