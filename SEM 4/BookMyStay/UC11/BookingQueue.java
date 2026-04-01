import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private final Queue<Reservation> queue = new LinkedList<>();

    public synchronized void submit(Reservation reservation) {
        queue.offer(reservation);
        notifyAll();
    }

    public synchronized Reservation retrieve() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
