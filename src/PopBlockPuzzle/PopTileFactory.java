package PopBlockPuzzle;

import TMGE.Board;
import TMGE.Tile;
import TMGE.TileFactory;

import java.awt.*;
import java.util.HashMap;

public class PopTileFactory extends TileFactory {


    public PopTileFactory() {
        super();
    }

    @Override
    public Tile create(String type, Board board) {
        HashMap<String, Color> colorMap = new HashMap<String, Color>();

        colorMap.put("bomb", new Color(230,66,112));
        colorMap.put("green", new Color(56,219, 83));
        colorMap.put("yellow", new Color(234,215, 98));
        colorMap.put("blue", new Color(56,72, 219));
        colorMap.put("blank", new Color(227,230, 232));

        if(!colorMap.containsKey(type)){
            return new StaticTile(board, colorMap.get("blank"), "");
        }
        else if(type.equals("bomb")){
            return new BombTile(board, colorMap.get(type), "B");
        }
        else {
            return new StaticTile(board, colorMap.get(type), "");
        }
    }
}
