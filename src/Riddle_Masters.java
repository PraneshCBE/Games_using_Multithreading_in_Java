import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Riddle_Masters {

    int Least_Moves=0,Player_Moves,Player_Scores,ID,range;
    boolean Can_Solve;
    String Player_Status,current;
    ArrayList<Integer> Tile_Positions= new ArrayList<Integer>(3);
    Hashtable<Integer, Integer> pm = new Hashtable<>();
    Hashtable<String, String> retrace = new Hashtable<>();
    Klotski_Tiles kt2= new Klotski_Tiles();
    Hashtable<Integer,ArrayList<Integer>> indexmap= new Hashtable<Integer,ArrayList<Integer>>();
    ArrayList<Integer> arr= new ArrayList<Integer>();
    ArrayList<String> arr1= new ArrayList<String>();
    kr_gui g;




    //specifying the indexes to which it can exchange
    //012
    //345
    public Riddle_Masters(String name) {

        indexmap.put(0,new ArrayList<Integer>());
        indexmap.get(0).add(1);
        indexmap.get(0).add(3);

        indexmap.put(1,new ArrayList<Integer>());
        indexmap.get(1).add(0);
        indexmap.get(1).add(2);
        indexmap.get(1).add(4);

        indexmap.put(2,new ArrayList<Integer>());
        indexmap.get(2).add(1);
        indexmap.get(2).add(5);

        indexmap.put(3,new ArrayList<Integer>());
        indexmap.get(3).add(0);
        indexmap.get(3).add(4);

        indexmap.put(4,new ArrayList<Integer>());
        indexmap.get(4).add(1);
        indexmap.get(4).add(3);
        indexmap.get(4).add(5);

        indexmap.put(5,new ArrayList<Integer>());
        indexmap.get(5).add(2);
        indexmap.get(5).add(4);
        g=new kr_gui(name);

    }

    public int getLeast_Moves() {
        return Least_Moves;
    }

    // setting player id, original tile numbers, jumbled tile positions
    public void setidtilenospos(ArrayList<Integer> Tile_Positions) {
        this.Tile_Positions = Tile_Positions;
    }

    public void setLeast_Moves(int Least_Moves) {

        this.Least_Moves = Least_Moves;
    }

    //contains ID and playermoves

    public void setPlayer_Moves(Hashtable<Integer,Integer> pm) {

        this.pm = pm;
    }

    public boolean getCan_Solve() {
        return Can_Solve;
    }

    public void setCan_Solve(boolean can_Solve) {
        this.Can_Solve = can_Solve;
    }

    public int getPlayer_Moves() {
        return pm.get(ID);
    }

    public void setPlayerID(int ID){
        this.ID=ID;
    }


    //calculating the least possible way of solving by using bfs method
    public int KlotskiRiddleSolving() {

        retrace.put(String.valueOf(Tile_Positions.get(2)),"");
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<String>();
        //giving initial jumbled position as input

        q.add(String.valueOf(Tile_Positions.get(2)));
        visited.add(q.peek());

        while(!q.isEmpty()) {
            range=q.size();
            for(int i=0;i<range;i++) {
                current=q.poll();


                if(current.equals("123450")) {
                    setCan_Solve(true);
                    return Least_Moves;
                }

                else{

                    int zero;
                    char t;
                    StringBuilder current1=new StringBuilder();
                    //finding index of 0 in the string
                    zero=current.indexOf('0');

                    arr.clear();
                    for(int element : indexmap.get(zero)) {
                        arr.add(element);
                    }

                    current1.append(current);
                    for(int j=0;j<arr.size();j++) {
                        t=current1.charAt(arr.get(j));
                        current1.setCharAt(zero,t);
                        current1.setCharAt(arr.get(j),'0');


                        if(!visited.contains(current1.toString())) {
                            retrace.put(current1.toString(),current);
                            visited.add(current1.toString());
                            q.add(current1.toString());
                        }
                        current1.delete(0, 6);
                        current1.append(current);
                    }

                }

            }

            Least_Moves+=1;

        }
        setCan_Solve(false);
        return -1;

    }

    public Boolean winnerloser() {
        Riddle_Solver rs3= new Riddle_Solver();
        if(getPlayer_Moves()==getLeast_Moves()) {
            rs3.setPlayerStatus(true);
            return true;
        }

        else {
            rs3.setPlayerStatus(false);
            return false;
        }
    }

    public void pathtracing() {
        arr1.clear();
        String key="123450",sub;
        arr1.add(key);

        System.out.print("["+key.substring(0,3)+"]"+"["+key.substring(3,6)+"]"+"<-");


        while(retrace.get(key)!="") {
            sub=retrace.get(key);
            arr1.add(sub);

            System.out.print("["+sub.substring(0,3)+"]"+"["+sub.substring(3,6)+"]"+"<-");
            key=retrace.get(key);
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=arr1.size()-1;i>=0;i--) {
            for(int j=0;j<6;j++) {
                String ch=arr1.get(i).split("")[j];
                g.b[j].setText(ch);
                if(ch.equals("0")) {
                    g.b[j].setBackground(Color.white);
                    g.b[j].setForeground(Color.black);
                }
                else {
                    g.b[j].setBackground(Color.DARK_GRAY);
                    g.b[j].setForeground(Color.white);
                }
            }

            g.frame.setVisible(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(i==0) {
                for(int j=0;j<6;j++) {
                    g.b[j].setBackground(Color.green);
                    g.frame.setVisible(true);

                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            g.frame.setVisible(false);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        g.frame.dispose();
        System.out.println("");

    }

}

