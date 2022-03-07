import PopBlockPuzzle.PopBlockPuzzle;
import TMGE.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args){
        int size = 700;
        JFrame frame = new JFrame("Tester");
        frame.setLayout(new BorderLayout());
        frame.setSize(size, size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ArrayList<Game> games = new ArrayList<>();
        games.add(new PopBlockPuzzle());


        Controller c = new Controller(frame, games, new MyGameFactory());


    }
}
