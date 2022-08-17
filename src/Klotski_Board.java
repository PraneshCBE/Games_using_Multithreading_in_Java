import java.util.*;


public class Klotski_Board {

    String Board_Size;
    int No_Of_Columns,No_Of_Rows;
    Hashtable<Integer,Integer> No_Of_Moves = new Hashtable<>();

    public Klotski_Board() {
        this.Board_Size="2 X 3";
    }

    public String getBoard_Size() {

        return this.Board_Size;
    }

    public void setNo_Of_Moves(Hashtable<Integer,Integer> h) {
        this.No_Of_Moves=h;
    }

    public Hashtable<Integer,Integer> getNo_Of_Moves() {
        return No_Of_Moves;
    }




}
