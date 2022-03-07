package PopBlockPuzzle;

import TMGE.Board;
import TMGE.Tile;

import java.awt.*;

public class StaticTile extends Tile {
    public StaticTile(Board board, Color color, String Label) {
        super(board, color, Label);
    }

    @Override
    public void trigger() {
        int startTiles = board.remainingTiles;
        pop(coord.x, coord.y);
        int pointsScored = startTiles - board.remainingTiles;
        if(board.p1Turn){
            board.scoreboard.put("P1", board.scoreboard.get("P1") + pointsScored);
        }
        else{
            board.scoreboard.put("P2", board.scoreboard.get("P2") + pointsScored);
        }
        board.p1Turn = !board.p1Turn;
        System.out.println("P1 score: " + board.scoreboard.get("P1") + " | P2 score: " + board.scoreboard.get("P2"));
        if(board.remainingTiles == 0){
            String winner = board.scoreboard.get("P1") > board.scoreboard.get("P2") ? "P1" : "P2";
            System.out.println(winner + " wins with " + board.scoreboard.get(winner));
        }
    }

    void pop(int x, int y){
        try {
            if(! ((StaticBoard) board).grid[x][y].isEnabled()){
                return;
            }
            if (board.grid[x][y].isMatch(this)){
                board.grid[x][y].setEnabled(false);
                board.remainingTiles--;
                board.grid[x][y].setBackground(Color.GRAY);
                pop(x + 1, y);
                pop(x - 1, y);
                pop(x, y + 1);
                pop(x, y - 1);
            } else {
                return;
                //System.out.println("Not Equal");
            }
        }catch (Exception e){
            return;
        }
    }

    @Override
    public Boolean isMatch(Tile other){
        return this.getValue().equals(other.getValue());
    }

    @Override
    public Object getValue() {
        return this.color;
    }
}
