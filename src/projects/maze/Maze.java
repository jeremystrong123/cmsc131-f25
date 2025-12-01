/** Maze class specification

- `Maze(int maxCells)` - pre-allocates array to maximum size

- `getStart()` - finds and returns the cell with Status Start

- `getEnd()` - finds and returns the cell with Status End
    - Consider writing a helper method `getFirstCellWithStatus(Status)` which does linear search

- setupNeighbors() populates the neighbors list of each cell in the grid

 */

package projects.maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Maze {

    //Instance Variable
    private final Grid grid;

    //Constructor
    public Maze(int maxCells) {
        grid = new Grid(maxCells);
    }

    //Methods
    /**
     * Find the cell in the grid with the CellStatus 'S'.
     * 
     * @return the proper cell if found, null if not found.
     */
    public Cell getStart() {
        for (int i = 0; i < grid.getCellCount(); i++) {
            if(grid.getAllCells()[i].getStatus() == CellStatus.valueOf("S")) {
                return grid.getAllCells()[i];
            }
        }
        return null;
    }

    /**
     * Find the cell in the grid with the CellStatus 'E'.
     * 
     * @return the proper cell if found, null if not found.
     */
    public Cell getEnd() {
        for (int i = 0; i < grid.getCellCount(); i++) {
            if(grid.getAllCells()[i].getStatus() == CellStatus.valueOf("E")) {
                return grid.getAllCells()[i];
            }
        }
        return null;
    }

    /**
     * Adds the coordinates of all of the cell's neighbors to its neighbors array.
     */
    public void setupNeighbors() {
        for (int i = 0; i < grid.getCellCount(); i++) {
            grid.getAllCells()[i].setNeighbors();
        }
    }

    /**
     * Accessor for the maze's grid.
     * @return grid of the maze
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Provided by Dusel. Assumes grid cell has a getStatus() method.
     * @param filename - Output filename.
     */
    public void serialize(String filename) {
        Cell[] cells = grid.getAllCells();

        FileWriter writer;
        try {
            writer = new FileWriter(new File(filename));
            // discover dimension of maze's underlying grid
            int maxRow = 0, maxCol = 0;
            int idxCell;
            for (idxCell = 0; idxCell < cells.length; idxCell++) {
                int row = cells[idxCell].getCoords().getRow();
                int col = cells[idxCell].getCoords().getCol();
                if (row > maxRow) { maxRow = row; }
                if (col > maxCol) { maxCol = col; }
            }
    
            // write cell statuses, using 'X' for absent (inaccessible) cells
            idxCell = 0;
            for (int row = 0; row <= maxRow; row++) {
                for (int col = 0; col <= maxCol; col++) {
                    Cell maybeCell = grid.getCell(
                        new Coords(row, col)
                    );
                    if (maybeCell != null) {
                        writer.write(maybeCell.getStatus().toString()/*maybeCell.getCoords().toString()*/);
                        idxCell++;
                    } else {
                        writer.write('X');
                    }
                    writer.write(' ');
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
