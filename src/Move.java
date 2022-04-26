public class Move {
    protected char type;
    private final int[] position;
    private final int[] initialPos;
    private final Piece piece;
    private final String ambiguousMoveNotation;
    private final char[] key = new char[]{'a','b','c','d','e','f','g','h'};


    public Move(Piece piece, int[] position) {
        this.type='n';
        this.position = position;
        this.piece = piece;
        this.initialPos=piece.getPosition();
        this.ambiguousMoveNotation = getAmbiguousMoveNotation();
    }
    public Move(Piece piece, int row, int column) {
        this.type='n';
        this.position = new int[]{row, column};
        this.piece = piece;
        this.initialPos=piece.getPosition();
        this.ambiguousMoveNotation = getAmbiguousMoveNotation();
    }

    public int[] getPosition() {
        return position;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getRow(){
        return position[0];
    }
    public int getColumn(){
        return position[1];
    }
    public boolean isPawnDoubleMove(){
        if(piece.getName()=='p'&&Math.abs(position[0]-initialPos[0])==2){
            return true;
        }
        return false;
    }
    public String getAmbiguousMoveNotation(){
        if(piece.getName()=='p'){
            return key[position[1]]+""+(position[0]+1);
        } else{
            return piece.getName()+""+key[position[1]]+""+(position[0]+1);
        }
    }
}
class EnPassant extends Move{

    public EnPassant(Piece piece, int[] position) {
        super(piece, position);
        super.type='e';

    }

    public EnPassant(Piece piece, int row, int column) {
        super(piece, row, column);
        super.type='e';
    }
}
class Castle extends Move{
    public Castle(Piece piece, int[] position) {
        super(piece, position);
        this.type='c';
    }

    public Castle(Piece piece, int row, int column) {
        super(piece, row, column);
        this.type='c';
    }
}
