import java.util.LinkedList;

public class User {
    String username;
    private String email;
    String ph_no;
    private LinkedList<BookingHistory> bookingHistory; // LinkedList to store booking history
    public String password;

    public User(String username, String email, String password, String ph_no) {
        this.username = username;
        this.email = email;
        this.ph_no = ph_no;
        this.password = password;
        this.bookingHistory = new LinkedList<>(); // Initialize with a LinkedList
    }

    // Add a booking to the history
    public void addBooking(BookingHistory booking) {
        bookingHistory.add(booking); // Add booking to the LinkedList
    }

    // View the booking history
    public void viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("You have no booking history.");
        } else {
            for (BookingHistory booking : bookingHistory) {
                System.out.println(booking);
                System.out.println("-------------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Email: " + email + ", Phone: " + ph_no;
    }

    // Correct implementation of getBookingHistory to return booking history
    public LinkedList<BookingHistory> getBookingHistory() {
        return bookingHistory;  // Return the LinkedList of booking history
    }
}
