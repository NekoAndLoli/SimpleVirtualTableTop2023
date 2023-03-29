package www.polimi.it.Model;

import java.util.HashMap;

public class Player {
    private String playerID;
    private HashMap<String,Integer> stats = new HashMap<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Player(String playerID) {
        this.playerID = playerID;
    }
}
