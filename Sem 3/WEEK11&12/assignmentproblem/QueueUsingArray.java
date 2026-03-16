public class QueueUsingArray {
    int front, rear, size;
    int[] queue;

    public QueueUsingArray(int capacity) {
        front = rear = -1;
        size = capacity;
        queue = new int[size];
    }

    void enqueue(int item) {
        if (rear == size - 1) {
            System.out.println("Queue is Full");
            return;
        }
        if (front == -1)
            front = 0;
        queue[++rear] = item;
        System.out.println(item + " inserted");
    }

    void dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.println(queue[front++] + " removed");
    }

    void peek() {
        if (front == -1 || front > rear)
            System.out.println("Queue is Empty");
        else
            System.out.println("Front element: " + queue[front]);
    }

    void display() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.print("Queue elements: ");
        for (int i = front; i <= rear; i++)
            System.out.print(queue[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        QueueUsingArray q = new QueueUsingArray(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();
        q.dequeue();
        q.peek();
        q.display();
    }
}
