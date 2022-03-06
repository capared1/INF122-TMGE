package Klax;

import TMGE.*;

import javax.swing.*;
import java.awt.*;

public class KlaxGame implements Game {

    private KlaxBoard board = new KlaxBoard(new Point(5,31));

    public KlaxGame()
    {
        board.setup();
        board.update();
    }

    @Override
    public JPanel getGamePanel()
    {
        return board.getBoardPanel();
    }

    @Override
    public String getName()
    {
        return "Klax";
    }

    @Override
    public String toString()
    {
        return "Klax Game";
    }
}
