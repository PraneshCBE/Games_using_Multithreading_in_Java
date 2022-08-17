import java.util.*;
import java.io.*;

public class Crip extends Player {

    Move m1=new Move();
    private int minMoves=0;

    public int getMinMoves() {
        return minMoves;
    }

    public void playerAction(int id, String name)
    {

        int n = 10;
        int[] arr = new int[n];

        try {
            Scanner s = new Scanner(new File(String.format("src\\ZipnCrip%d",id)));


            int i = 0;
            while(s.hasNextInt() && i<n) {
                arr[i] = s.nextInt();
                i++;
            }

        }catch(IOException e)
        {
            System.out.println(e);
        }
        this.minMoves=m1.minJumps(arr, arr.length);
        System.out.println("Number of jumps to reach end by "+name+" is : "
                + this.minMoves);
    }
}
