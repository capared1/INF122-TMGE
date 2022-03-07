package TMGE;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class Board {

    public Tile [][] grid;
    public int remainingTiles;
    public String playerName;
    public HashMap<String, Integer> scoreboard;
    public boolean p1Turn;
    protected JPanel boardPanel;
    protected Point size;
    protected TileFactory factory;



    public abstract void setup();
    public abstract void update();

    public JPanel getBoardPanel(){
        return this.boardPanel;
    }

    public void addTile(Tile tile, Point coord){
        this.grid[coord.x][coord.y] = tile;
        tile.setCoord(coord);
    }


    public Board(Point size, TileFactory factory){
        this.size = size;
        this.factory = factory;
        this.grid = new Tile[size.y][size.x];
        this.boardPanel = new JPanel(new GridLayout(size.y, size.y));
        this.remainingTiles = size.x * size.y;
        this.p1Turn = true;

        this.scoreboard = new HashMap<String, Integer>();
        this.scoreboard.put("P1", 0);
        this.scoreboard.put("P2", 0);

    }
}
