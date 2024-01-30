/**
 * Circular array deque
 *
 * @param <T>
 * @author Jerry-W
 * @source https://github.com/PKUFlyingPig/CS61B/blob/master/proj1a/ArrayDeque.java (the for loop logic optimized)
 */
public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int length = 8;
    private final double limit = 0.25;
    private final int RESIZE_FACTOR = 2;
    /**
     * Both front and end pointing to the same place means the array is empty (end+1)%length==front means the array is
     * full
     */
    private int front = 0;
    private int end = 0;

    public ArrayDeque() {
        array = (T[])new Object[length];
    }

    public ArrayDeque(int size) {
        this.size = size;
        length = size * RESIZE_FACTOR;
        array = (T[])new Object[length];
    }

    public ArrayDeque(ArrayDeque other) {

    }

    private void resize() {
        if (((double)(size - 1) / length) < limit) {
            T[] newArray = (T[])new Object[length / RESIZE_FACTOR];
            arrayCopy((T[])newArray);
            length /= RESIZE_FACTOR;
            array = newArray;
        } else if (size + 1 >= length) {
            T[] newArray = (T[])new Object[(size + 1) * RESIZE_FACTOR];
            arrayCopy((T[])newArray);
            length = (size + 1) * RESIZE_FACTOR;
            array = newArray;
        }
    }

    /**
     * customized minusOne for the circular array
     *
     * @param index index
     * @return the actual index after minusOne
     */
    private int minusOne(int index) {
        if (index == 0)
            return length - 1;
        return index - 1;
    }

    /**
     * customized plusOne for the circular array
     *
     * @param index  index
     * @param module the length of the array
     * @return the actual index after plusOne
     */
    private int plusOne(int index, int module) {
        return (index + 1) % module;
    }

    /**
     * optimized for loop
     *
     * @param newArray new array
     * @source https://github.com/PKUFlyingPig/CS61B/blob/master/proj1a/ArrayDeque.java
     */
    private void arrayCopy(T[] newArray) {
        int ptr1 = front + 1;
        int ptr2 = 1;
        do {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, newArray.length);
        } while (ptr1 != end);
        newArray[ptr2] = array[ptr1];
        front = 0;
        end = size;
    }

    public void addFirst(T item) {
        if (item == null)
            return;
        if (size + 1 >= length)
            resize();
        ++size;
        array[front] = item;
        front = minusOne(front);
    }

    public void addLast(T item) {
        if (item == null)
            return;
        if (size + 1 >= length)
            resize();
        ++size;
        end = plusOne(end, length);
        array[end] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int length() {
        return length - 1;
    }

    /**
     * print the elements of deque
     *
     * @source https://github.com/PKUFlyingPig/CS61B/blob/master/proj1a/ArrayDeque.java (optimized for loop)
     */
    public void printDeque() {
        if (size == 0)
            return;
        int i = 0;
        for (i = plusOne(front, length); i != end; i = plusOne(i,
            length)) { // The loop termination condition is not necessarily a greater than or less than relation
            System.out.print(array[i] + " ");
        }
        System.out.println(array[i]);
    }

    public T removeFirst() {
        if (isEmpty())
            return null;
        if ((double)(size - 1) / length < limit && length > 8)// length>8 can avoid the arrayCopy bound issue
            resize();
        front = plusOne(front, length);
        --size;
        return array[front];
    }

    public T removeLast() {
        if (isEmpty())
            return null;
        if ((double)(size - 1) / length < limit && length > 8)// length>8 can avoid the arrayCopy bound issue
            resize();
        int temp = end;
        --size;
        end = minusOne(end);
        return array[temp];
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            String message = String.format("Index %d out of bounds length %d", index, size);
            throw new IndexOutOfBoundsException(message);
        }
        return array[plusOne(index + front, length)];
    }
}
