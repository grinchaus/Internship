package queue;

public class ArrayQueueModuleTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue("e " + i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(ArrayQueueModule.dequeue() + " " + ArrayQueueModule.size());
        }
        for (int i = 0; i < 20; i++) {
            ArrayQueueModule.enqueue("e " + i * 10);
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue() + " " + ArrayQueueModule.size());
        }
    }
}
