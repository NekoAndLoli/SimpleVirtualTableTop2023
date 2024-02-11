package www.polimi.it.Model;

import www.polimi.it.Exception.NegativeException;

import java.util.HashMap;

public class Token implements HasStats {
    private TokenImage image;
    private String owner;

    private Stats stats = new Stats();

    /**
     * Constructor
     * @param image TokenImage to be set
     */
    public Token(TokenImage image){
        this.owner = "";
        this.image = image;
    }

    /**
     *
     * @param image TokenImage to be set
     * @param owner DM, .playerId or ""
     */
    public Token(TokenImage image, String owner){
        this.owner = owner;
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
        stats.removeStat(stat);
    }

    /**
     * Adds a new stat to the token and sets its attributes
     * @param stat String
     * @param max Integer
     * @param value Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max, Integer value) throws NegativeException {
        stats.addStat(stat,max,value);
    }

    /**
     * Adds a new stat to the token and sets its max value, current value is set to max
     * @param stat String
     * @param max Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max)throws NegativeException{
        stats.addStat(stat,max);
    }

    /**
     * Adds a new stat to the token and sets its max value to 0
     * @param stat String
     * @throws NegativeException
     */
    public void addStat(String stat) throws NegativeException{
        stats.addStat(stat);
    }

    /**
     *
     * @param stat String name of the stat to set
     * @param value Integer value to be set
     * @throws NegativeException
     */
    public void setStat(String stat, Integer value) throws NegativeException {
        setStat(stat,value);
    }

    /**
     *
     * @param stat String name of the stat to set
     * @param max Integer max value of the stat to be set
     * @throws NegativeException
     */
    public void setMax(String stat, Integer max)throws NegativeException{
        setMax(stat,max);
    }

    /**
     *
     * @param playerID
     * @return true if player is DM, player is owner, or owner is ""
     */
    public boolean canMove(String playerID) {
        return playerID == "DM" || playerID == owner || owner == "";
    }
}
