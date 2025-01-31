package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

    /*
    Model: a[1]..a[n]
    Invariant: n >= 0 && for i = 1..n: a`[i] != null

    Let immutable(n): for i = 1..n: a`[i] == a[i]
     */

public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head, tail;

    private static int countIfFor(final ArrayQueueADT queue, int start, int end, final Predicate<Object> predict){
        int kol = 0;
        for (int i = start; i < end; i++) {
            if (predict.test(queue.elements[i])) {
                kol += 1;
            }
        }
        return kol;
    }

    private static void ensureCapacity(ArrayQueueADT queue) {
        if (size(queue) + 1 >= queue.elements.length) {
            int len = queue.elements.length;
            Object[] elementsToTail = Arrays.copyOfRange(queue.elements, 0, queue.tail % (queue.head + 1));
            queue.elements = Arrays.copyOfRange(queue.elements, queue.head, 2 * queue.elements.length);
            System.arraycopy(elementsToTail, 0, queue.elements, len - queue.head, elementsToTail.length);
            queue.head = 0;
            queue.tail = len - 1;
        }
    }

    //Pred: predict != null
    //Post: kol = 0 for i = 1..n: if(predict.test(a[i]) == true): kol` = kol + 1 &&
    // && R == kol` && immutable(n)
    public static int countIf(final ArrayQueueADT queue, final Predicate<Object> predict) {
        int kol = 0;
        if (queue.head > queue.tail) {
            kol +=  countIfFor(queue,queue.head, queue.elements.length, predict);
            kol +=  countIfFor(queue,0, queue.tail, predict);
        } else {
           kol += countIfFor(queue,queue.head, queue.tail, predict);
        }
        return kol;
    }

    //Pred: element != null
    //Post: n` == n + 1 && a[n`] = element && immutable(n)
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    //Pred: n >= 1
    //Post: n` = n - 1 && R == a[1] &&
    // && for i = 1..(n - 1): a[i] = a[i + 1] && immutable(n`)
    public static Object dequeue(final ArrayQueueADT queue) {
        assert size(queue) >= 1;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return result;
    }

    //Pred: true
    //Post: n` = 0
    public static void clear(final ArrayQueueADT queue) {
        queue.elements = new Object[queue.elements.length];
        queue.tail = queue.head = 0;
    }

    //Pred: n >= 1
    //Post: n` == n && R == a[1] && immutable(n`)
    public static Object element(final ArrayQueueADT queue) {
        assert size(queue) >= 1;
        return queue.elements[queue.head];
    }

    //Pred: true
    //Post: n` == n && R == n && immutable(n`)
    public static int size(final ArrayQueueADT queue) {
        if (queue.head > queue.tail) {
            return queue.elements.length - queue.head + queue.tail;
        } else {
            return queue.tail - queue.head;
        }
    }

    //Pred: true
    //Post: n` == n && R == (n == 0) && immutable(n`)
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return  queue.head == queue.tail;
    }
}