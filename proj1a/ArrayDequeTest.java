import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addFirst() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertTrue(arrayDeque.isEmpty());
        arrayDeque.addFirst("End");
        assertEquals(1, arrayDeque.size());
        assertEquals("End", arrayDeque.get(0));
        arrayDeque.addFirst("Middle");
        arrayDeque.addFirst("Front");
        assertEquals("Front", arrayDeque.get(0));
        assertEquals("Middle", arrayDeque.get(1));
        assertEquals("End", arrayDeque.get(2));
        arrayDeque.printDeque();
        for (int i = 'F'; i >= 'A'; --i) {
            arrayDeque.addFirst(Character.toString((char)i));
        }
        arrayDeque.printDeque();
        assertEquals(15, arrayDeque.length());
    }

    @Test
    public void addLast() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast("Front");
        arrayDeque.addLast("Middle");
        arrayDeque.addLast("End");
        assertEquals(3, arrayDeque.size());
        assertEquals(7, arrayDeque.length());
        assertEquals("Front", arrayDeque.get(0));
        assertEquals("Middle", arrayDeque.get(1));
        assertEquals("End", arrayDeque.get(2));
        arrayDeque.printDeque();
        arrayDeque.addLast(null);
        assertEquals(3, arrayDeque.size());
        arrayDeque.printDeque();
        arrayDeque.addLast("A");
        arrayDeque.addLast("B");
        arrayDeque.addLast("C");
        arrayDeque.addLast("D");
        assertEquals(7, arrayDeque.length());
        arrayDeque.addLast("E");
        assertEquals(15, arrayDeque.length());
        arrayDeque.addLast("F");
        arrayDeque.printDeque();
    }

    @Test
    public void isEmpty() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertTrue(arrayDeque.isEmpty());
        arrayDeque.addFirst("First");
        assertFalse(arrayDeque.isEmpty());

    }

    @Test
    public void removeFirst() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertNull(arrayDeque.removeFirst());
        arrayDeque.addFirst("End");
        assertEquals("End", arrayDeque.removeFirst());
        assertTrue(arrayDeque.isEmpty());
        for (int i = 'A'; i <= 'G'; ++i) {
            arrayDeque.addLast(Character.toString((char)i));
        }
        assertEquals(7, arrayDeque.length());
        arrayDeque.printDeque();
        assertEquals("A", arrayDeque.removeFirst());
        assertEquals(6, arrayDeque.size());
        assertEquals("B", arrayDeque.get(0));
        arrayDeque.printDeque();
        for (int i = 0; i < 5; ++i) {
            arrayDeque.removeFirst();
        }
        arrayDeque.printDeque();
        assertEquals(1, arrayDeque.size());
        arrayDeque.addLast("H");
        arrayDeque.printDeque();
        assertEquals("H", arrayDeque.get(1));
        assertEquals(2, arrayDeque.size());
        assertEquals("G", arrayDeque.removeFirst());
        assertEquals("H", arrayDeque.removeFirst());
        assertTrue(arrayDeque.isEmpty());
    }

    @Test
    public void removeLast() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertNull(arrayDeque.removeLast());
        arrayDeque.addFirst("End");
        assertEquals("End", arrayDeque.removeLast());
        assertTrue(arrayDeque.isEmpty());
        for (int i = 'A'; i <= 'G'; ++i) {
            arrayDeque.addLast(Character.toString((char)i));
        }
        assertEquals(7, arrayDeque.length());
        arrayDeque.printDeque();
        assertEquals("G", arrayDeque.removeLast());
        assertEquals(6, arrayDeque.size());
        assertEquals("F", arrayDeque.get(5));
        arrayDeque.printDeque();

        for (int i = 0; i < 5; ++i)
            arrayDeque.removeLast();
        assertEquals(1, arrayDeque.size());
        assertEquals("A", arrayDeque.get(0));
        arrayDeque.addFirst("first");
        arrayDeque.printDeque();
        assertEquals("first", arrayDeque.get(0));
        assertEquals("A", arrayDeque.get(1));
        assertEquals("A", arrayDeque.removeLast());
        arrayDeque.printDeque();
        arrayDeque.addFirst("second");
        arrayDeque.printDeque();
        assertEquals("first", arrayDeque.removeLast());
        assertFalse(arrayDeque.isEmpty());
        assertEquals(1, arrayDeque.size());
        arrayDeque.printDeque();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(10);
        arrayDeque.get(1);
    }
}