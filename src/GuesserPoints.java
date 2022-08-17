public class GuesserPoints
{
    private int correctGuess;
    private int wrongGuess;
    private int score;
    public GuesserPoints() {
        //super();
        this.correctGuess = 0;
        this.wrongGuess = 0;
        this.score= 0;
    }
    public int getCorrectGuess() {
        return correctGuess;
    }
    public void updateCorrectGuess(int val) {
        this.correctGuess +=val;
    }
    public int getWrongGuess() {
        return wrongGuess;
    }
    public void updateWrongGuess() {
        this.wrongGuess += 1;
    }
    public int getScore(int wrngGuessVal) {
        calcScore(wrngGuessVal);
        return score;
    }
    public void calcScore(int wrngGuessVal) {
        this.score=this.correctGuess+(this.wrongGuess*wrngGuessVal);
    }


}
