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
    GridBagConstraints c;
    public StartScreen(){
        this.setBackground(GUI.background);
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

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

        selection = new JPanel();
        selection.setBackground(GUI.background2);
        selection.setLayout(new GridLayout(2,3));
        numPlayer = new JLabel("One or two players?", SwingConstants.CENTER);
        numPlayer.setFont(new Font(Font.SERIF,Font.ITALIC, 30));
        numPlayer.setForeground(Color.black);
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
            numPlayers[i].setForeground(GUI.white);
            colorSelect[i].setForeground(Color.WHITE);
            switch(i){
                case(0): numPlayers[0].setBackground(GUI.black);
                         colorSelect[0].setBackground(GUI.black);
                         numPlayers[0].setForeground(GUI.white);
                         colorSelect[0].setForeground(GUI.white);
                case(1): numPlayers[1].setBackground(GUI.white);
                         colorSelect[1].setBackground(GUI.white);
                         numPlayers[1].setForeground(GUI.black);
                         colorSelect[1].setForeground(GUI.black);
            }
        }
        selection.add(numPlayers[0]);
        selection.add(numPlayers[1]);
        colorChoose = new JLabel("What color are you?", SwingConstants.CENTER);
        colorChoose.setFont(new Font(Font.SERIF,Font.ITALIC, 30));
        colorChoose.setForeground(Color.black);
        selection.add(colorChoose);
        selection.add(colorSelect[0]);
        selection.add(colorSelect[1]);


        play = new JButton("Play");
        play.setFont(new Font(Font.SERIF,Font.ITALIC, 30));
        play.addActionListener(this);
        play.setBackground(GUI.white);


        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 100;
        c.ipady = 20;
        this.add(title,c);
        c.gridy = 1;
        c.ipadx = 100;
        c.ipady = 280;
        this.add(selection,c);
        c.weighty = 1;
        c.gridy = 2;
        c.ipadx = 75;
        c.ipady = 40;
        this.add(play,c);

    }
    public void createGame(){
        Board board = new Board();
        Player[] players = new Player[2];
        if(onePlayer){
            if(pOneWhite){
                players[1] = new HumanPlayer('b',board);
                players[0] = new Player('w',board);
            }
            else{
                players[1] = new Player('b',board);
                players[0] = new HumanPlayer('w',board);
            }

        }
        else{
            players[1] = new HumanPlayer('b',board);
            players[0] = new HumanPlayer('w',board);
        }
        Game game = new Game(board, players);
        GUI.gameScreen = new GameScreen(game);
        GUI.swapScreen(this, GUI.gameScreen);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == numPlayers[0]){
            numPlayers[1].setBackground(GUI.white);
            numPlayers[0].setBackground(GUI.black);
            numPlayers[1].setForeground(GUI.black);
            numPlayers[0].setForeground(GUI.white);
            onePlayer = true;
            colorSelect[0].setVisible(true);
            colorSelect[1].setVisible(true);
            colorChoose.setVisible(true);
        }
        if (e.getSource() == numPlayers[1]){
            numPlayers[0].setBackground(GUI.white);
            numPlayers[1].setBackground(GUI.black);
            numPlayers[0].setForeground(GUI.black);
            numPlayers[1].setForeground(GUI.white);
            onePlayer = false;
            colorSelect[0].setVisible(false);
            colorSelect[1].setVisible(false);
            colorChoose.setVisible(false);
        }
        if (e.getSource() == colorSelect[0]){
            colorSelect[1].setBackground(GUI.white);
            colorSelect[0].setBackground(GUI.black);
            colorSelect[1].setForeground(GUI.black);
            colorSelect[0].setForeground(GUI.white);
            pOneWhite = true;
        }
        if (e.getSource() == colorSelect[1]){
            colorSelect[0].setBackground(GUI.white);
            colorSelect[1].setBackground(GUI.black);
            colorSelect[0].setForeground(GUI.black);
            colorSelect[1].setForeground(GUI.white);
            pOneWhite = false;
        }
        if(e.getSource() == play){
            createGame();
        }
    }
}
