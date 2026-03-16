public class CircularQueue {
    int front, rear, size;
    int[] queue;

    public CircularQueue(int capacity) {
        size = capacity;
        queue = new int[size];
        front = rear = -1;
    }

    void enqueue(int item) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is Full");
            return;
        }
        if (front == -1)
            front = 0;
        rear = (rear + 1) % size;
        queue[rear] = item;
        System.out.println(item + " inserted");
    }

    void dequeue() {
        if (front == -1) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.println(queue[front] + " removed");
        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % size;
    }

    void display() {
        if (front == -1) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.print("Queue elements: ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear) break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();
        cq.dequeue();
        cq.enqueue(40);
        cq.enqueue(50);
        cq.enqueue(60); // This will show "Queue is Full"
        cq.display();
    }
}
