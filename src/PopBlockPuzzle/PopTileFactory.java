package PopBlockPuzzle;

import TMGE.Board;
import TMGE.Tile;
import TMGE.TileFactory;

import java.awt.*;

public class PopTileFactory extends TileFactory {


    public PopTileFactory() {
        super();
    }

    @Override
    public Tile create(String type, Board board) {
        if(type.equals("bomb")){
            return new BombTile(board, Color.yellow, "BOMB");
        }
        else if(type.equals("red")){
            return new StaticTile(board, Color.red, "");
        }
        else if(type.equals("blue")){
            return new StaticTile(board, Color.blue, "");
        }
        else {
            return new StaticTile(board, Color.white, "");
        }
    }
}
