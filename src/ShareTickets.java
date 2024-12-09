// ShareTickets.java
import java.util.*;

public class ShareTickets {

    private static Map<String, String> sharedTickets = new HashMap<>();
    
    // Method to share tickets
    public static void shareTicket(String eventName, String ticketType, int ticketCount, String friendEmail) {
        
        String shareCode = generateShareCode();
        
        String ticketDetails = "Event: " + eventName + ", Ticket Type: " + ticketType + ", Number of Tickets: " + ticketCount + ", Friend Email: " + friendEmail;
        
        sharedTickets.put(shareCode, ticketDetails);
        
        System.out.println("Your ticket has been successfully shared!");
        System.out.println("Share code: " + shareCode);
        System.out.println("Ticket Details: " + ticketDetails);
    }
    
    private static String generateShareCode() {
        return "SC" + (int) (Math.random() * 100000);
    }
    
    // Method to view shared tickets (can be used by the user to track shared tickets)
    public static void viewSharedTickets() {
        if (sharedTickets.isEmpty()) {
            System.out.println("No tickets shared yet.");
        } else {
            System.out.println("Shared Tickets:");
            for (Map.Entry<String, String> entry : sharedTickets.entrySet()) {
                System.out.println("Share Code: " + entry.getKey() + " | Details: " + entry.getValue());
            }
        }
    }
    
    // Main method to test ticket sharing functionality
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Assuming a logged-in user
        System.out.println("Enter the event name for ticket sharing: ");
        String eventName = scanner.nextLine();
        
        System.out.println("Enter ticket type (e.g., General, VIP): ");
        String ticketType = scanner.nextLine();
        
        System.out.println("Enter the number of tickets to share: ");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();  
        
        System.out.println("Enter your friend's email: ");
        String friendEmail = scanner.nextLine();
        
        shareTicket(eventName, ticketType, ticketCount, friendEmail);
        
        viewSharedTickets();
        
        scanner.close();
    }
}
