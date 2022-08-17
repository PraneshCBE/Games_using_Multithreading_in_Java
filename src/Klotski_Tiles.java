import java.util.*;



public class Klotski_Tiles {

    int No_Of_Tiles,Tile_Numbers, Tile_Positions,ID;
    ArrayList<Integer> Tile_Number_Positions= new ArrayList<Integer>(3);

    public Klotski_Tiles(){

    }

    public Klotski_Tiles(int number){
        setTile_Numbers(number);

    }



    public ArrayList<Integer> getTile_Position() {
        // setting player id,original tile numbers,jumbled tile positions
        Tile_Number_Positions.add(ID);
        Tile_Number_Positions.add(Tile_Numbers);
        Tile_Number_Positions.add(Tile_Positions);

        return Tile_Number_Positions;
    }

    public void setTile_Numbers(int tile_Numbers) {
        this.Tile_Numbers = tile_Numbers;
    }


    public void setTile_Positions(int tile_Positions) {
        this.Tile_Positions = tile_Positions;
    }

    public void setPlayerID(int ID) {
        this.ID=ID;
    }
}
