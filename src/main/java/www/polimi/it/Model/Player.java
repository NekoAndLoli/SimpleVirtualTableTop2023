package www.polimi.it.Model;

import www.polimi.it.Exception.NegativeException;

public class Player implements HasStats {
    private String playerID;
    private Stats stats = new Stats();

    public String getPlayerID() {
        return playerID;
    }

    public Player(String playerID) {
        this.playerID = playerID;
    }


    @Override
    public void removeStat(String stat) {
        stats.removeStat(stat);
    }

    @Override
    public void addStat(String stat, Integer max, Integer value) throws NegativeException {
        stats.addStat(stat,max,value);
    }

    @Override
    public void addStat(String stat, Integer max) throws NegativeException {
        stats.addStat(stat,max);
    }

    @Override
    public void addStat(String stat) throws NegativeException {
        stats.addStat(stat);
    }

    @Override
    public void setStat(String stat, Integer value) throws NegativeException {
        stats.setStat(stat,value);
    }

    @Override
    public void setMax(String stat, Integer max) throws NegativeException {
        stats.setMax(stat,max);
    }

    public boolean isDM(){
        return false;
    }

}
