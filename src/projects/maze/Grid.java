package projects.maze;

public class Grid {
    //instance variables
    private final Cell[] cells;
    private int cellCount;

    //constructors
    /** 
     * Constructs a grid with the given amount of max cells and sets the cell count to 0.
     * 
     * @param maxCells ID for the account, must be a string.
    */
    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }

    //methods
    /** 
     * Adds a cell into the grid, then increments the cell count.
     * 
     * @param cell cell to be added to the grid.
     * 
     * @return true if the cell was successfully added, false if not.
    */
    public boolean insertCell(Cell cell) {
        if (cellCount < cells.length) {
            cells[cellCount] = cell;
            cellCount++;
            return true;
        }
        return false;
    }

    /** 
     * Returns a cell with the given coords.
     * 
     * @param vh coordinates of the cell to be found.
     * 
     * @return the cell with the given coords if it is found, null if not.
    */
    public Cell getCell(Coords vh) {
        for (int idx = 0; idx < cellCount; idx++) {
            if ( cells[idx].getCoords().equals(vh) ) {
               return cells[idx];
            }
        }
        return null;
    }

    /**
     * Accessor for cell count.
     * @return the current cell count.
     */
    public int getCellCount() {
        return cellCount;
    }

    /**
     * Accessor for cells array.
     * @return the cells array with all of its current values.
     */
    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }

}
