package PopBlockPuzzle;

import TMGE.Board;
import TMGE.Tile;

import java.awt.*;

public class BombTile extends Tile {
    public BombTile(Board board, Color color, String Label) {
        super(board, color, Label);
    }

    @Override
    public void trigger() {
        for (int i = -1; i <= 1; i ++ ){
            for (int j = -1; j <= 1; j ++){
                try{
                    if(! board.grid[coord.x + i][coord.y + j].isEnabled()){
                        continue;
                    }

                    board.grid[coord.x + i][coord.y + j].setEnabled(false);
                    board.grid[coord.x + i][coord.y + j].setBackground(Color.GRAY);
                    board.remainingTiles--;

                    if (board.grid[coord.x + i][coord.y + j].isMatch(this)) {
                        board.grid[coord.x + i][coord.y + j].trigger();
                    }
                }
                catch (Exception e){
                    continue;
                }
            }
        }
    }

    @Override
    public Boolean isMatch(Tile other) {
        return other instanceof BombTile;
    }

    @Override
    public Object getValue() {
        return "BombTile";
    }
}
