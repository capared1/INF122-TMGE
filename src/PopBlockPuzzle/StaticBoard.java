package PopBlockPuzzle;

import TMGE.*;

import java.awt.*;

public class StaticBoard extends Board {
    public StaticBoard(Point size) {
        super(size, new PopTileFactory());
    }

    @Override
    public void setup() {
        for(int i = 0; i < size.y; i ++){
            for (int j = 0 ; j < size.x; j ++){
                if((i + j)%6 == 0){
                    addTile(factory.create("bomb", this), new Point(i, j));
                }
                else if (i*j%5 == 0){
                    addTile(factory.create("green",  this),new Point(i, j));
                }
                else if((i+j)%7 == 0){
                    addTile(factory.create("yellow",  this),new Point(i, j));
                }
                else {
                    addTile(factory.create("blue",  this),new Point(i, j));
                }
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
}
