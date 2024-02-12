package www.polimi.it.Model;

import www.polimi.it.Exception.*;

public class Grid {
    private Integer rows;
    private Integer columns;
    private Token[][] grid;

    public Grid(){
        this(10,15);
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
    public void setRows(Integer rows) throws NegativeException {
        if(rows<0)throw new NegativeException("rows negative");
        this.rows = rows;
        generateGrid();
    }

    /**
     * Sets the new row length (number of columns in a row) and regenerates a new grid
     * @param columns
     */
    public void setColumns(Integer columns) throws NegativeException {
        if(columns<0)throw new NegativeException("columns negative");
        this.columns = columns;
        generateGrid();
    }

    public void setGrid(Integer rows,Integer columns) throws NegativeException {
        if(rows<0)throw new NegativeException("rows negative");
        if(columns<0)throw new NegativeException("columns negative");
        this.rows = rows;
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
     * @param player doing this action
     * @throws Exception
     */
    public void moveToken(Pos start, Pos end, Player player) throws NotYourTokenException, PosNotFreeException, NoTokenException, PosOutOfBoundException {
        if(start == null || end == null)throw new PosOutOfBoundException();
        else if(start.equals(end))return;
        if(checkPos(start) && checkPos(end)){

            if (grid[start.getX()][start.getY()] == null) {
                throw new NoTokenException();
            }
            if(!grid[start.getX()][start.getY()].canMove(player)) {//check owner
                throw new NotYourTokenException();
            }
            if (grid[end.getX()][end.getY()] != null) {
                throw new PosNotFreeException();
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
    public void addToken(Pos pos, TokenImage tokenImage) throws PosNotFreeException, PosOutOfBoundException {
        Token newToken = new Token(tokenImage);
        if(!checkPos(pos))throw new PosOutOfBoundException();
        if (grid[pos.getX()][pos.getY()]!=null)throw new PosNotFreeException();
        grid[pos.getX()][pos.getY()] = newToken;
    }

    /**
     *
     * @param pos
     * @throws Exception
     */
    public void removeToken(Pos pos) throws PosOutOfBoundException, NoTokenException {
        if (!checkPos(pos)){
            throw new PosOutOfBoundException();
        }
        if (grid[pos.getX()][pos.getY()]==null){
            throw new NoTokenException();
        }
        grid[pos.getX()][pos.getY()] = null;
    }

    private boolean checkPos(Pos   pos){
        if(pos.getX()>=rows || pos.getY()>= columns){
            return false;
        }
        return  true;
    }

    public Token getToken(Pos pos) {
        return grid[pos.getX()][pos.getY()];
    }

    public TokenImage getTokenImage(Pos pos){
        Token t = getToken(pos);
        if(t == null)return null;
        return t.getImage();
    }
}
