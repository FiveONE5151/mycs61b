package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package

// package <package name>;

import java.util.Iterator;

// TODO: Make sure to make this class and all of its methods public
// TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first; // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[])new Object[capacity + 1];
    }

    private int plusOne(int index) {
        return (++index) % capacity;
    }

    private int minusOne(int index) {
        if (index == 0)
            return capacity - 1;
        return --index;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then throw new RuntimeException("Ring buffer
     * overflow"). Exceptions covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity)
            throw new RuntimeException("Ring buffer overflow");
        ++fillCount;
        last = plusOne(last);
        rb[last] = x;
    }

    /**
     * Dequeue the oldest item in the ring buffer. If the buffer is empty, then throw new RuntimeException("Ring buffer
     * underflow"). Exceptions covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount == 0)
            throw new RuntimeException("Ring buffer overflow");
        --fillCount;
        first = plusOne(first);
        return rb[first];
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount == 0)
            throw new RuntimeException("The buffer is empty");
        return rb[plusOne(first)];
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }

    private class arIterator implements Iterator<T> {

        private int ptr;

        public arIterator() {
            ptr = plusOne(first);
        }

        @Override
        public boolean hasNext() {
            return ptr != last;
        }

        @Override
        public T next() {
            T res = rb[ptr];
            ptr = plusOne(ptr);
            return res;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new arIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
