package queue;

public class LinkedQueue extends AbstractQueue {

    private Node head, tail;

    private static class Node {
        private final Object element;
        private Node prev;

        private Node(Object element, Node prev) {
            this.element = element;
            this.prev = prev;
        }
    }

    protected void enqueueIml(final Object input) {
        Node element = tail;
        tail = new Node(input, null);
        if (size == 0) {
            head = tail;
        } else {
            element.prev = tail;
        }
    }

    protected Object dequeueIml() {
        Node element = head;
        head = head.prev;
        return element.element;
    }

    protected void clearIml() {
        tail = head = null;
    }

    protected Object elementIml() {
        return head.element;
    }
}