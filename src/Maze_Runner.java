import java.util.*;

public class Maze_Runner extends Player {
    private ArrayList<String> move;
    private Cells destroyed_blocks;
    private boolean isMovePossible;
    private LinkedList<Cells> path = new LinkedList<>();
    private double distance;
    private double distance_difference;
    public double score;
    private boolean selected_status;


    public void setDestroyedBlock(Cells destroyed_blocks) {
        this.destroyed_blocks = destroyed_blocks;
    }

    public boolean calculateMovePossible(Cells c[],int t,String i) {
        boolean x = true;

        if(i.equals("right") && (c[t].getIsBlocked() && c[t].equals(destroyed_blocks) || !(c[t].getIsBlocked())))
            x = true;
        else if(i.equals("diagonal") && ((c[t].getIsBlocked() && (c[t].equals(destroyed_blocks)) || !(c[t].getIsBlocked()))  || ((c[t+5].getIsBlocked() && c[t+5].equals(destroyed_blocks)) || !(c[t+5].getIsBlocked()))))
            x = true;
        else if(i.equals("down") && (c[t+5].getIsBlocked() && c[t+5].equals(destroyed_blocks) || !(c[t+5].getIsBlocked())))
            x = true;
        else x = false;

        return x;
    }

    public void setMove(ArrayList<String> move) {
        this.move = move;
    }

    public void setStatus()
    {
        this.selected_status = (this.score >= 8);
    }



    public Cells getDestroyedBlock() {
        return destroyed_blocks;
    }

    public void setDifference(Maze_Specialist m1) {
        this.distance_difference = m1.calculateDifference();
        System.out.printf("\nDifference of Runner's path from shortest path :  %.2f metres",this.distance_difference);
    }

    public LinkedList<Cells> calculatePath(Cells c[]) {
        int t = 1;
        path.add(c[t-1]);
        for(String i : move)
        {
            if(i.equals("diagonal") && calculateMovePossible(c,t,i))
            {
                t += 7;
                path.add(c[t-1]);
            }
            else if(i.equals("right") && calculateMovePossible(c,t,i))
            {
                t += 1;
                path.add(c[t-1]);
            }
            else if(i.equals("down") && calculateMovePossible(c,t,i))
            {
                t += 6;
                path.add(c[t-1]);
            }
            else if(i.equals("up") && calculateMovePossible(c,t,i))
            {
                t -= 6;
                path.add(c[t-1]);
            }

        }

        return path;
    }



    public double calculateDistance() {
        double sum1 = 0.0;
        for(int i = 0; i < path.size()-1;i++)
            sum1 += Math.pow(Math.pow(path.get(i+1).getCoordinate().get(0)- path.get(i).getCoordinate().get(0),2.0) + Math.pow(path.get(i+1).getCoordinate().get(1)- path.get(i).getCoordinate().get(1),2.0),0.5);
        distance = sum1;
        return distance;
    }

    public double getDifference() {
        return this.distance_difference;
    }


}
