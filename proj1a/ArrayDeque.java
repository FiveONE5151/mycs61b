public class ArrayDeque<ListItem> {
    private ListItem[] array;
    private int size = 8;
    private int length;
    private final double limit = 0.25;
    private final int RESIZE_FACTOR = 2;
    /**
     * Both front and end pointing to the same place means the array is empty (end+1)%length==front means the array is
     * full
     */
    private int front = 0;
    private int end = 0;

    public ArrayDeque() {
        length = size * RESIZE_FACTOR;
        array = (ListItem[])new Object[length];
    }

    public ArrayDeque(int size) {
        this.size = size;
        length = size * RESIZE_FACTOR;
        array = (ListItem[])new Object[length];
    }

    public ArrayDeque(ArrayDeque other) {

    }

    private void resize() {
        if ((size / length) < limit) {
            ListItem[] newArray = (ListItem[])new Object[length / RESIZE_FACTOR];
            System.arraycopy(array, (front + 1) % length, newArray, 1, size);
            front = 0;
            end = size;
            length /= RESIZE_FACTOR;
            array = newArray;
        } else if (size >= length) {
            ListItem[] newArray = (ListItem[])new Object[length * RESIZE_FACTOR];
            System.arraycopy(array, (front + 1) % length, newArray, 1, size);
            front = 0;
            end = size;
            length *= RESIZE_FACTOR;
            array = newArray;
        }
    }

    public void addFirst(ListItem item) {
        if (size + 1 >= length)
            resize();
        ++size;
        array[front] = item;
        if (front - 1 < 0) {
            front = size - 1;
            return;
        }
        --front;
    }

    public void addLast(ListItem item) {
        if (size + 1 >= length)
            resize();
        ++size;
        end = (end + 1) % length;
        array[end] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0)
            return;
        for (int i = front + 1; i <= end - 1; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[end]);
    }

    public ListItem removeFirst() {
        if (isEmpty())
            return null;
        front = (front + 1) % length;
        return array[front];
    }

    public ListItem removeLast() {
        if (isEmpty())
            return null;
        int temp = end;
        if (end - 1 < 0)
            end = length - 1;
        return array[temp];
    }

    public ListItem get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index<0 or index>=size");
        }
        return array[++index];
    }
}
