//Packages for Algorithm
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.lang.Math;

//Packages for GUI and Sound
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.sound.sampled.*;

public class Grid {

        private JFrame f=new JFrame("Panchithandhiram");
        private JButton[] cell=new JButton[25];
        private JLabel[] GStatus=new JLabel[10];
        private JLabel[] G=new JLabel[5];
        private final int totCells;
        private HashMap<Integer,String> cells;
        private int guessNum;
        private HashMap<Integer,String> foundCells;
        private OrgPcells op[]=new OrgPcells[5];
        private int hidOrgCells;
        public static String wrongSlotValue="-1";
        public boolean completion=false;


        public Grid(int totCells)
        {
            //initializing the Original cells with their jackpot scores
            for(int i=0,j=10;i<5;i++,j=j+5)
                op[i]=new OrgPcells(j);

            this.totCells=totCells;
            cells=new HashMap<Integer,String>(totCells);
            this.hidOrgCells=5;

            //fixing the first random order of the cells
            this.fixCellOrder();

            this.guessNum=0;
            foundCells=new HashMap<Integer,String>(5);

            //Initializing the GUI Frame with layout
            this.GridGUI_Init();
            f.setLayout(new GridLayout(8,5));
            f.setSize(450,350);;
        }

        public void GridGUI_Init()
        {
            int i=0;
            G[0]=new JLabel("Guess",SwingConstants.RIGHT);
            G[1]=new JLabel("the",SwingConstants.CENTER);
            G[2]=new JLabel("Original",SwingConstants.RIGHT);
            G[3]=new JLabel("Slots",SwingConstants.RIGHT);
            G[4]=new JLabel(" ");
            for(i=0;i<5;i++)
            {
                G[i].setBackground(Color.white);
                G[i].setForeground(Color.BLUE);
                G[i].setOpaque(true);
                G[i].setFont(new Font("Tahoma",Font.BOLD,20));
                f.add(G[i]);
            }

            //buttons take 2-6 rows
            i=0;
            for(Map.Entry<Integer,String> c: cells.entrySet())
            {
                cell[i]=new JButton(Integer.toString(c.getKey()));
                i++;
            }
            for(i=0;i<this.totCells;i++)
            {
                f.add(cell[i]);
            }

            //Using 5 labels in 6th row to pretend like a text in the Layout for updating the Guesser's Game Status
            GStatus[0]=new JLabel(" ");
            GStatus[1]=new JLabel("Current",JLabel.CENTER);
            GStatus[2]=new JLabel("Points :");
            GStatus[3]=new JLabel("0",SwingConstants.CENTER);
            Border bl=BorderFactory.createLineBorder(Color.GREEN);
            GStatus[3].setBorder(bl);
            GStatus[4]=new JLabel(" ");
            GStatus[5]=new JLabel(" ");
            GStatus[6]=new JLabel(" ");
            GStatus[7]=new JLabel(" ");
            GStatus[8]=new JLabel(" ");
            GStatus[9]=new JLabel(" ");
            for(i=0;i<10;i++)
            {
                GStatus[i].setBackground(Color.white);
                GStatus[i].setForeground(Color.red);
                GStatus[i].setOpaque(true);
                GStatus[i].setFont(new Font("Tahoma",Font.BOLD,20));
                f.add(GStatus[i]);
            }
            GStatus[3].setForeground(Color.BLUE);
        }


        public void fixCellOrder()
        {
            int max=this.totCells+1;
            int min=1;
            int pos[]=new int[this.hidOrgCells];

            //generating 5 random positions for the Original cells in the Grid
            for(int i=0;i<this.hidOrgCells;i++)
                pos[i]=(int)(Math.random()*(max-min+1)+min);
            //checking for repeated position and changing it
            for(int i=0;i<this.hidOrgCells;i++)
            {
                for(int j=i+1;j<this.hidOrgCells;j++)
                {
                    if(pos[i]==pos[j]||cells.get(pos[i])=="x")
                    {
                        pos[i]=(int)(Math.random()*(max-min+1)+min);
                    }
                }
            }

            //fixing all cells value as Wrong Slot Value at first
            for(int i=1;i<=this.totCells;i++)
            {
                if(cells.get(i)!="x")
                    cells.put(i, wrongSlotValue);

            }

            //changing the values of OrgCells as their respective values
            ArrayList<String> temp=new ArrayList<>();
            for(int i=0;i<this.hidOrgCells;i++)
            {
                if(this.foundCells==null)
                {
                    cells.replace(pos[i],op[i].toString());
                }
                else
                {
                    // if there is some found cells in the Grid..except those grids other slots are changed
                    for(int j=0;j<5;j++)
                    {
                        if(!this.foundCells.containsValue(op[j].toString()) && temp.contains(op[j].toString())==false)
                        {
                            cells.replace(pos[i],op[j].toString());
                            temp.add(op[j].toString());
                            break;
                        }
                    }
                }
            }

        }


        //Displaying Grid
        public void dispGrid()
        {
            for(Map.Entry<Integer,String> c: cells.entrySet())
            {
                if(c.getValue()=="x")
                {
                    cell[c.getKey()-1].setText(c.getValue());
                }
            }
            f.setVisible(true);

        }


        public String guessCheck(int gn)
        {

            //Respective JButton color will be changed according the correctness for 1 second
            if(cells.get(gn)==wrongSlotValue)
            {
                try {
                    //Audio for Wrong Guess
                    AudioInputStream ais=AudioSystem.getAudioInputStream(new File("src\\wasound1.wav"));
                    Clip clip=AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                    //Coloring for Wrong Guess
                    cell[gn-1].setBackground(Color.RED);
                    cell[gn-1].setText(wrongSlotValue);

                    Thread.sleep(1000);
                    cell[gn-1].setText(String.valueOf(gn));
                    cell[gn-1].setBackground(null);
                    dispGrid();
                    return "WG";
                }catch(Exception e) {System.out.println(e);}
                return "WG";
            }
            else if(cells.get(gn)=="x")
            {
                try {
                    //Coloring for Already Selected
                    cell[gn-1].setBackground(Color.yellow);
                    Thread.sleep(1000);
                    cell[gn-1].setBackground(Color.green);
                    return "error";
                }catch(Exception e) {System.out.println(e);}
                return "error";
            }
            else
            {
                try {
                    //Audio Clip for Correct Guess
                    AudioInputStream ais=AudioSystem.getAudioInputStream(new File("src\\cgsound.wav"));
                    Clip clip=AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();

                    //Coloring for Correct Guess
                    cell[gn-1].setBackground(Color.GREEN);
                    cell[gn-1].setText("["+cells.get(gn)+"]");
                    cell[gn-1].setFont(new Font("TimesNewRoman",Font.BOLD,20));
                    Thread.sleep(1000);
                    dispGrid();

                    //adding the values of the found cells and the found position in found cells Hash Map
                    this.foundCells.put(gn,cells.get(gn));
                    //replacing the value to x for better usage in the Grid
                    cells.replace(gn,"x");
                    //decrementing the Hidden Original cells count
                    updateHiddenOrgCells();
                    //returning the value of the found cell to guess Update
                    return foundCells.get(gn);
                }catch(Exception e) {System.out.println(e);}
                return foundCells.get(gn);
            }
        }

        public JLabel[] getGStatus() {
            return GStatus;
        }
        public JFrame getF() {
            return f;
        }
        public JLabel[] getG() {
            return G;
        }
        public HashMap<Integer, String> getCells() {
            return cells;
        }
        public void setCells(HashMap<Integer, String> cells) {
            this.cells = cells;
        }
        public int getGuessNum() {
            return guessNum;
        }
        public void setGuessNum(int guessNum) {
            this.guessNum = guessNum;
        }
        public HashMap<Integer, String> getFoundCells() {
            return foundCells;
        }
        public void setFoundCells(HashMap<Integer, String> foundCells) {
            this.foundCells = foundCells;
        }
        public void updateHiddenOrgCells()
        {
            this.hidOrgCells--;
        }
}
