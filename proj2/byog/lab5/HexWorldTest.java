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
    public void addHexagon() {

    }
}