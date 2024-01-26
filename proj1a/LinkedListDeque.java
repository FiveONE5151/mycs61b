public class LinkedListDeque<ListItem> {
    private class Node {
        private Node prev;
        private ListItem item;
        private Node next;

        Node() {
            prev = null;
            item = null;
            next = null;
        }

        Node(ListItem item) {
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

        ListItem getItem() {
            return item;
        }

        public void setItem(ListItem item) {
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

    public LinkedListDeque(LinkedListDeque other) {
        if (other == null || other.size() == 0)
            return;
        Node current = this.sentinel.next;
        Node ptr = other.sentinel.next;
        Node prev = this.sentinel;
        while (ptr != other.sentinel) {
            current.item = ptr.item;
            current.prev = prev;
            current = current.next;
            prev = prev.next;
            ptr = ptr.next;
        }

    }

    public void addFirst(ListItem item) {
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

    public void addLast(ListItem item) {
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

    public ListItem removeFirst() {
        if (sentinel.next == null || size == 0)
            return null;
        ListItem first = sentinel.next.getItem();
        Node secondNode = sentinel.next.next;
        secondNode.prev = sentinel;
        sentinel.next = secondNode;
        --size;
        return first;
    }

    public ListItem removeLast() {
        if (sentinel.prev == null || size == 0)
            return null;
        ListItem last = sentinel.prev.item;
        Node secondLastNode = sentinel.prev.prev;
        sentinel.prev = secondLastNode;
        secondLastNode.next = sentinel;
        --size;
        return last;
    }

    public ListItem get(int index) {
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

    private ListItem getRecursion(int cnt, int index, Node ptr) {
        if (cnt == index)
            return ptr.item;
        ptr = ptr.next;
        return getRecursion(++cnt, index, ptr);
    }

    public ListItem getRecursion(int index) {
        if (index >= size)
            return null;
        int cnt = 0;
        Node ptr = sentinel.next;
        return getRecursion(cnt, index, ptr);
    }
}
