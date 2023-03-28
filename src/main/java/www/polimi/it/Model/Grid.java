package www.polimi.it.Model;

public class Grid {
    private Integer rows;
    private Integer columns;
    private Token[][] grid;
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

}
