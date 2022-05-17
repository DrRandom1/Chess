import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OpeningBook {
    //best move is always node 0
    public final Node node=new Node("0",new int[]{-1,-1},new int[]{-1,-1});

    public OpeningBook(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/Openings.txt"));
            for (int i = 0; i < 2; i++) {
                String[] moveCode=reader.readLine().split(" ");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add(Node node){
        String path=node.name.substring(0, node.name.lastIndexOf('.'));
        Node parent=this.node;
        while(!path.equals(parent.name)){
            parent=parent.children.get(Integer.parseInt(path.substring(0,path.indexOf('.'))));
            path=path.substring(path.indexOf('.')+1);
        }
        parent.children.add(node);
    }

    public void printAll(Node node){
        for (Node child : node.children) {
            System.out.println(child.name);
            printAll(child);
        }
    }
    public void printAll(){
        printAll(node);
    }


    public static void main(String[] args) {
        OpeningBook book=new OpeningBook();
        book.add(new Node("0.0", new int[]{1,4},new int[]{3,4}));
        book.add(new Node("0.0.0", new int[]{6,2},new int[]{4,2}));
        book.add(new Node("0.0.0.0", new int[]{0,6},new int[]{2,5}));
        book.add(new Node("0.0.0.0.0", new int[]{7,1},new int[]{5,2}));
        book.add(new Node("0.0.0.0.0.0", new int[]{1,3},new int[]{3,3}));
        book.printAll();
    }
}
class Node{
    String name;
    int[] piece;
    int[] location;
    ArrayList<Node> children=new ArrayList<>();
    public Node(String name, int[] piece, int[] location){
        this.name=name;
        this.piece=piece;
        this.location=location;
    }
}