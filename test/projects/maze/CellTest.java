package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {

    private Coords cr;
    private Cell ce;

    @BeforeEach
    void createCell() {
        cr = new Coords(1, 1);
        ce = new Cell(cr, "O");
    }

    @Test
    void constructorThrowsForInvalidStatus() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new Cell(cr, "L");}
        );
        assertEquals(
            "Status must be 'S', 'E', 'O', or 'P'",
            e.getMessage()
        );
    }

    @Test 
    void getCoordsTest() {
        assertEquals(cr, ce.getCoords());
    }

    @Test 
    void getNeighborsTest() {
        assertEquals(null, ce.getNeighbors()[1]);
    }

    @Test
    void setNeighborsTest() {
        ce.setNeighbors();
        assertEquals(2, ce.getNeighbors()[0].getRow());
        assertEquals(0, ce.getNeighbors()[1].getRow());
        assertEquals(2, ce.getNeighbors()[2].getCol());
        assertEquals(0, ce.getNeighbors()[3].getCol());
    }

    @Test 
    void getExplorationStatusTest() {
        assertEquals(false, ce.getExplorationStatus());
    }

    @Test
    void exploreTest() {
        ce.explore();
        assertEquals(true, ce.getExplorationStatus());
    }

    @Test 
    void getStatusTest() {
        assertEquals(CellStatus.valueOf("O"), ce.getStatus());
    }

    @Test
    void setStatusTest() {
        ce.setStatus("E");
        assertEquals(CellStatus.valueOf("E"), ce.getStatus());
    }

    @Test
    void setStatusReturnsFalseForImproperValue() {
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {ce.setStatus("L");}
        );
        assertEquals(
            "Status must be 'S', 'E', 'O', or 'P'",
            e.getMessage()
        );
    }
}
