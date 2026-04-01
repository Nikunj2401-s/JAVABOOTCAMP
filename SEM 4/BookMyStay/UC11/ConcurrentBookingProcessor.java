public class ConcurrentBookingProcessor implements Runnable {
    private final String workerName;
    private final BookingQueue bookingQueue;
    private final RoomInventory inventory;

    public ConcurrentBookingProcessor(String workerName, BookingQueue bookingQueue, RoomInventory inventory) {
        this.workerName = workerName;
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            Reservation request = bookingQueue.retrieve();
            if (request == null) break;
            if ("STOP".equals(request.getReservationId())) {
                System.out.println("[" + workerName + "] Received STOP signal. Exiting.");
                bookingQueue.submit(request); // allow other threads to stop as well
                break;
            }

            synchronized (inventory) {
                boolean allocated = inventory.allocateRoom(request.getRoomType());
                if (allocated) {
                    System.out.println("[" + workerName + "] Allocated " + request.getRoomType() + " for " + request.getGuestName() + " (" + request.getReservationId() + ")");
                } else {
                    System.out.println("[" + workerName + "] Failed " + request.getGuestName() + " (" + request.getReservationId() + "): no " + request.getRoomType() + " available");
                }
            }
        }
    }
}
