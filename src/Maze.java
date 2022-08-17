import java.util.*;

public class Maze {
    private final int noOfCells = 36;
    private ArrayList<Cells> block_cells;
    private ArrayList<Cells> path_cells;


    public Maze(ArrayList<Cells> block_cells,ArrayList<Cells> path_cells)
    {

        this.block_cells = block_cells;
        this.path_cells = path_cells;

    }

    public int getnoOfCells() {
        return noOfCells;
    }

    public ArrayList<Cells> getBlockCells() {
        return block_cells;
    }

    public ArrayList<Cells> getPathCells() {
        return path_cells;
    }


    public void setAll(ArrayList<Cells> block_cells,ArrayList<Cells> path_cells) {

        this.block_cells = block_cells;
        this.path_cells = path_cells;
    }

    public void setChangedBlock() {
        if(!(new Maze_Specialist().calculateIsPossible(this)))
        {
            System.out.println("Blocks positions are invalid");

        }

    }


}
