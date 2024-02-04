package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

public class HexWorldTest {
    HexWorld hexWorld = new HexWorld();

    @Test
    public void testBoundCheck() {
        assertTrue(hexWorld.boundCheck(10, 10, new Position(9, 9), 4, 4));
        assertFalse(hexWorld.boundCheck(10, 10, new Position(9, 9), 16, 16));
    }

    @Test
    public void rowWidth() {
        assertEquals(3, HexWorld.hexRowWidth(3, 0));
        assertEquals(7, HexWorld.hexRowWidth(3, 2));
        assertEquals(7, HexWorld.hexRowWidth(3, 3));
        assertEquals(3, HexWorld.hexRowWidth(3, 5));
        assertEquals(6, HexWorld.hexRowWidth(4, 1));
        assertEquals(10, HexWorld.hexRowWidth(4, 3));
        assertEquals(10, HexWorld.hexRowWidth(4, 4));
        assertEquals(6, HexWorld.hexRowWidth(4, 6));
    }

    @Test
    public void rowOffset() {
        assertEquals(2, HexWorld.hexRowOffset(3, 0));
        assertEquals(1, HexWorld.hexRowOffset(3, 1));
        assertEquals(0, HexWorld.hexRowOffset(3, 2));
        assertEquals(0, HexWorld.hexRowOffset(3, 3));
        assertEquals(1, HexWorld.hexRowOffset(3, 4));
        assertEquals(2, HexWorld.hexRowOffset(3, 5));

        assertEquals(3, HexWorld.hexRowOffset(4, 0));
        assertEquals(2, HexWorld.hexRowOffset(4, 1));
        assertEquals(1, HexWorld.hexRowOffset(4, 2));
        assertEquals(0, HexWorld.hexRowOffset(4, 3));
        assertEquals(0, HexWorld.hexRowOffset(4, 4));
        assertEquals(1, HexWorld.hexRowOffset(4, 5));
        assertEquals(2, HexWorld.hexRowOffset(4, 6));
        assertEquals(3, HexWorld.hexRowOffset(4, 7));
    }

}