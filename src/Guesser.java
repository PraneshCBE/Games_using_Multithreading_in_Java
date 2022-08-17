import java.util.*;
import java.io.*;
import java.lang.Thread;
public class Guesser extends Player
{
    private int playerID;
    private int totScore;
    private String status;
    private GuesserPoints GamePoint;
    private Grid grid;
    public Guesser(int playerID, int totScore, String status,int gridNum) {
        GamePoint=new GuesserPoints();
        grid = new Grid(gridNum*gridNum);
        this.playerID = playerID;
        this.totScore = totScore;
        this.status = status;
    }
    public int getPlayerID() {
        return playerID;
    }
    public int getTotScore() {
        return totScore;
    }
    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    private int no_of_guess=0;
    public int guessaNum(int id) throws IOException,InterruptedException
    {

        Scanner s = new Scanner(new File(String.format("src\\Ginput%d",id)));
        int guess=0;
        int i=0;
        while(s.hasNextInt())
        {
            int temp=s.nextInt();
            if(i==this.no_of_guess)
            {
                guess=temp;
                this.no_of_guess++;
                break;
            }
            i++;
        }
        //For Simulation Purpose making the program sleep
        Thread.sleep(1000+(id%100));
        s.close();
        return guess;
    }
    public GuesserPoints getGamePoint() {
        return GamePoint;
    }
    public Grid getGrid() {
        return grid;
    }

    public void guessesUpdate(int guessNum)
    {
        String gstatus=grid.guessCheck(guessNum);
        if (gstatus=="WG")
        {
            GamePoint.updateWrongGuess();
            System.out.println("Wrong Guess!!");
        }
        else if(gstatus=="error")
        {
            System.out.println("Already Selected Correctly!!");
        }
        else
        {

            GamePoint.updateCorrectGuess(Integer.parseInt(gstatus));
            System.out.println("Wow!! Correct Guess");
        }
        //Displaying the points by updating in each guess in both console and GUI
        System.out.println("Current Points: "+GamePoint.getScore(Integer.parseInt(Grid.wrongSlotValue)));
        grid.getGStatus()[3].setText(String.valueOf(GamePoint.getScore(Integer.parseInt(Grid.wrongSlotValue))));
    }

}
