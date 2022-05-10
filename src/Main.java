
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GUI gui=new GUI();
    }
    private static int [] parsePosition(String string){
        int[] position=new int[2];
        if(string.length()!=2){
            return null;
        }
        switch(string.charAt(0)){
            case 'a'->position[1]=0;
            case 'b'->position[1]=1;
            case 'c'->position[1]=2;
            case 'd'->position[1]=3;
            case 'e'->position[1]=4;
            case 'f'->position[1]=5;
            case 'g'->position[1]=6;
            case 'h'->position[1]=7;
            default -> {
                return null;
            }
        }
        switch(string.charAt(1)){
            case '1'->position[0]=0;
            case '2'->position[0]=1;
            case '3'->position[0]=2;
            case '4'->position[0]=3;
            case '5'->position[0]=4;
            case '6'->position[0]=5;
            case '7'->position[0]=6;
            case '8'->position[0]=7;
            default -> {
                return null;
            }
        }
        return position;
    }
}
