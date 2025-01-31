package queue;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head, tail;

    private int countIfFor(int start, int end, final Predicate<Object> predict){
        int kol = 0;
        for (int i = start; i < end; i++) {
            if (predict.test(elements[i])) {
                kol += 1;
            }
        }
        return kol;
    }

    public int countIf(final Predicate<Object> predict) {
        int kol = 0;
        if (head > tail) {
            kol +=  countIfFor(head, elements.length, predict);
            kol +=  countIfFor(0, tail, predict);
        } else {
            kol += countIfFor(head, tail, predict);
        }
        return kol;
    }

    private void ensureCapacity() {
        if (size() + 1 >= elements.length) {
            int len = elements.length;
            Object[] elementsToTail = Arrays.copyOfRange(elements, 0, tail % (head + 1));
            elements = Arrays.copyOfRange(elements, head, 2 * elements.length);
            System.arraycopy(elementsToTail, 0, elements, len - head, elementsToTail.length);
            head = 0;
            tail = len - 1;
        }
    }

    protected void enqueueIml(final Object element) {
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    protected Object dequeueIml() {
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    protected void clearIml() {
        elements = new Object[elements.length];
        tail = head = 0;
    }

    protected Object elementIml() {
        return elements[head];
    }
}