package www.polimi.it.Model;

import www.polimi.it.Exception.NegativeException;

import java.util.HashMap;

public class Token {
    private TokenImage image;
    private String owner;

    private HashMap<String,Integer> stats = new HashMap();
    private HashMap<String,Integer> statsMax = new HashMap();

    /**
     * Constructor
     * @param image TokenImage to be set
     */
    public Token(TokenImage image){
        this.image = image;
    }

    /**
     * Get the Image Resource
     * @return TokenImage set
     */
    public TokenImage getImage() {
        return image;
    }

    /**
     * Get the PlayerID of the owner
     * @return String
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set the owner of the Token
     * @param owner String, playerID of the new owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * removes a stat from token
     * @param stat name of the stat
     */
    public void removeStat(String stat){
        stats.remove(stat);
        statsMax.remove(stat);
    }

    /**
     * Adds a new stat to the token and sets its attributes
     * @param stat String
     * @param max Integer
     * @param value Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max, Integer value) throws NegativeException {
        if(stats.containsKey(stat))throw new IllegalArgumentException("Stat already assigned");
        if(max<0)throw new NegativeException("Max is negative");
        if(value<0)throw new NegativeException("Value is negative");
        if(value>max) {
            value = max;
        }
        stats.put(stat,value);
        statsMax.put(stat,max);
    }

    /**
     * Adds a new stat to the token and sets its max value, current value is set to max
     * @param stat String
     * @param max Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max)throws NegativeException{
        addStat(stat,max,max);
    }

    /**
     * Adds a new stat to the token and sets its max value to 0
     * @param stat String
     * @throws NegativeException
     */
    public void addStat(String stat) throws NegativeException{
        addStat(stat,0);
    }

    /**
     *
     * @param stat String name of the stat to set
     * @param value Integer value to be set
     * @throws NegativeException
     */
    public void setStat(String stat, Integer value) throws NegativeException {
        if(!stats.containsKey(stat))throw new IllegalArgumentException("Stat not found");
        if(statsMax.get(stat) < value) value = statsMax.get(stat);
        if(value<0)throw new NegativeException("Value is negative");
        stats.put(stat,value);
    }

    /**
     *
     * @param stat String name of the stat to set
     * @param max Integer max value of the stat to be set
     * @throws NegativeException
     */
    public void setMax(String stat, Integer max)throws NegativeException{
        if(!stats.containsKey(stat))throw new IllegalArgumentException("Stat not found");
        if(max<0)throw new NegativeException("Max is negative");
        statsMax.put(stat,max);
        if(stats.get(stat)<max) {
            stats.put(stat,max);
        }
    }
}
