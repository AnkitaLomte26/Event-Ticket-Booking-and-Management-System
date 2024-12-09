// BookTicket.java
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BookTicket {
    private static Queue<BookingRequest> regularBookingQueue = new LinkedList<>(); // Queue for regular bookings
    private static PriorityQueue<BookingRequest> vipBookingQueue = new PriorityQueue<>(new Comparator<BookingRequest>() {
        @Override
        public int compare(BookingRequest o1, BookingRequest o2) {
            // Higher priority for VIP requests
            return Boolean.compare(o2.isVIP(), o1.isVIP());
        }
    }); // Priority queue for VIP bookings

    public static void book(Map<String, Event> events, Scanner scanner, User loggedInUser) {
        System.out.println("Enter event name to book tickets:");
        String eventName = scanner.nextLine();
        Event event = events.get(eventName);

        if (event != null) {
            System.out.println("Available seats: " + event.getAvailableSeats());

            System.out.println("Enter number of tickets to book:");
            int numberOfTickets = scanner.nextInt();

            if (numberOfTickets > event.getAvailableSeats()) {
                System.out.println("Not enough seats available. Try booking fewer tickets!");
                return;
            }

            System.out.print("Choose ticket type (1 for General, 2 for VIP): ");
            int ticketType = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            boolean isVIP = ticketType == 2;

            // Add booking request to appropriate queue
            BookingRequest bookingRequest = new BookingRequest(eventName, numberOfTickets, isVIP);
            if (isVIP) {
                vipBookingQueue.add(bookingRequest); // VIP bookings go into the priority queue
            } else {
                regularBookingQueue.add(bookingRequest); // Regular bookings go into the regular queue
            }

            System.out.println("Your booking has been added to the queue.");

            // Process both queues (VIP queue first, then regular queue)
            processQueues(events, loggedInUser);
        } else {
            System.out.println("Event not found.");
        }
    }

    // Process bookings from the priority queue and regular queue
    private static void processQueues(Map<String, Event> events, User loggedInUser) {
        // Process VIP bookings first from the priority queue
        while (!vipBookingQueue.isEmpty()) {
            BookingRequest request = vipBookingQueue.poll();  // Retrieve the next VIP booking request
            processBookingRequest(request, events, loggedInUser);
        }

        // Process regular bookings from the queue
        while (!regularBookingQueue.isEmpty()) {
            BookingRequest request = regularBookingQueue.poll();  // Retrieve the next regular booking request
            processBookingRequest(request, events, loggedInUser);
        }
    }

    // Process each booking request (either VIP or regular)
    private static void processBookingRequest(BookingRequest request, Map<String, Event> events, User loggedInUser) {
        String eventName = request.getEventName();
        int numberOfTickets = request.getNumberOfTickets();
        boolean isVIP = request.isVIP();

        Event event = events.get(eventName);

        // Process the booking
        if (event != null) {
            System.out.println("Processing booking for event: " + eventName);
            if (numberOfTickets <= event.getAvailableSeats()) {
                event.bookTickets(numberOfTickets, isVIP);  // Book the tickets
                System.out.printf("Booking successful for %d %s ticket(s) at event %s.%n",
                        numberOfTickets, isVIP ? "VIP" : "General", eventName);
                // Add booking to user's history
                BookingHistory booking = new BookingHistory(eventName, isVIP, numberOfTickets, 
                        isVIP ? event.getVipTicketPrice() : event.getGeneralTicketPrice());
                loggedInUser.addBooking(booking);
            } else {
                System.out.println("Not enough seats available for your request. Adding to the waitlist.");
                // Add to the waitlist if needed (or handle differently)
            }
        }
    }
}
