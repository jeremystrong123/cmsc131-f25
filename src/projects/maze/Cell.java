/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

package projects.maze;

public class Cell {
    //Instance Variables
    private final Coords coords;
    private Coords[] neighbors;
    private boolean explored;
    CellStatus status;

    //Constructors
    //constructors
    /** 
     * Constructs a Cell with the given coords and status.
     * 
     * @param c Coords of the cell.
     * 
     * @param s Status of the cell, must be S, E, O, or P.
     * 
     * @throws IllegalArgumentException If status is not a valid type.
    */
    public Cell(Coords c, String s) {
        // you should just pass a CellStatus argument instead of a String
        if ((s.toUpperCase().equals("S") || 
        s.toUpperCase().equals("E") || 
        s.toUpperCase().equals("O") || 
        s.toUpperCase().equals("P")) == false) {
            throw new IllegalArgumentException(
                "Status must be 'S', 'E', 'O', or 'P'"
            );
        }
        status = CellStatus.valueOf(s.toUpperCase());
        coords = c;
        neighbors = new Coords[4];
        explored = false;
    }

    //Methods
    /**
     * Accessor for cell coordinates.
     * @return coordinates of the cell
     */
    public Coords getCoords() {
        return coords;
    }

    /**
     * Updates the neigbors array with all adjacent cell coordinates to the cell.
     */
    public void setNeighbors() {
        // needs to use the grid as context, to test which of these coords 
        // actually belong to a grid cell
        Coords c1 = new Coords(coords.getRow()+1, coords.getCol());
        Coords c2 = new Coords(coords.getRow()-1, coords.getCol());
        Coords c3 = new Coords(coords.getRow(), coords.getCol()+1);
        Coords c4 = new Coords(coords.getRow(), coords.getCol()-1);
        neighbors[0] = c1;
        neighbors[1] = c2;
        neighbors[2] = c3;
        neighbors[3] = c4;
    }

    /**
     * Accessor for neighbors array.
     * @return neighbors array for the cell
     */
    public Coords[] getNeighbors() {
        return neighbors;
    }

    /**
     * Updates the exploration status of the cell.
     */
    public void explore() {
        explored = true;
    }

    /**
     * Accessor for exploration status.
     * @return exploration status of the cell
     */
    public boolean getExplorationStatus() {
        return explored;
    }

    /**
     * Changes the CellStatus of the cell.
     * 
     * @param s the CellStatus to change to.
     * 
     * @return true if successful.
     * 
     * @throws IllegalArgumentException if value of s is an invalid status type.
     */
    public boolean setStatus(String s) {
        if ((s.toUpperCase().equals("S") || 
        s.toUpperCase().equals("E") || 
        s.toUpperCase().equals("O") || 
        s.toUpperCase().equals("P")) == false) {
            throw new IllegalArgumentException(
                "Status must be 'S', 'E', 'O', or 'P'"
            );
        }
        else {
            status = CellStatus.valueOf(s.toUpperCase());
            return true;
        }
    }

    /**
     * Accessor for cell status.
     * @return CellStatus of the cell
     */
    public CellStatus getStatus() {
        return status;
    }
}
