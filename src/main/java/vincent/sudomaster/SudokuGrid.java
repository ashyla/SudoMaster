package vincent.sudomaster;



public class SudokuGrid {

    private int id;
    private int level;
    private int num;
    private int done;
    private String grid;
    private String playerGrid;
    private int chrono;

    public SudokuGrid(int id, int level, int num, int done, String grid){
        this.level = level;
        this.num = num;
        this.done = done;
        this.grid = grid;
        this.id = id;
    }

    public int getDone(){
        return done;
    }

    public void setDone(int done){
        this.done = done;
    }

    public int getNum(){
        return num;
    }

    public int getLevel(){
        return level;
    }

    public String getGrid(){
        return grid;
    }

    public String getPlayerGrid(){
        return playerGrid;
    }

    public int getChrono(){
        return chrono;
    }

    public void setChrono(int chrono){
        this.chrono = chrono;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return "Grid n°"+num+" level "+level+" \n "+done+"%";
    }

    public void setPlayerGrid(String newGrid){
        playerGrid = newGrid;
    }
}
