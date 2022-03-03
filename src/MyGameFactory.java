import PopBlockPuzzle.PopBlockPuzzle;
import TMGE.Game;

public class MyGameFactory extends TMGE.GameFactory {
    @Override
    public Game create(String type) {
        return new PopBlockPuzzle();
    }
}
