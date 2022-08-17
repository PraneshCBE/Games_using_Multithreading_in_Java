import java.util.ArrayList;

public abstract class Judge_Panel
{
    protected int PlayerScore;
    protected ArrayList Player_Status=new ArrayList();
    public Judge_Panel()
    {
        this.PlayerScore=0;
    }
    public int getPlayerScore() {
        return PlayerScore;
    }
    public void setPlayerScore(int playerScore) {
        PlayerScore = playerScore;
    }
    public ArrayList getPlayer_Status() {
        return Player_Status;
    }
    public abstract void setPlayer_Status(ArrayList Player_Status);



}
