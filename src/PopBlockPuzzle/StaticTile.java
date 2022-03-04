package PopBlockPuzzle;

import TMGE.Board;
import TMGE.Tile;

import java.awt.*;

public class StaticTile extends Tile {
    public StaticTile(Board board, Color color, String Label) {
        super(board, color, Label);
    }

    @Override
    public void trigger() {
        pop(coord.x, coord.y);
    }

    void pop(int x, int y){
        try {
            if(! ((StaticBoard) board).grid[x][y].isEnabled()){
                return;
            }
            if (board.grid[x][y].isMatch(this)){
                board.grid[x][y].setEnabled(false);
                board.remainingTiles--;
                board.grid[x][y].setBackground(Color.GRAY);
                pop(x + 1, y);
                pop(x - 1, y);
                pop(x, y + 1);
                pop(x, y - 1);
            } else {
                return;
                //System.out.println("Not Equal");
            }
        }catch (Exception e){
            return;
        }
    }

    @Override
    public Boolean isMatch(Tile other){
        return this.getValue().equals(other.getValue());
    }

    @Override
    public Object getValue() {
        return this.color;
    }
}
