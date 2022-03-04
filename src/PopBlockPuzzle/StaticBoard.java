package PopBlockPuzzle;

import TMGE.*;

import java.awt.*;

import java.util.Random;

public class StaticBoard extends Board {
    public StaticBoard(Point size) {
        super(size, new PopTileFactory());
    }

    @Override
    public void setup() {
        for(int i = 0; i < size.y; i ++){
            for (int j = 0 ; j < size.x; j ++){
                addTile(factory.create(getTileType(), this), new Point(i, j));
            }
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < size.y; i ++){
            for (int j = 0 ; j < size.x; j ++){
                boardPanel.add(grid[i][j].getTilePanel());
            }
        }

    }

    private String getTileType(){
        Random numberGenerator = new Random();
        int selectedValue = numberGenerator.nextInt(100);
        int[] thresholds = {31, 62, 93};
        if(selectedValue < thresholds[0]){
            return "green";
        }
        else if(selectedValue >= thresholds[0] && selectedValue < thresholds[1]){
            return "yellow";
        }
        else if(selectedValue >= thresholds[1] && selectedValue < thresholds[2]){
            return "blue";
        }
        else if(selectedValue >= thresholds[2]){
            return "bomb";
        }
        return "";
    }
}
