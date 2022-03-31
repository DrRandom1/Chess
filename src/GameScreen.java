import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends JPanel{

    JPanel playBoard;
    Board board;
    Piece selected;
    public GameScreen(){
        board = new Board();
        board.printBoardState();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        playBoard = new chessBoard();
        playBoard.setBackground(GUI.background);

        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 1280;
        c.ipady = 675;
        this.add(playBoard,c);

    }

    public class chessBoard extends JPanel implements MouseListener {
        int startX = 40;
        int startY = 41;
        int width = 75;
        public chessBoard(){
            addMouseListener(this);
        }

        protected void paintComponent(Graphics g) {
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
                    g.fillRect(startX + (i * width), startY + (j * width), width, width);

                }
            }
            if(selected != null){
                g.setColor(Color.CYAN);
                g.drawRect(startX + (selected.getRow() * width), startY + (selected.getColumn() * width),width,width);
            }
        }
        public int[] posClicked(int x, int y){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(startX +(i * width) <= x && x < startX +((i+1) * width) && startY +(j * width) <= y && y < startY +((j+1) * width)){
                        System.out.println((i+1) + " " + (j+1));
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{-1};
        }
        public void mouseClicked(MouseEvent e) {
            int[] index = posClicked(e.getX(), e.getY());
            selected = board.getBoard()[index[0]][index[1]];
            this.repaint();
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}