public class ArrayDeque<ListItem> {
    private ListItem[] array;
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
        if (((double)(size - 1) / length) < limit) {
            ListItem[] newArray = (ListItem[])new Object[length / RESIZE_FACTOR];
            arrayCopy((ListItem[])newArray);
            length /= RESIZE_FACTOR;
            array = newArray;
        } else if (size + 1 >= length) {
            ListItem[] newArray = (ListItem[])new Object[(size + 1) * RESIZE_FACTOR];
            arrayCopy((ListItem[])newArray);
            length = (size + 1) * RESIZE_FACTOR;
            array = newArray;
        }
    }

    private void arrayCopy(ListItem[] newArray) {
        if (front < end) {
            for (int i = front + 1, j = 1; i <= end; ++i, ++j) {
                newArray[j] = array[i];
            }
        } else {
            int j = 1;
            for (int i = front + 1; i < length; ++i, ++j) {
                newArray[j] = array[i];
            }
            for (int i = 0; i <= end; ++i, ++j) {
                newArray[j] = array[i];
            }
        }
        front = 0;
        end = size;
    }

    public void addFirst(ListItem item) {
        if (item == null)
            return;
        if (size + 1 >= length)
            resize();
        ++size;
        array[front] = item;
        if (front - 1 < 0) {
            front = length - 1;
            return;
        }
        --front;
    }

    public void addLast(ListItem item) {
        if (item == null)
            return;
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

    public int length() {
        return length - 1;
    }

    public void printDeque() {
        if (size == 0)
            return;
        if (front < end) {
            for (int i = (front + 1) % length; i <= end - 1; ++i) {
                System.out.print(array[i] + " ");
            }
            System.out.println(array[end]);
        } else {
            for (int i = front + 1; i < length; ++i) {
                System.out.print(array[i] + " ");
            }
            for (int i = 0; i < end; ++i) {
                System.out.print(array[i] + " ");
            }
            System.out.println(array[end]);
        }

    }

    public ListItem removeFirst() {
        if (isEmpty())
            return null;
        if ((double)(size - 1) / length < limit)
            resize();
        front = (front + 1) % length;
        --size;
        return array[front];
    }

    public ListItem removeLast() {
        if (isEmpty())
            return null;
        if ((double)(size - 1) / length < limit)
            resize();
        int temp = end;
        --size;
        if (end - 1 < 0) {
            end = length - 1;
            return array[temp];
        }
        --end;
        return array[temp];
    }

    public ListItem get(int index) {
        if (index < 0 || index >= size) {
            String message = String.format("Index %d out of length %d", index, size);
            throw new IndexOutOfBoundsException(message);
        }
        return array[(++index + front) % length];
    }
}
