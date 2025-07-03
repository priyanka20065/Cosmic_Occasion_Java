import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private Queue<Booking> bookings = new LinkedList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void listBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }
        System.out.println("\n=== List of Bookings ===");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
