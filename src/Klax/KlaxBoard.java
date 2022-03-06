package Klax;

import TMGE.*;

import java.awt.*;
import java.util.*;

public class KlaxBoard extends Board
{
    public dropMeter = 5;   //  Game over if 0
    protected Tile[][] boardStripped;
    protected Tile[] falling;
    protected Map<Color, KlaxTile[]> dumpZone;

    public KlaxBoard(Point size = new Point(5,31))    //  20 for Top; 6 for Paddle; 5 for Dump
    {
        super(size, new KlaxTileFactory());
        this.dumpZone = new HashMap<Color, KlaxTile[]>();
    }

    @Override
    public void setup()
    {
        for(int i = 0; i < size.y; i++)
        {
            for (int j = 0 ; j < size.x; j++)
            {
                if ((i > 4) && (j % 2 == 0))    //  Top Zone & Even Column
                {
                    addTile(factory.create("deliveryA", this), new Point(i, j));
                }
                else if ((i > 4) && (j % 2 == 1))    //  Top Zone & Odd Column
                {
                    addTile(factory.create("deliveryB", this), new Point(i, j));
                }
                else if (i < 4)    //  Dump Zone
                {
                    addTile(factory.create("dump", this), new Point(i, j));
                }
                else    //  Paddle Carry Buffer Zone
                {
                    addTile(factory.create("carryall", this), new Point(i, j));
                }
            }
        }
        this.boardStripped = this.grid; //  Save for future cross-referencing (when tiles "move")
        addTile(factory.create("player", this), new Point(10, 2));  //  Initialize Player Paddle
    }

    //  Will have to alter so that Colored Tiles get made after a certain interval
    //  Once on-screen, will fall down Y-Axis till reaches Drop-Zone
    //  If no Player Paddle, "drop"; Else, push Paddle down Y with Tile on top

    @Override
    public void update()
    {
        boardPanel = new JPanel(new GridLayout(size.y, size.y));    //  Clean refresh
        for(int i = 0; i < size.y; i++)
        {
            for (int j = 0 ; j < size.x; j ++)
            {
                boardPanel.add(grid[i][j].getTilePanel());  // Displays this.grid on JPanel
            }
        }
    }

    public void delivery()
    {
        for (int i = 0; i < falling.length; i++)
        {
            Tile tile = falling[i];
            Point currCoord = tile.getCoord();
            if (currCoord.getY() > 9)  //  If in Top Delivery Zone
            {
                addTile(tile, currCoord.translate(0,-1));
            }
            else
            {
                Tile under = this.grid[currCoord.getY() - 1][currCoord.getX()];
                Point undCoord = under.getCoord();
                //  "KlaxPaddleTile" HAVE YET TO BE MADE!!!
                if (under instanceof KlaxPaddleTile)    //  Merge Klax Tile with Player Paddle
                {
                    addTile(under, undCoord.translate(0,-1));  //  Paddle goes down 1 to carry
                    addTile(tile, currCoord.translate(0,-1));   //  Tile goes on top of Paddle
                }
                else if (under instanceof KlaxTile) //  If "KlaxPaddleTile" is stacked
                {
                    //  Will probably need to come up with a better system to handle Paddle Stacks?
                    int stacked = 1;
                    Tile underStack = under;
                    while (!(underStack instanceof KlaxPaddleTile))
                    {
                        stacked++;
                        underStack = this.grid[currCoord.getY() - stacked][currCoord.getX()];
                    }
                    if (stacked > 5)    //  If the Paddle is already carrying 5 Tiles
                    {
                        dropped(i);
                    }
                    else    //  Push entire Paddle Stack down by 1
                    {
                        while (stacked >= 0)
                        {
                            addTile(this.grid[currCoord.getY() - stacked][currCoord.getX()], currCoord.translate(0,-1)); 
                        }
                    }
                }
                else
                {
                    dropped(i);
                }
            }
            this.grid[currCoord.getY()][currCoord.getX()] = this.boardStripped[currCoord.getY()][currCoord.getX()]; //  Cleans original spot of Tile
        }
    }

    public void dropped(int index)  //  Removes from Falling and Subtracts from dropMeter
    {
        ArrayUtils.remove(falling, index);
        this.dropMeter--;
        //  Trigger alert?
    }

    public void toss()    //  When Paddle throws top stack Tile back up into Top Zone
    {}

    public void dump()    //  When Paddle puts top stack Tile down into Dump Zone
    {
        //this.dumpZone.add
    }

//  Everything below this point gets into absolute pattern matching hell.
//  If anyone has a smart idea of trying to match up all the possible combos then please implement
//  It's reaching such a messy point that I barely can wrap my head around my own code anymore

    public void patternCheck()  //  NEED TO FINISH
    {   //  Called to see if a Klax Pattern is made in Dump Zone
        for (Map.Entry<Color, KlaxTile[]> entry : dumpZone.entrySet())
        {
            Color key = entry.getKey();
            if (key != null)    //  Skip Wilds
            {
                KlaxTile[] value = ArrayUtils.addAll(entry.getValue(), dumpZone.get(null)); //  Merge Wilds
                if (value.length > 2)   //  Matches only start 3+
                {
                    for (int i = 0; i < value.length; i++)  //  Check all possible combos, tally them up, and then destroy
                    {  
                        Array<boolean, Array<KlaxTile[]>> horiz = checkHoriz(value);
                        Array<boolean, Array<KlaxTile[]>> vert = checkVert(value);
                        Array<boolean, Array<KlaxTile[]>> diag = checkDiag(value);
                    }
                }
            }
        }
    }

    //  Following two algorithms are roughly the same
    //  They're fairly ugly but I couldn't think of a better way of going at it atm
    private Array<boolean, Array<KlaxTile[]>> checkHoriz(KlaxTile[] input)
    {
        boolean retFlag = false;
        if (input.length <= 5)  //  Hard-coded check cases
        {
            if (input.length) == 5)
            {
                retFlag = ((input[i].getCoord().getY() == input[i+1].getCoord().getY()) && (input[i+1].getCoord().getY() == input[i+2].getCoord().getY()) && (input[i+2].getCoord().getY() == input[i+3].getCoord().getY()) && (input[i+3].getCoord().getY() == input[i+4].getCoord().getY()));
            }
            else if (input.length == 4)
            {
                retFlag = ((input[i].getCoord().getY() == input[i+1].getCoord().getY()) && (input[i+1].getCoord().getY() == input[i+2].getCoord().getY()) && (input[i+2].getCoord().getY() == input[i+3].getCoord().getY()));
            }
            else if (input.length == 3)
            {
                retFlag = ((input[i].getCoord().getY() == input[i+1].getCoord().getY()) && (input[i+1].getCoord().getY() == input[i+2].getCoord().getY()));
            }
            return [retFlag, [input]];
        }
        else    //  Will have to manually check each individual tile
        {
            Array<KlaxTile[]>> retArray = [];
            KlaxTile[] rowA = [];
            KlaxTile[] rowB = [];
            KlaxTile[] rowC = [];
            KlaxTile[] rowD = [];
            KlaxTile[] rowE = [];
            for (int i = 0; i < input.length; i++)  //  Filling each Row
            {
                if (input[i].getCoord().getY() == 0)
                {
                    rowA.push(input[i]);
                }
                else if (input[i].getCoord().getY() == 1)
                {
                    rowB.push(input[i]);
                }
                else if (input[i].getCoord().getY() == 2)
                {
                    rowC.push(input[i]);
                }
                else if (input[i].getCoord().getY() == 3)
                {
                    rowD.push(input[i]);
                }
                else if (input[i].getCoord().getY() == 4)
                {
                    rowE.push(input[i]);
                }
            }
            Array<KlaxTile[]> rows = [rowA,rowB,rowC,rowD,rowE];
            rows.forEach(row -> {    //  Manually check each Row
                if (row.length == 5)    //  Automatically a match
                {
                    retFlag = true;
                    retArray.push(row);
                }
                else if (row.length > 2)    //  Matches are 3+ only
                {
                    int[] order;
                    row.forEach(tile -> {
                        order.push(tile.getY());
                    });
                    Arrays.sort(order);
                    if (row.length == 4)
                    {
                        if (Arrays.equals(order,[0,1,2,3]) || Arrays.equals(order,[1,2,3,4]))
                        {
                            retFlag = true;
                            retArray.push(order);
                        }
                    }
                    else
                    {
                        if(Arrays.equals(order,[0,1,2]) || Arrays.equals(order,[1,2,3]) || Arrays.equals(order,[2,3,4]))
                        {
                            retFlag = true;
                            retArray.push(order);
                        }
                    }
                }
            });
            return [retFlag, retArray];
            }
        }
    }
    private Array<boolean, Array<KlaxTile[]>> checkVert(KlaxTile[] input)
    {
        boolean retFlag = false;
        if (input.length <= 5)  //  Hard-coded check cases
        {
            if (input.length) == 5)
            {
                retFlag = ((input[i].getCoord().getX() == input[i+1].getCoord().getX()) && (input[i+1].getCoord().getX() == input[i+2].getCoord().getX()) && (input[i+2].getCoord().getX() == input[i+3].getCoord().getX()) && (input[i+3].getCoord().getX() == input[i+4].getCoord().getX()));
            }
            else if (input.length == 4)
            {
                retFlag = ((input[i].getCoord().getX() == input[i+1].getCoord().getX()) && (input[i+1].getCoord().getX() == input[i+2].getCoord().getX()) && (input[i+2].getCoord().getX() == input[i+3].getCoord().getX()));
            }
            else if (input.length == 3)
            {
                retFlag = ((input[i].getCoord().getX() == input[i+1].getCoord().getX()) && (input[i+1].getCoord().getX() == input[i+2].getCoord().getX()));
            }
            return [retFlag, [input]];
        }
        else    //  Will have to manually check each individual tile
        {
            Array<KlaxTile[]>> retArray = [];
            KlaxTile[] colA = [];
            KlaxTile[] colB = [];
            KlaxTile[] colC = [];
            KlaxTile[] colD = [];
            KlaxTile[] colE = [];
            for (int i = 0; i < input.length; i++)  //  Filling each Column
            {
                if (input[i].getCoord().getX() == 0)
                {
                    colA.push(input[i]);
                }
                else if (input[i].getCoord().getX() == 1)
                {
                    colB.push(input[i]);
                }
                else if (input[i].getCoord().getX() == 2)
                {
                    colC.push(input[i]);
                }
                else if (input[i].getCoord().getX() == 3)
                {
                    colD.push(input[i]);
                }
                else if (input[i].getCoord().getX() == 4)
                {
                    colE.push(input[i]);
                }
            }
            Array<KlaxTile[]> columns = [colA,colB,colC,colD,colE];
            columns.forEach(col -> {    //  Manually check each Column
                if (col.length == 5)    //  Automatically a match
                {
                    retFlag = true;
                    retArray.push(col);
                }
                else if (col.length > 2)    //  Matches are 3+ only
                {
                    int[] order;
                    col.forEach(tile -> {
                        order.push(tile.getX());
                    });
                    Arrays.sort(order);
                    if (col.length == 4)
                    {
                        if (Arrays.equals(order,[0,1,2,3]) || Arrays.equals(order,[1,2,3,4]))
                        {
                            retFlag = true;
                            retArray.push(order);
                        }
                    }
                    else
                    {
                        if(Arrays.equals(order,[0,1,2]) || Arrays.equals(order,[1,2,3]) || Arrays.equals(order,[2,3,4]))
                        {
                            retFlag = true;
                            retArray.push(order);
                        }
                    }
                }
            });
            return [retFlag, retArray];
            }
        }
    }

    private Array<boolean, Array<KlaxTile[]>> checkDiag(KlaxTile[] input)   //  NEED TO FINISH
    {
        if (input.length == 5)
        {
            retFlag = ((input[i].getCoord().equals(Point(0,0))) && (input[i].getCoord().equals(Point(1,1))) && (input[i].getCoord().equals(Point(2,2))) && (input[i].getCoord().equals(Point(3,3))) && (input[i].getCoord().equals(Point(4,4)))) || (input[i].getCoord().equals(Point(4,0))) && (input[i].getCoord().equals(Point(3,1))) && (input[i].getCoord().equals(Point(2,2))) && (input[i].getCoord().equals(Point(1,3))) && (input[i].getCoord().equals(Point(0,4)));
            return [retFlag, [input]];
        }
        else
        {
            //
        }
    }
}
