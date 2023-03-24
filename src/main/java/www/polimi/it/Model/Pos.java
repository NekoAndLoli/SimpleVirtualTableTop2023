package www.polimi.it.Model;

public class Pos {
    private Integer x;
    private Integer y;

    /**
     * Constructor of Position
     * @param x Integer
     * @param y Integer
     * @throws IllegalArgumentException when input is negative
     */
    public Pos(Integer x, Integer y)throws IllegalArgumentException{
        if(x<0)throw new IllegalArgumentException("X negative");
        if(y<0)throw new IllegalArgumentException("Y negative");
        this.x = x;
        this.y = y;
    }

    /**
     * Sets x
     * @param x the new Integer to be assigned
     * @throws IllegalArgumentException when negative
     */
    public void setX(Integer x) throws IllegalArgumentException {
        if(x<0)throw new IllegalArgumentException("X negative");
        this.x = x;
    }

    /**
     * Sets y
     * @param y the new Integer to be assigned
     * @throws IllegalArgumentException when negative
     */
    public void setY(Integer y) throws IllegalArgumentException {
        if(y<0)throw new IllegalArgumentException("Y negative");
        this.y = y;
    }

    /**
     *
     * @return x Integer
     */
    public Integer getX() {
        return x;
    }

    /**
     *
     * @return y Integer
     */
    public Integer getY() {
        return y;
    }
}
