package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {

    protected int size = 0;

    public void enqueue(final Object element) {
        Objects.requireNonNull(element);
        enqueueIml(element);
        size++;
    }

    public Object dequeue() {
        assert size >= 1;
        size--;
        return dequeueIml();
    }

    public void clear() {
        size = 0;
        clearIml();
    }

    public Object element() {
        assert size >= 1;
        return elementIml();
    }

    public void dedup() {
        Object element;
        for (int i = size - 1; i >= 0; i--){
            element = dequeue();
            if (i == 0 || !element().equals(element)) {
                enqueue(element);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract Object elementIml();

    protected abstract void clearIml();

    protected abstract Object dequeueIml();

    protected abstract void enqueueIml(final Object element);
}
