package Klax;

import TMGE.Board;
import TMGE.Tile;
import TMGE.TileFactory;

import java.awt.*;

public class KlaxTileFactory extends TileFactory {


    public KlaxTileFactory()
    {
        super();
    }

    /*
        Will need to make custom tiles subclasses?
            Controllable Player's Paddle
            Normal Tile   ~   Semi-Implemented
            Wild Tile   ~   Semi-Implemented
    */

    @Override
    public Tile create(String type, Board board)
    {
        if (type.equals("deliveryA"))   //  Top Zone & Even Column
        {
            return new Tile(board, new Color(137,98,121), "");  //  Mauve Taupe
        }
        else if (type.equals("deliveryB"))  //  Top Zone & Odd Column
        {
            return new Tile(board, new Color(173,178,211), "");  //  Wild Blue Yonder
        }
        else if (type.equals("dump"))   //  Dump Zone
        {
            return new Tile(board, new Color(22,22,29), "");  //  Eigengrau
        }
        else if (type.equals("player")) //  Player's Paddle
        {
            return new Tile(board, new Color(112,106,110), "Paddle");  //  Dark Taupe Gray
        }
        else if (type.equals("carryall")) //  Player's Paddle
        {
            return new Tile(board, new Color(0,0,0), "");  //  Black
        }
        else if (type.equals("normie")) //  Normal Klax Tile
        {
            return new KlaxTile(board, null, "");
        }
        else if (type.equals("wild")) //  Wild Klax Tile
        {
            return new KlaxWildTile(board, null, "");
        }
        else    //  Nonexistent Type
        {
            try
            {
                return new Tile(null, null, "Why are we still here? Just to suffer?");  //  Should create a broken tile
            }
            catch(Exception e)
            {
                System.out.println("Factory attempted to make a Klax tile with an incorrect input type: '"+type+"'.");
            }
        }
    }
}
