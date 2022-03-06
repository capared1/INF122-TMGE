package Klax;

import TMGE.Board;
import TMGE.Tile;

import java.awt.*;

public class KlaxTile extends Tile
{
    protected int colorID;

    public KlaxTile(Board board, Color color = null, String Label = null)
    {
        Random randomizer = new Random();
        this.colorID = randomizer.nextInt(10);
        Color colorSel = setColor();
        super(board, colorSel, Label);
    }

    public Color setColor()    //  Selecting 1/10 possible colors
    {
        if (this.colorID == 0)
        {
            colorSel = new Color(255, 241, 0);  //  Process Yellow
        }
        else if (this.colorID == 1)
        {
            colorSel = new Color(255, 140, 0);  //  Orange 144
        }
        else if (this.colorID == 2)
        {
            colorSel = new Color(232, 17, 35);  //  Red 185
        }
        else if (this.colorID == 3)
        {
            colorSel = new Color(236, 0, 140);  //  Process Magenta
        }
        else if (this.colorID == 4)
        {
            colorSel = new Color(104, 33, 122);  //  Purple 526
        }
        else if (this.colorID == 5)
        {
            colorSel = new Color(0, 24, 143);  //   Blue 286
        }
        else if (this.colorID == 6)
        {
            colorSel = new Color(0, 188, 242);  //  Process Cyan
        }
        else if (this.colorID == 7)
        {
            colorSel = new Color(0, 178, 148);  //  Teal 3275
        }
        else if (this.colorID == 8)
        {
            colorSel = new Color(0, 158, 73);  //  Green 355
        }
        else if (this.colorID == 9)
        {
            colorSel = new Color(186, 216, 10);  //  Lime 382
        }
        return colorSel;
    }

    public Color getColor()
    {
        return this.color;
    }
}