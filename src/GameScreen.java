import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen extends JPanel{

    JPanel playBoard, infoBoard;
    Game game;
    int[] selected;
    int[] selectedMove;
    boolean current = true;
    JLabel black = new JLabel("Black"), white = new JLabel("White");
    public GameScreen(Game game){
        this.game = game;

        this.setLayout(new GridBagLayout());
        this.setBackground(GUI.background);
        GridBagConstraints c = new GridBagConstraints();

        playBoard = new chessBoard();
        playBoard.setBackground(GUI.background);

        infoBoard = new JPanel();
        infoBoard.setMinimumSize(new Dimension(550, 675));
        infoBoard.add(new JLabel("Current Player:"));
        infoBoard.add(white);


        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 695;
        c.ipady = 675;
        this.add(playBoard,c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 640;
        c.ipady = 675;
        this.add(infoBoard);

    }
    public Player getCurrentPlayer(){
        return game.getCurrentPlayer();
    }
    private void updateInfoBoard(){
        if(getCurrentPlayer().getColor() == 'w'){
            infoBoard.remove(black);
            infoBoard.add(white);
        }
        else{
            infoBoard.remove(white);
            infoBoard.add(black);
        }
        infoBoard.updateUI();
    }

    private class chessBoard extends JPanel implements MouseListener {
        int startX = 40;
        int startY = 41;
        int width = 75;
        public chessBoard(){
            addMouseListener(this);
        }

        protected void paintComponent(Graphics g) {
            //g.drawString(players[0].isInCheck() + " " + players[1].isInCheck(), 20,20);
            super.paintComponent(g);
            boolean color = false;
            for (int i = 0; i < 8; i++) {
                color = !color;
                for (int j = 0; j < 8; j++) {
                    if(color) {
                        g.setColor(Color.black);
                        color = false;
                    } else {
                        g.setColor(Color.WHITE);
                        color = true;
                    }
                    g.fillRect(startX + (j * width), startY + (i * width), width, width);

                }
            }
            paintAllPieces(g);
            if(selected != null) {
                paintSelectedPeice(g);
            }
            if(selectedMove != null) {
                PaintSelectedMove(g);
            }
           if(game.getCurrentPlayer().isInCheck()){
                PaintCheck(g);
            }
        }
        private void PaintCheck(Graphics g) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (game.getBoard().getPiece(i, j).getName() == 'k'&& game.getBoard().getPiece(i, j).getColor() == getCurrentPlayer().getColor()) {
                        g.drawRect(startX + j + (getMoves().get(i).getColumn() * width), startY + j + (getMoves().get(i).getRow() * width), width - (j * 2), width - (j * 2));
                    }
                }
            }
        }
        private void paintAllPieces(Graphics g){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(game.getBoard().getPiece(i,j) != null) {
                        /*
                        g.setFont(new Font(Font.SERIF,Font.ITALIC, 50));
                        if(game.getBoard().getPiece(i,j).getColor() == 'w'){
                            g.setColor(GUI.white);
                        }
                        else{
                            g.setColor(GUI.black);
                        }
                        g.drawString(Character.toString(game.getBoard().getPiece(i, j).getName()), startX + (j * width) + (width/2), startY + (i * width) + (width/2));
                        */
                        Toolkit t=Toolkit.getDefaultToolkit();
                        Image myPicture = (t.getImage("src/Standard/" + game.getBoard().getPiece(i, j).getColor() + game.getBoard().getPiece(i, j).getName() + ".png"));
                        g.drawImage(myPicture,startX + (j * width), startY + (i * width),this);
                    }
                }
            }
        }
        private void paintAvailableMoves(Graphics g){
            for(int i = 0; i < getMoves().size(); i++){
                g.setColor(Color.pink);
                for(int j = 0; j < 5; j++) {
                    g.drawRect(startX + j + (getMoves().get(i).getColumn() * width), startY + j + (getMoves().get(i).getRow() * width), width - (j * 2), width - (j * 2));
                }
            }
        }
        private ArrayList<Move> getMoves(){
            return game.getBoard().getPiece(selected).getMoves();
        }

        private void paintSelectedPeice(Graphics g){
            if(selected[0] >= 0 && selected[1] >= 0 && game.getBoard().getPiece(selected[0],selected[1]) != null){
                g.setColor(Color.CYAN);
                for(int i = 0; i < 5; i++){
                    g.drawRect(startX+i + (selected[1] * width), startY+i + (selected[0] * width),width-(i*2),width-(i*2));
                }
                g.drawRect(startX + (selected[1] * width), startY + (selected[0] * width),width,width);
                paintAvailableMoves(g);
            }
        }

        private void PaintSelectedMove(Graphics g) {
            if(selectedMove[0] >= 0 && selectedMove[1] >= 0){
                g.setColor(Color.blue);
                for(int i = 0; i < 5; i++){
                    g.drawRect(startX+i + (selectedMove[1] * width), startY+i + (selectedMove[0] * width),width-(i*2),width-(i*2));
                }
                g.drawRect(startX + (selectedMove[1] * width), startY + (selectedMove[0] * width),width,width);
            }
        }

        private int[] posClicked(int x, int y){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(startX +(j * width) <= x && x < startX +((j+1) * width) && startY +(i * width) <= y && y < startY +((i+1) * width)){

                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        private boolean checkPos(int[] pos){
            for(int i = 0; i < getMoves().size(); i++){
                if(pos[0] == getMoves().get(i).getPosition()[0] && pos[1] == getMoves().get(i).getPosition()[1]){
                    return true;
                }
            }
            return false;
        }
        private void movePeice(){
            game.makeMove(new Move(game.getBoard().getPiece(selected[0],selected[1]), selectedMove));
            selected = null;
            selectedMove = null;
            current = !current;
            this.repaint();
        }
        public void mouseClicked(MouseEvent e) {
            if(game.getBoard().getPiece(posClicked(e.getX(), e.getY())) != null && getCurrentPlayer().getColor() == game.getBoard().getPiece(posClicked(e.getX(), e.getY())).getColor()) {
                selected = posClicked(e.getX(), e.getY());
                selectedMove = null;
                this.repaint();
            }
            if(selected != null && checkPos(posClicked(e.getX(), e.getY()))){
                selectedMove =  posClicked(e.getX(), e.getY());
                movePeice();
                this.repaint();
                updateInfoBoard();
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
