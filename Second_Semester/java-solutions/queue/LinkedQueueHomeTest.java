package queue;

public class LinkedQueueHomeTest {
    public static void main(String[] args) {
        LinkedQueue queue1 = new LinkedQueue();
        LinkedQueue queue2 = new LinkedQueue();
        for (int i = 0; i < 10; i++) {
            queue1.enqueue("e " + i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue1.dequeue() + " " + queue1.size());
        }
        for (int i = 0; i < 20; i++) {
            queue2.enqueue("e " + i * 10);
        }
        while (!queue2.isEmpty()) {
            System.out.println(queue2.dequeue() + " " + queue2.size());
        }
    }
}
