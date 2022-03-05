package Klax;

import TMGE.*;

import java.awt.*;

public class KlaxBoard extends Board {
    public StaticBoard(Point size = new Point(5,31))    //  20 for Top; 6 for Paddle; 5 for Drop
    {
        super(size, new KlaxTileFactory());
    }

    @Override
    public void setup()
    {
        for(int i = 0; i < size.y; i ++)
        {
            for (int j = 0 ; j < size.x; j ++)
            {
                if ((i > 4) && (j % 2 == 0))    //  Top Zone & Even Column
                {
                    addTile(factory.create("deliveryA", this), new Point(i, j));
                }
                else if ((i > 4) && (j % 2 == 1))    //  Top Zone & Odd Column
                {
                    addTile(factory.create("deliveryB", this), new Point(i, j));
                }
                else if (i < 4)    //  Drop Zone
                {
                    addTile(factory.create("drop", this), new Point(i, j));
                }
                else if ((i == 10) && (j == 2))    //  Player Paddle
                {
                    addTile(factory.create("player", this), new Point(i, j));
                }
                else    //  Paddle Carry Buffer Zone
                {
                    addTile(factory.create("carryall", this), new Point(i, j));
                }
            }
        }
    }
/*
    //  Will have to alter so that Colored Tiles get made after a certain interval
    //  Once on-screen, will fall down Y-Axis till reaches Drop-Zone
    //  If no Player Paddle, "drop"; Else, push Paddle down Y with Tile on top

    @Override
    public void update() {
        for(int i = 0; i < size.y; i ++){
            for (int j = 0 ; j < size.x; j ++){
                boardPanel.add(grid[i][j].getTilePanel());
            }
        }
    }
*/
}
