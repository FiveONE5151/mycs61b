import org.junit.Test;

import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct, finally printing the results.
     *
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    @Test
    public void testGet() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("Front");
        assertFalse(lld.isEmpty());
        assertEquals("Front", lld.get(0));
        assertNull(lld.get(19));
        lld.removeLast();
        assertNull(lld.get(0));
        lld.addLast("Front");
        lld.addLast("Middle");
        lld.addLast("End");
        assertEquals("Front", lld.get(0));
        assertEquals("Middle", lld.get(1));
        assertEquals("End", lld.get(2));
        assertNull(lld.get(3));
        lld.printDeque();
    }

    @Test
    public void testGetRecursion() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("Front");
        assertFalse(lld.isEmpty());
        assertEquals("Front", lld.getRecursive(0));
        assertNull(lld.getRecursive(19));
        lld.removeLast();
        assertNull(lld.getRecursive(0));
        lld.addLast("Front");
        lld.addLast("Middle");
        lld.addLast("End");
        assertEquals("Front", lld.getRecursive(0));
        assertEquals("Middle", lld.getRecursive(1));
        assertEquals("End", lld.getRecursive(2));
        assertNull(lld.getRecursive(3));
        lld.printDeque();
    }

    @Test
    public void testDeepCopy() {
        LinkedListDeque<String> lld = new LinkedListDeque<>("Front", "Middle", "End");
        assertEquals("Front", lld.getRecursive(0));
        assertEquals("Middle", lld.getRecursive(1));
        assertEquals("End", lld.getRecursive(2));
        LinkedListDeque<String> lld2 = new LinkedListDeque<>(lld);
        assertEquals("Front", lld2.getRecursive(0));
        assertEquals("Middle", lld2.getRecursive(1));
        assertEquals("End", lld2.getRecursive(2));
        lld2.printDeque();
        lld2 = null;
        LinkedListDeque<String> lld3 = new LinkedListDeque<>(lld2);
        assertEquals(0, lld3.size());
    }

    @Test
    public void testIterator() {
        LinkedListDeque<String> lld = new LinkedListDeque<>("Front", "Middle", "End");
        for (var i : lld) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(-1 % 6);
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }
} 