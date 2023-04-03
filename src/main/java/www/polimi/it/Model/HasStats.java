package www.polimi.it.Model;

import www.polimi.it.Exception.NegativeException;

public interface HasStats {

    /**
     * removes a stat from token
     * @param stat name of the stat
     */
    public void removeStat(String stat);

    /**
     * Adds a new stat to the token and sets its attributes
     * @param stat String
     * @param max Integer
     * @param value Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max, Integer value) throws NegativeException;

    /**
     * Adds a new stat to the token and sets its max value, current value is set to max
     * @param stat String
     * @param max Integer
     * @throws NegativeException
     */
    public void addStat(String stat, Integer max)throws NegativeException;

    /**
     * Adds a new stat to the token and sets its max value to 0
     * @param stat String
     * @throws NegativeException
     */
    public void addStat(String stat) throws NegativeException;

    /**
     *
     * @param stat String name of the stat to set
     * @param value Integer value to be set
     * @throws NegativeException
     */
    public void setStat(String stat, Integer value) throws NegativeException;

    /**
     *
     * @param stat String name of the stat to set
     * @param max Integer max value of the stat to be set
     * @throws NegativeException
     */
    public void setMax(String stat, Integer max)throws NegativeException;

}
