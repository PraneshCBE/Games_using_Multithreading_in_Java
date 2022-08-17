import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
public class TestDriver {
    static int count=0;
    public static void playerQualification(Player[] player)
    {

        ArrayList<Double> Scores=new ArrayList<>(6);
        for(int i=0;i<6;i++)
            Scores.add(player[i].getTot_Score());
        Collections.sort(Scores);
        for(int i=0;i<6;i++)
            if(Scores.get(count)==player[i].Tot_Score) {
                player[i].exit = true;
                player[i].Tot_Score=0;
                break;
            }
        if(count>=2)
            for(int j=0;j<6;j++)
                if(Scores.get(count+1)==player[j].Tot_Score) {
                    player[j].exit = true;
                    player[j].Tot_Score=0;
                }
        Player.pause=false;
        count++;
    }
    public static void Survey(Player[] player) throws InterruptedException
    {
        Thread.sleep(1000);
        System.out.println("\n\n**************Total Survey of the Game Show ******************");
        System.out.println("\nPlayer(s) Eliminated on Level 1 Game(Panchathandhiram):");
        for(int i=0;i<6;i++)
            if(player[i].getLevel_reach()==1)
                System.out.println(player[i].Personal_ID+" : "+player[i].Player_Name);
        System.out.println("\n\nPlayer(s) Eliminated on Level 2 Game(Maze):");
        for(int i=0;i<6;i++)
            if(player[i].getLevel_reach()==2)
                System.out.println(player[i].Personal_ID+" : "+player[i].Player_Name);
        System.out.println("\n\nPlayer(s) Eliminated on Level 3 Game(Klotski Riddle):");
        for(int i=0;i<6;i++)
            if(player[i].getLevel_reach()==3)
                System.out.println(player[i].Personal_ID+" : "+player[i].Player_Name);
        System.out.println("\n\nPlayer(s) Eliminated on Level 4 Game(Zip n Crip): Runner UP");
        for(int i=0;i<6;i++)
            if(player[i].getLevel_reach()==4)
                System.out.println(player[i].Personal_ID+" : "+player[i].Player_Name);
        System.out.println("\n\nThe Title WINNER is :");
        Thread.sleep(1200);
        for(int i=0;i<6;i++)
            if(player[i].getLevel_reach()==5)
                System.out.println(player[i].Personal_ID+" : "+player[i].Player_Name);
    }
    public static void main(String[] args) throws IOException,InterruptedException

    {
        Player[] player = new Player[6];
        BufferedReader b1 = new BufferedReader(new FileReader("src\\Player.txt"));

        //Retrieving Player Info from Files
        for(int i = 0; i < 6; i++)
        {
            String t;
            ArrayList<String> player_info = new ArrayList<>();
            while(!((t = b1.readLine()).equals("*")))
            {
                player_info.add(t);

            }
            player[i] = new Player(Integer.parseInt(player_info.get(0)),Integer.parseInt(player_info.get(1)), player_info.get(2));
        }

        //Creating and Starting Run() of the Threads for Each Player object
        Thread[] t =new Thread[6];
        for(int i=0;i<6;i++)
        {
            t[i]=new Thread(player[i]);
        }
        System.out.println("\n------------------WELCOME TO THE GREAT KARIGAALAN GAME SHOW------------------------------");
        System.out.println("Level 1 - Panchathandhiram Game : Possible Score = 100 and 6 Players");
        System.out.println("Level 2 - Maze Game             : Possible Score = 200 and 5 Players");
        System.out.println("Level 3 - Klotski Board Game    : Possible Score = 150 and 4 Players");
        System.out.println("Level 4 : Zip and Crip Game     : Possible Score = 100 and 2 Players");
        Scanner temp= new Scanner(System.in);
        temp.nextLine();

        //Panchathandhiram Game Starts - Level 1 with 6 Players
        t[1].start();
        t[2].start();
        t[3].start();
        t[4].start();
        t[5].start();
        t[0].start();

        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(65000);

        //Getting the Qualified Player List for next level and Killing the Eliminated Player's Thread
        playerQualification(player);
        System.out.println("=====================================================================");
        System.out.println("\nSelected Players for Maze Game:");
        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(810);
        //Maze Game Starts - Level 2 with 5 Players
        System.out.println("\n------------- WELCOME TO THE MAZE GAME -----------\n\n");
        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(9010);
        //Getting the Qualified Player List for next level and Killing the Eliminated Player's Thread
        playerQualification(player);
        System.out.println("=====================================================================");
        System.out.println("\nSelected Players for KLOTSKI BOARD Game:");
        //Klotski Board Game Starts - Level 3 with 4 Players
        Thread.sleep(940);

        System.out.println("\n----------------WELCOME TO THE KLOTSKI BOARD GAME ------------\n\n");
        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(29040);
        //Getting the Qualified Player List for next level and Killing the Eliminated Player's Thread
        playerQualification(player);
        System.out.println("=====================================================================");
        System.out.println("\nSelected Players for Zip n Crip Game: ");
        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(1040);
        //Klotski Board Game Starts - Level 3 with 4 Players
        System.out.println("\n----------------WELCOME TO THE ZIP N CRIP GAME ------------\n\n");
        System.out.println("<*****************************	Final Level		*****************************>\n");
        System.out.println("<*****************************   ZIP' N 'CRIP	*****************************>\n");
        System.out.println("<*****************************			*****************************>\n");
        System.out.println("_______Rules:_______\n");
        System.out.println("""
                1)All Crips have access to arena once they have cleared the previous games.
                2)The length of arena is entered by the judges according to the number of participants.
                3)The Crip will be given access to the arena to build it as he/she aspires.
                4)The main goal is to use the given Zips and clear the course in the minimum possible jumps.
                5)The winner will be the one with the least jumping arena.
                6)____ZIPS Provided____:
                	#4zip = 1
                	#3zip = 1
                	#2zip = 2
                	#1zip = infinite(can be used as many times as required.)

                """);
        //Making the Main Thread Sleep for Simulation Purpose
        Thread.sleep(9040);
        //Getting the Qualified Player List for next level and Killing the Eliminated Player's Thread
        playerQualification(player);
        System.out.println("=====================================================================");
        System.out.println("\n*****Calculating Results*****\n");
        Survey(player);

    }

}
