package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 * 
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> ar = new ArrayRingBuffer<>(5);
        assertTrue(ar.isEmpty());
        for (int i = 0; i < 5; ++i) {
            ar.enqueue(i);
        }
        assertEquals(0, ar.peek().intValue());
        assertTrue(ar.isFull());
        assertEquals(0, (int)ar.dequeue());
        assertEquals(1, ar.dequeue().intValue());
        assertEquals(2, ar.peek().intValue());
        assertFalse(ar.isFull());
        ar.enqueue(5);
        ar.enqueue(6);
        for (int i = 2; i < 7; ++i)
            assertEquals(i, ar.dequeue().intValue());
    }

    @Test
    public void iterator() {
        ArrayRingBuffer<Integer> ar = new ArrayRingBuffer<>(5);
        for (int i = 0; i < 5; i++) {
            ar.enqueue(i);
        }

        for (var i : ar) {
            System.out.print(i + " ");
        }
    }

}
