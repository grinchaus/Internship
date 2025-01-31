package queue;

public class ArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT queue1 = new ArrayQueueADT();
        ArrayQueueADT queue2 = new ArrayQueueADT();
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue1,"e " + i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(ArrayQueueADT.dequeue(queue1) + " " + ArrayQueueADT.size(queue1));
        }
        for (int i = 0; i < 20; i++) {
            ArrayQueueADT.enqueue(queue2,"e " + i * 10);
        }
        while (!ArrayQueueADT.isEmpty(queue2)) {
            System.out.println(ArrayQueueADT.dequeue(queue2) + " " + ArrayQueueADT.size(queue2));
        }
    }
}
