package queue;

    /*
    Model: a[1]..a[n]
    Invariant: n >= 0 && for i = 1..n: a`[i] != null

    Let immutable(n): for i = 1..n: a`[i] == a[i]
     */

public interface Queue {

    //Pred: true
    //Post: сохранение порядка элементов && уйдут только повторы && стануться элементы в очереди
    //Post: b[n], k = 0 for i=0..(n - 1): if a[i] != a[i + 1]: b.enqueue, k` = k + 1 &&
    // && a = b && n` = n - k && n` > 0
    void dedup();

    //Pred: element != null
    //Post: n` = n + 1 && a[n`] = element && immutable(n)
    void enqueue(final Object element);

    //Pred: n >= 1
    //Post: n` = n - 1 && R == a[1] &&
    // && for i = 1..(n - 1): a[i] = a[i + 1] && immutable(n`)
    Object dequeue();

    //Pred: true
    //Post: n` = 0
    void clear();

    //Pred: n >= 1
    //Post: n` == n && R == a[1] && immutable(n`)
    Object element();

    //Pred: true
    //Post: n` == n && R == n && immutable(n`)
    int size();

    //Pred: true
    //Post: n` == n && R == (n == 0) && immutable(n`)
    boolean isEmpty();
}
