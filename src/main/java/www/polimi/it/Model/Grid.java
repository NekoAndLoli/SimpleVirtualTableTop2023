package www.polimi.it.Model;

import www.polimi.it.Exception.NoTokenException;
import www.polimi.it.Exception.NotYourTokenException;
import www.polimi.it.Exception.PosNotFreeException;
import www.polimi.it.Exception.PosOutOfBoundException;

public class Grid {
    private Integer rows;
    private Integer columns;
    private Token[][] grid;

    public Grid(){
        new Grid(10,15);
    }

    /**
     * Constructor of the grid, with its initial dimension
     * @param rows
     * @param columns
     * @throws IllegalArgumentException
     */
    public Grid(Integer rows, Integer columns) throws IllegalArgumentException{
        this.rows = rows;
        this.columns = columns;
        generateGrid();
    }

    /**
     *
     * @return number of rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     *
     * @return number of columns
     */
    public Integer getColumns() {
        return columns;
    }

    /**
     * Sets the new column height (number of rows in a column) and regenerates a new grid
     * @param rows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
        generateGrid();
    }

    /**
     * Sets the new row length (number of columns in a row) and regenerates a new grid
     * @param columns
     */
    public void setColumns(Integer columns) {
        this.columns = columns;
        generateGrid();
    }

    /**
     * Regenerates an empty grid of null Tokens
     */
    private void generateGrid(){
        grid = new Token[rows][columns];
    }

    /**
     *
     * @param start
     * @param end
     * @param playerID of the player doing this action
     * @throws Exception
     */
    public void moveToken(Pos start, Pos end, String playerID) throws Exception {
        if(checkPos(start) && checkPos(end)){
            if(!grid[end.getX()][end.getY()].canMove(playerID)) {//check owner
                throw new NotYourTokenException();
            }
            if (grid[end.getX()][end.getY()] != null) {
                throw new PosNotFreeException();
            }
            if (grid[start.getX()][start.getY()] == null) {
                throw new NoTokenException();
            }
            grid[end.getX()][end.getY()] = grid[start.getX()][start.getY()];
            removeToken(start);
        }
        else {
            throw new PosOutOfBoundException();
        }
    }

    /**
     *
     * @param pos
     * @param tokenImage
     */
    public void addToken(Pos pos, TokenImage tokenImage){
        Token newToken = new Token(tokenImage);
        grid[pos.getX()][pos.getY()] = newToken;
    }

    /**
     *
     * @param pos
     * @throws Exception
     */
    public void removeToken(Pos pos) throws Exception {
        if (!checkPos(pos)){
            throw new PosOutOfBoundException();
        }
        if (grid[pos.getX()][pos.getY()]==null){
            throw new NoTokenException();
        }
        grid[pos.getX()][pos.getY()] = null;
    }

    private boolean checkPos(Pos   pos){
        if(pos.getX()<0 || pos.getY()<0){
            return false;
        }
        else if(pos.getX()>=rows || pos.getY()>= columns){
            return false;
        }
        return  true;
    }
}
