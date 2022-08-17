import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
public class Player extends Judge_Panel implements Runnable
{
    private int Age;
    protected int Personal_ID;
    protected String Player_Name;
    private static ArrayList Player_Status;
    //protected String Time_taken;
    protected boolean Winner;
    protected boolean Runner;
    protected double Tot_Score;
    public static boolean pause=false;
    public boolean exit=false;
    private int level_reach=0;
    public ArrayList<Double> Scores = new ArrayList<>();

    public Player()
    {
        // just for good usage
    }

    @Override
    public void setPlayer_Status(ArrayList Player_Stat)
    {
        Player_Status=Player_Stat;
    }

    public Player(int age,int id,String name)
    {
        super();
        this.Age=age;
        this.Personal_ID=id;
        this.Player_Name=name;
        this.Winner=false;
        this.Runner=false;
        this.Tot_Score=0;
    }
    public void run()
    {
        Guesser g1=new Guesser(this.Personal_ID,0,"S",5);
        int xn=1;
        long begin=System.currentTimeMillis();
        long end=0;
        this.level_reach++;
        g1.getGrid().getF().setTitle(String.format("Panchathandhiram : %s",this.Player_Name));
        do
        {
            g1.getGrid().dispGrid();
            int guess= 0;
            try {
                guess = g1.guessaNum(this.Personal_ID);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nPlayer "+this.Player_Name+" Guess: "+guess);
            g1.guessesUpdate(guess);
            end=System.currentTimeMillis();
            if((end-begin)>=30000*xn && (end-begin)<60000)
            {
                System.out.println("\nRe-Arranging the Grid ;)\n");
                g1.getGrid().fixCellOrder();
                g1.getGrid().getGStatus()[6].setText("Grid");
                g1.getGrid().getGStatus()[7].setText("Shuffled");
                xn++;
            }

        }while((end-begin)<60000 && g1.getGrid().getFoundCells().size()<5);
        g1.getGrid().getGStatus()[1].setText("Total");
        g1.getGrid().getGStatus()[2].setText("Score :");
        g1.getGrid().getF().setVisible(true);
        this.setTot_Score(g1.getGamePoint().getScore(Integer.parseInt(Grid.wrongSlotValue)));
        if((end-begin)<60000) {
            try {
                Thread.sleep(60000 -(end-begin));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Panchathandhiram Score of Player "+this.Personal_ID+": "+this.getTot_Score());
        g1.getGrid().getF().dispose();
        pause=true;
        while(pause)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // ----------------------------------MAZE GAME------------------------------//
        if(!exit)
        {
            System.out.println(this.Player_Name);
            this.level_reach++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(this){

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(String.format("src/mazegame%d.txt",this.Personal_ID)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String f = null;
            try {
                f = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String t;
                System.out.println("Number of Cells : 36\n");
                System.out.println(f);
            ArrayList<Integer> block_cells = new ArrayList<>();
            t=null;
            while(true)
            {
                try {
                    t = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(t.equals("*"))
                    break;
                else {
                    block_cells.add(Integer.parseInt(t.trim()));
                    System.out.print(t + " ");
                }
            }
            System.out.println("\n");
            System.out.println("Path Cells ");
            for(int i = 1; i <= 36; i++)
            {
                if(!block_cells.contains(i))
                    System.out.print(i + " ");
            }
            System.out.println("\n");

            Cells c[] = new Cells[36];
            int j = 1;
            for(int i = 0; i < 36; i++)												//cell objects created along with co-ordinates
            {
                ArrayList<Integer> pairs = new ArrayList<>();
                pairs.add(i/6+1);
                pairs.add(j);
                if(!block_cells.contains(i))

                    c[i] = new Cells(pairs,false,i+1);
                else
                    c[i] = new Cells(pairs,true,i+1);
                if(j < 6)
                    j++;
                else
                    j = 1;

            }

            ArrayList<Cells> blocked = new ArrayList<>();				//blocked cells
            ArrayList<Cells> notblocked = new ArrayList<>(); 			//unblocked cells

            for(int i = 0; i < 36; i++)
            {
                if(c[i].getIsBlocked())
                    blocked.add(c[i]);
                else
                    notblocked.add(c[i]);
            }

            for(int i = 0; i < 36; i++)
                c[i].setIsDestroyed(i+1 == 2 || i+1 == 14 || i+1 == 16 || i+1 == 18 || i+1 == 28 || i+1 == 32);


            Maze maze = new Maze(blocked,notblocked);

            Maze_Runner runner = new Maze_Runner();
            try {
                f = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n" + f);

            while(true)
            {
                try {
                    if ((t = br.readLine()).equals("*")) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runner.setDestroyedBlock(c[Integer.parseInt(t)-1]);
                System.out.println(t+"\n");
            }

            ArrayList<String> moves = new ArrayList<>();
            try {
                f = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true)
            {
                try {
                    if ((t = br.readLine()).equals("*")) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                moves.add(t.trim());
            }

            runner.setMove(moves);

            LinkedList<Integer> runner_path = new LinkedList<>();
            for(Cells i : runner.calculatePath(c))
            {
                runner_path.add(i.getCellNo());
            }
            System.out.println("\nRunner "+this.Player_Name+"'s path : " + runner_path.stream().map(Object::toString).collect(Collectors.joining(" -> ")));
            System.out.printf("\nDistance moved by %s from Start to Target : %.2f metres\n",this.Player_Name,runner.calculateDistance());
            Maze_Specialist specialist = new Maze_Specialist();
            specialist.findPaths(blocked, notblocked);
            specialist.setRunnerData(runner);
            runner.setDifference(specialist);
            specialist.calculateScore();
            System.out.println("\n_______________________________________________________________________________________________________________\n");
            this.setTot_Score(runner.score);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("\nMaze Game Score by Player %s : %.0f\n",this.Player_Name,this.getTot_Score());
            pause=true;
            while(pause)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //---------------------------------KLOTSKI RIDDLE---------------------------------//
            if(!exit) {
                System.out.println(this.Player_Name);
                this.level_reach++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //synchronized(this) {
                int ID, tilenos, playermoves, tilenos1;
                String name;
                ArrayList<Integer> arr = new ArrayList<>(3);

                Hashtable<Integer, Integer> hash = new Hashtable<>();// for storing playerID and playermoves
                BufferedReader f = null;
                try {
                    f = new BufferedReader(new FileReader(String.format("src/Klotski%d.txt", this.Personal_ID)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String x = "";
                ArrayList chumma = new ArrayList();
                while (true) {
                    try {
                        if (!((x = f.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chumma.add(x);
                }

                ID = this.Personal_ID;
                name = this.Player_Name;
                tilenos = Integer.parseInt((String) chumma.get(0));// tilenos for checking(final state)
                tilenos1 = Integer.parseInt((String) chumma.get(1));// tilepositions to play
                playermoves = Integer.parseInt((String) chumma.get(2));


                //use hashtable for storing
                hash.put(ID, playermoves);

                //calling getter to store ID and Name in Klotski_Board
                Klotski_Board kb = new Klotski_Board();
                kb.setNo_Of_Moves(hash);

                //setting playerID, Name
                Riddle_Solver rs = new Riddle_Solver(ID, name);

                //setting tile nos for checking

                Klotski_Tiles kt = new Klotski_Tiles(tilenos);

                //setting tile positions for playing

                kt.setTile_Positions(tilenos1);


                //setting playermoves
                Riddle_Masters rm3 = new Riddle_Masters(this.Player_Name);
                rm3.setPlayerID(ID);
                rm3.setPlayer_Moves(kb.getNo_Of_Moves());
                rm3.setidtilenospos(kt.getTile_Position());
                rm3.KlotskiRiddleSolving();

                System.out.println("\nBoard Size: " + kb.getBoard_Size());
                System.out.println("Moves made by the player " + this.Player_Name + " : " + rm3.getPlayer_Moves());
                System.out.println("Least Moves: " + rm3.getLeast_Moves() + "\n");
                rm3.pathtracing();
                System.out.println("_________________________________________________________________________________________");
                this.setTot_Score(150 - (rm3.getPlayer_Moves() - rm3.getLeast_Moves()) * 10);
                //}
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("\nKlotski Riddle Score by Player %s : %.0f\n", this.Player_Name, this.getTot_Score());
                pause = true;
                while (pause) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //____________________ZIP N CRIP------------------------------//
                if (!exit) {
                    System.out.println(this.Player_Name);
                    this.level_reach++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Crip c = new Crip();
                    c.playerAction(this.Personal_ID, this.Player_Name);
                    System.out.println("\n_______________________________________________________________________________________________________________\n");
                    this.setTot_Score(100-(c.getMinMoves()*10));

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("\nZip n Crip Score by Player %s : %.0f\n", this.Player_Name, this.getTot_Score());
                    pause = true;
                    while (pause) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!exit) {
                        this.level_reach++;
                    }
                }
            }
        }
    }
    public double getTot_Score() {
        return Tot_Score;
    }

    public int getLevel_reach() {
        return level_reach;
    }

    public void setTot_Score(double tot_Score) {
        Tot_Score =tot_Score;
        Scores.add(tot_Score);
    }
}
