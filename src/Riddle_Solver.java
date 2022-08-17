import java.util.*;
import java.util.ArrayList;

public class Riddle_Solver extends Player {

    String Player_Name,Time_Taken;
    int Player_Score,Player_ID;
    boolean Winner,Runner;
    boolean Status;
    //Riddle_Masters rm= new Riddle_Masters();
    Klotski_Tiles kt1= new Klotski_Tiles();
    Klotski_Board kb1= new Klotski_Board();


    public Riddle_Solver() {

    }
    public Riddle_Solver(int ID, String name){
        this.Player_ID=ID;
        this.Player_Name=name;
        //rm.setPlayerID(this.Player_ID);
        kt1.setPlayerID(this.Player_ID);

    }

	/*
	 public int getScore() {
		return Player_Score;
	}
	*/

    public boolean getPlayerStatus() {
        return Status;
    }

    public void setPlayerStatus(boolean status) {
        this.Status = status;
    }



}
