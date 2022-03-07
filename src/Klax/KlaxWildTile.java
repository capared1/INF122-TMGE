package Klax;

import TMGE.Board;
import TMGE.Tile;

import java.awt.*;

public class KlaxWildTile extends KlaxTile
{
    public KlaxWildTile(Board board, Color color = null, String Label = null)
    {
        super(board, color, Label);
    }

    public colorIncrement() //  Called every time the tile goes down the Y-Axis?
    {
        if (this.colorID < 9)
        {
            this.colorID++;
        }
        else
        {
            this.colorID = 0;
        }
        this.color = setColor();
        this.tilePanel.setBackground(this.color);
    }

    @Override
    public Color getColor()
    {
        return null;
    }

    @Override
    public boolean compColor(KlaxTile input)
    {
        return true;
    }
}