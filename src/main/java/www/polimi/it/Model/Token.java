package www.polimi.it.Model;

import java.util.HashMap;

public class Token {
    //TODO javadoc
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
     *
     * @return TokenImage set
     */
    public TokenImage getImage() {
        return image;
    }


    public String getOwner() {
        return owner;
    }

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
     * @throws IllegalArgumentException
     */
    public void addStat(String stat, Integer max, Integer value) throws IllegalArgumentException{
        if(stats.containsKey(stat))throw new IllegalArgumentException("Stat already assigned");
        if(max<0)throw new IllegalArgumentException("Max is negative");
        if(value<0)throw new IllegalArgumentException("Value is negative");
        stats.put(stat,value);
        statsMax.put(stat,max);
    }

    /**
     * Adds a new stat to the token and sets its max value, current value is set to max
     * @param stat String
     * @param max Integer
     * @throws IllegalArgumentException
     */
    public void addStat(String stat, Integer max)throws IllegalArgumentException{
        addStat(stat,max,max);
    }

    /**
     * Adds a new stat to the token and sets its max value to 0
     * @param stat String
     * @throws IllegalArgumentException
     */
    public void addStat(String stat) throws IllegalArgumentException{
        addStat(stat,0);
    }

    /**
     *
     * @param stat
     * @param value
     * @throws IllegalArgumentException
     */
    public void setStat(String stat, Integer value) throws IllegalArgumentException{
        if(!stats.containsKey(stat))throw new IllegalArgumentException("Stat not found");
        if(statsMax.get(stat) < value) throw new IllegalArgumentException("value has to be lower then its max limit");
        if(value<0)throw new IllegalArgumentException("Value is negative");
        stats.put(stat,value);
    }

    /**
     *
     * @param stat
     * @param max
     * @throws IllegalArgumentException
     */
    public void setMax(String stat, Integer max)throws IllegalArgumentException{
        if(!stats.containsKey(stat))throw new IllegalArgumentException("Stat not found");
        if(max<0)throw new IllegalArgumentException("Max is negative");
        statsMax.put(stat,max);
        if(stats.get(stat)<max) {
            stats.put(stat,max);
        }
    }
}
