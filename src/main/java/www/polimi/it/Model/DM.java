package www.polimi.it.Model;

public class DM extends Player{
    public DM(String playerID) {
        super(playerID);
    }

    @Override
    public boolean isDM() {
        return true;
    }
}
