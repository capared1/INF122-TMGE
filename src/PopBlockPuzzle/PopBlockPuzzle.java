package PopBlockPuzzle;

import TMGE.*;

import javax.swing.*;
import java.awt.*;

public class PopBlockPuzzle implements Game {

    private StaticBoard board = new StaticBoard(new Point(15,15));

    public PopBlockPuzzle(){
        board.setup();
        board.update();
    }

    @Override
    public JPanel getGamePanel(){
        return board.getBoardPanel();
    }

    @Override
    public String getName(){
        return "Pop Block Puzzle";
    }

    @Override
    public String toString(){
        return "Pop Block Puzzle";
    }
}
