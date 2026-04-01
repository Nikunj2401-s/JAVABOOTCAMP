public class UseCase11ConcurrentBookingDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v11.0");
        System.out.println("Concurrent Booking Simulation Demo");
        System.out.println("======================================\n");

        BookingQueue bookingQueue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 3);
        inventory.addRoomType("Double Room", 2);

        Thread t1 = new Thread(new ConcurrentBookingProcessor("Worker-1", bookingQueue, inventory));
        Thread t2 = new Thread(new ConcurrentBookingProcessor("Worker-2", bookingQueue, inventory));
        Thread t3 = new Thread(new ConcurrentBookingProcessor("Worker-3", bookingQueue, inventory));

        t1.start();
        t2.start();
        t3.start();

        bookingQueue.submit(new Reservation("R-300", "Alice", "Single Room"));
        bookingQueue.submit(new Reservation("R-301", "Bob", "Double Room"));
        bookingQueue.submit(new Reservation("R-302", "Charlie", "Single Room"));
        bookingQueue.submit(new Reservation("R-303", "Diana", "Single Room"));
        bookingQueue.submit(new Reservation("R-304", "Eve", "Double Room"));
        bookingQueue.submit(new Reservation("R-305", "Frank", "Suite Room")); // invalid type causes fail

        // stop signals
        bookingQueue.submit(new Reservation("STOP", "", ""));

        t1.join();
        t2.join();
        t3.join();

        inventory.displayInventory();
        System.out.println("\n[Result] Concurrent booking processing complete.");
    }
}
