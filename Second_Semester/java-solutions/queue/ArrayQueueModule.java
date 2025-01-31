package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

    /*
    Model: a[1]..a[n]
    Invariant: n >= 0 && for i = 1..n: a`[i] != null

    Let immutable(n): for i = 1..n: a`[i] == a[i]
     */

public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head, tail;

    private static int countIfFor(int start, int end, final Predicate<Object> predict){
        int kol = 0;
        for (int i = start; i < end; i++) {
            if (predict.test(elements[i])) {
                kol += 1;
            }
        }
        return kol;
    }

    private static void ensureCapacity() {
        if (size() + 1 >= elements.length) {
            int len = elements.length;
            Object[] elementsToTail = Arrays.copyOfRange(elements, 0, tail % (head + 1));
            elements = Arrays.copyOfRange(elements, head, 2 * elements.length);
            System.arraycopy(elementsToTail, 0, elements, len - head, elementsToTail.length);
            head = 0;
            tail = len - 1;
        }
    }

    //Pred: predict != null
    //Post: kol = 0 for i = 1..n: if(predict.test(a[i]) == true): kol` = kol + 1 &&
    // && R == kol` && immutable(n)
    public static int countIf(final Predicate<Object> predict) {
        int kol = 0;
        if (head > tail) {
            kol +=  countIfFor(head, elements.length, predict);
            kol +=  countIfFor(0, tail, predict);
        }else {
            kol += countIfFor(head, tail, predict);
        }
        return kol;
    }

    //Pred: element != null
    //Post: n` == n + 1 && a[n`] = element && immutable(n)
    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    //Pred: n >= 1
    //Post: n` = n - 1 && R == a[1] &&
    // && for i = 1..(n - 1): a[i] = a[i + 1] && immutable(n`)
    public static Object dequeue() {
        assert size() >= 1;
        Object result = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return result;
    }

    //Pred: true
    //Post: n` = 0
    public static void clear() {
        elements = new Object[elements.length];
        tail = head = 0;
    }

    //Pred: n >= 1
    //Post: n` == n && R == a[1] && immutable(n`)
    public static Object element() {
        assert size() >= 1;
        return elements[head];
    }

    //Pred: true
    //Post: n` == n && R == n && immutable(n`)
    public static int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else {
            return tail - head;
        }
    }

    //Pred: true
    //Post: n` == n && R == (n == 0) && immutable(n`)
    public static boolean isEmpty() {
        return head == tail;
    }
}