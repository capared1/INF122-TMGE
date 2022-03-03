package TMGE;

import java.util.Map;

public class Profile {

    private String name;
    private Map<String, Integer> totalScore;

    public Profile(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Map<String, Integer> getTotalScore(){
        return this.totalScore;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
