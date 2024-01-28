/**
 * Doubly-Linked Circular List Circular design can avoid the special cases in add and remove methods e.g. When size of
 * the list is 1, remove last requires both sentF and sentB to move to the previous node, which is the sentinel node but
 * in normal cases, it only requires the sentB to move to the previous node. it can also avoid the size==0 case
 *
 * @param <T>
 */
public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node() {
            prev = null;
            item = null;
            next = null;
        }

        Node(T item) {
            prev = null;
            this.item = item;
            next = null;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null);
        //even if there is only a single sentinel node, you should make it circular!
        //especially important for making your code cleaner
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T... items) {
        sentinel = new Node(null);
        //even if there is only a single sentinel node, you should make it circular!
        //especially important for making your code cleaner
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (T item : items) {
            addLast(item);
        }
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null);
        //even if there is only a single sentinel node, you should make it circular!
        //especially important for making your code cleaner
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        if (other == null || other.size() == 0)
            return;
        for (int i = 0; i < other.size(); i++) {
            this.addLast((T)other.get(i));
        }
        /*Node current = this.sentinel;
        Node ptr = other.sentinel.next;
        while (ptr != other.sentinel) {
            current.next = new Node(ptr.item);
            current.next.prev = current;
            current = current.next;
            ptr = ptr.next;
            ++size;
        }
        current.next = this.sentinel;
        this.sentinel.prev = current;*/
    }

    public void addFirst(T item) {
        //can be omitted for the circular design(sentinel node)
        //        if (size == 0) {
        //            sentinel.next = new Node(item);
        //            sentinel.prev = sentinel.next;
        //            sentinel.next.prev = sentinel;
        //            sentinel.next.next = sentinel;
        //            ++size;
        //            return;
        //        }
        Node first = new Node(item);
        first.next = sentinel.next;
        sentinel.next.prev = first;
        first.prev = sentinel;
        sentinel.next = first;
        ++size;
    }

    public void addLast(T item) {
        //can be omitted for the circular design(sentinel node)
        //        if (size == 0) {
        //            sentinel.next = new Node(item);
        //            sentinel.prev = sentinel.next;
        //            sentinel.next.prev = sentinel;
        //            sentinel.next.next = sentinel;
        //            ++size;
        //            return;
        //        }
        Node last = new Node(item);
        last.prev = sentinel.prev;
        sentinel.prev.next = last;
        last.next = sentinel;
        sentinel.prev = last;
        ++size;
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
        Node ptr = sentinel.next;
        while (sentinel != ptr.next) {
            System.out.print(ptr.item);
            System.out.print(" ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item);
    }

    public T removeFirst() {
        if (sentinel.next == null || size == 0)
            return null;
        T first = sentinel.next.getItem();
        Node secondNode = sentinel.next.next;
        secondNode.prev = sentinel;
        sentinel.next = secondNode;
        --size;
        return first;
    }

    public T removeLast() {
        if (sentinel.prev == null || size == 0)
            return null;
        T last = sentinel.prev.item;
        Node secondLastNode = sentinel.prev.prev;
        sentinel.prev = secondLastNode;
        secondLastNode.next = sentinel;
        --size;
        return last;
    }

    public T get(int index) {
        if (index >= size)
            return null;
        int cnt = 0;
        Node ptr = sentinel.next;
        while (sentinel != ptr) {
            if (cnt == index)
                return ptr.item;
            ++cnt;
            ptr = ptr.next;
        }
        return null;
    }

    private T getRecursive(int cnt, int index, Node ptr) {
        if (cnt == index)
            return ptr.item;
        ptr = ptr.next;
        return getRecursive(++cnt, index, ptr);
    }

    public T getRecursive(int index) {
        if (index >= size)
            return null;
        int cnt = 0;
        Node ptr = sentinel.next;
        return getRecursive(cnt, index, ptr);
    }
}
