package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    private Maze maze;
    private Cell cell;
    private Coords coords;

    @BeforeEach 
    void setupMaze() {
        maze = new Maze(10);
        coords = new Coords(0, 0);
        cell = new Cell(coords, "O");
        maze.getGrid().insertCell(cell);
    }

    @Test
    void getStartTest() {
        Coords coords2 = new Coords(1, 1);
        Cell cell2 = new Cell(coords2, "S");
        maze.getGrid().insertCell(cell2);
        assertEquals(cell2, maze.getStart());
    }

    @Test 
    void getStartReturnsNullIfNoStartCell() {
        assertEquals(null, maze.getStart());
    }

    @Test
    void getEndTest() {
        Coords coords2 = new Coords(1, 1);
        Cell cell2 = new Cell(coords2, "E");
        maze.getGrid().insertCell(cell2);
        assertEquals(cell2, maze.getEnd());
    }

    @Test 
    void getEndReturnsNullIfNoEndCell() {
        assertEquals(null, maze.getEnd());
    }

    @Test
    void setupNeighborsTest() {
        assertEquals(null, cell.getNeighbors()[0]);
        maze.setupNeighbors();
        assertEquals(1, cell.getNeighbors()[0].getRow());
        assertEquals(0, cell.getNeighbors()[0].getCol());
        assertEquals(-1, cell.getNeighbors()[1].getRow());
        assertEquals(0, cell.getNeighbors()[1].getCol());
    }

}
