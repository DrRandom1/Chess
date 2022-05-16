import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen extends JPanel implements ActionListener{

    JPanel playBoard, infoBoard, text;
    Game game;
    JLabel eval=new JLabel("Current Evaluation: 0.0",SwingConstants.CENTER);
    JLabel bestMove=new JLabel("Null",SwingConstants.CENTER);
    JButton back;
    int[] selected;
    int[] selectedMove;
    boolean current = true;
    JLabel black = new JLabel("Current Player: Black",SwingConstants.CENTER), white = new JLabel("Current Player: White",SwingConstants.CENTER);
    public GameScreen(Game game){
        this.game = game;

        this.setLayout(new GridBagLayout());
        this.setBackground(GUI.background);
        GridBagConstraints c = new GridBagConstraints();

        infoBoard = new JPanel();
        text = new JPanel();
        playBoard = new chessBoard();
        playBoard.setBackground(GUI.background);

        back = new JButton("Back to Menu");
        back.addActionListener(this);


        text.setLayout(new GridLayout(3,1));
        text.add(eval);
        text.add(bestMove);
        text.add(white);

        infoBoard.setMinimumSize(new Dimension(550, 675));
        infoBoard.setLayout(new GridBagLayout());
        c.ipadx = 470;
        c.ipady = 500;
        c.gridx = 0;
        c.gridy = 0;
        infoBoard.add(text,c);
        c.gridy = 1;
        c.ipady = 100;
        infoBoard.add(back,c);



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
    
    private void isGameOver(){
        if(game.isGameOver()){
            GUI.endScreen = new JFrame("Win");
            GUI.endScreen.setSize(500,200);
            String win;
            if(game.getPlayers()[game.winningPlayer()].getColor() == 'w'){
                win = "White";
            }
            else{
                win = "Black";
            }
            GUI.endScreen.add(new JLabel(win + " Wins!!!!",SwingConstants.CENTER));
            GUI.endScreen.setVisible(true);
            playBoard.setFocusable(false);
        }
    }
    
    public Player getCurrentPlayer(){
        return game.getCurrentPlayer();
    }
    
    private void updateInfoBoard(){
        if(getCurrentPlayer().getColor() == 'w'){
            text.remove(black);
            text.add(white);
        }
        else{
            text.remove(white);
            text.add(black);
        }
        eval.setText("Current Evaluation: "+ComputerPlayer.evaluatePosition(game.getBoard()));
        //bestMove.setText(new ComputerPlayer(getCurrentPlayer().getColor(), game.getBoard()).getBestMove().getPiece().getName()+""+
        //        (Arrays.toString((new ComputerPlayer(getCurrentPlayer().getColor(), getCurrentPlayer().board).getBestMove().getPiece().getPosition())))+", "+
        //        (Arrays.toString((new ComputerPlayer(getCurrentPlayer().getColor(), getCurrentPlayer().board).getBestMove().getPosition()))));
        infoBoard.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.swapScreen(this,GUI.startScreen);
    }

    private class chessBoard extends JPanel implements MouseListener {
        int startX = 40;
        int startY = 41;
        int width = 75;
        public chessBoard(){
            addMouseListener(this);
            if(game.getPlayers()[0] instanceof ComputerPlayer){
                selectedMove = ((ComputerPlayer) getCurrentPlayer()).getMove().getPosition();
                selected = ((ComputerPlayer) getCurrentPlayer()).getMove().getPiece().getPosition();
                movePeice();
                this.repaint();
                updateInfoBoard();
            }
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
            if(game.getCurrentPlayer().isInCheck()){
                PaintCheck(g);
            }
            if(selected != null) {
                paintSelectedPeice(g);
            }
            if(selectedMove != null) {
                PaintSelectedMove(g);
            }
            paintComputer(g);
        }
        private void paintComputer(Graphics g) {
            g.setColor(Color.GREEN);
            int c;
            if(game.getPlayers()[0] instanceof ComputerPlayer){
                c = 0;
            }
            else if(game.getPlayers()[1] instanceof ComputerPlayer){
                c = 1;
            }
            else {
                return;
            }
            int i = ((ComputerPlayer) game.getPlayers()[c]).getMove().getRow();
            int j = ((ComputerPlayer) game.getPlayers()[c]).getMove().getColumn();
            for(int k = 0; k<5; k++){
                g.drawRect(startX+k + (j * width), startY+k + (i * width),width-(k*2),width-(k*2));
            }

        }
        private void PaintCheck(Graphics g) {
            g.setColor(Color.RED);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (game.getBoard().getPiece(i, j) != null && game.getBoard().getPiece(i, j).getName() == 'k'&& game.getBoard().getPiece(i, j).getColor() == getCurrentPlayer().getColor()) {
                        for(int k = 0; k<5; k++){
                            g.drawRect(startX+k + (j * width), startY+k + (i * width),width-(k*2),width-(k*2));
                        }
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

        private ArrayList<Move> getMoves(){
            return game.getBoard().getPiece(selected).getMoves();
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
           int selectedMoveKey = 0;
            for (int i = 0; i < game.getBoard().getPiece(selected[0],selected[1]).getMoves().size(); i++) {
                Move move=game.getBoard().getPiece(selected[0],selected[1]).getMoves().get(i);
                if(move.getPosition()[0]==selectedMove[0]&&move.getPosition()[1]==selectedMove[1]){
                    selectedMoveKey=i;
                }
            }
            game.makeMove(game.getBoard().getPiece(selected[0],selected[1]).getMoves().get(selectedMoveKey));
            selected = null;
            selectedMove = null;
            current = !current;
            this.repaint();
            isGameOver();
            if(getCurrentPlayer() instanceof ComputerPlayer){
                selectedMove = ((ComputerPlayer) getCurrentPlayer()).getMove().getPosition();
                selected = ((ComputerPlayer) getCurrentPlayer()).getMove().getPiece().getPosition();
                movePeice();
                this.repaint();
                updateInfoBoard();
            }
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
