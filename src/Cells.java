
import java.util.*;
public class Cells {
    private boolean isblocked;
    private ArrayList<Integer> co_ordinate;
    private int cell_no;
    private HashMap<ArrayList<Integer>,Integer> cellmap = new HashMap<>();
    private boolean isdestroyed;

    public Cells(ArrayList<Integer> co_ordinate, boolean isblocked,int cell_no) {
        this.isblocked = isblocked;
        this.co_ordinate = co_ordinate;
        this.cell_no = cell_no;
        this.cellmap.put(co_ordinate,cell_no);
    }

    public void setCoordinate(ArrayList<Integer> co_ordinate) {
        this.co_ordinate = co_ordinate;
    }

    public void setIsBlocked(boolean isblocked) {
        this.isblocked = isblocked;
    }

    public void setCellNo(int cell_no) {
        this.cell_no = cell_no;
    }

    public void setCellMap(HashMap<ArrayList<Integer>,Integer> cellmap) {
        this.cellmap = cellmap;
    }

    public void setIsDestroyed(boolean isdestroyed) {
        this.isdestroyed = isdestroyed;
    }

    public ArrayList<Integer> getCoordinate(){
        return co_ordinate;
    }

    public boolean getIsBlocked() {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,14,16,18,28,32)); //checks whether the cell is blocked
        if(a.contains(this.cell_no))
            return true;
        return false;
    }

    public HashMap<ArrayList<Integer>,Integer> getCellMap() {
        return cellmap;
    }

    public boolean getIsDestroyed() {
        return isdestroyed;
    }

    public int getCellNo()
    {
        return this.cell_no;
    }


}



