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
            return new BombTile(board, new Color(230,66,112), "B");
        }
        else if(type.equals("green")){
            return new StaticTile(board, new Color(100,193, 189), "");
        }
        else if(type.equals("yellow")){
            return new StaticTile(board, new Color(234,215, 98), "");
        }
        else {
            return new StaticTile(board, new Color(227,230, 232), "");
        }
    }
}
