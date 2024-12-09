import java.util.*;

// Main.java
public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Assuming these classes are already implemented correctly.
        AuthenticationSystem authSystem = new AuthenticationSystem();
        EventManagementSystem eventSystem = new EventManagementSystem();
        SearchEvents searchSystem = new SearchEvents();
        ShareTickets shareTicketsSystem = new ShareTickets();
        Scanner scanner = new Scanner(System.in);

        // Initialize the event system with sample events
        EventManagementSystem.addEvent("Talor Swift", "Concert", "New York", "2024-12-15 20:00", 100, 150, 300);
        EventManagementSystem.addEvent("Samay Raina", "Show", "San Francisco", "2024-12-10 09:00", 200, 120, 250);
        EventManagementSystem.addEvent("Broadway Show", "Theater", "New York", "2024-12-15 19:00", 150, 80, 250);

        // Initialize index maps for search and filter
        Map<String, List<Event>> categoryIndex = new HashMap<>();
        Map<String, List<Event>> locationIndex = new HashMap<>();
        Map<String, List<Event>> dateTimeIndex = new HashMap<>();
        Map<Double, List<Event>> priceIndex = new HashMap<>();

        // Build indexes for fast searching
        SearchEvents.buildIndexes(EventManagementSystem.viewEvents(), categoryIndex, locationIndex, dateTimeIndex, priceIndex);

        while (true) {
            System.out.println("\n**** WELCOME TO THE TICKET BOOKING SYSTEM ****");
            System.out.println("Are you an existing user? (y/n)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                System.out.println("1. Login");
                System.out.println("2. Forgot Password");
                System.out.print("Choose an option: ");
                String option = scanner.nextLine().trim();

                if (option.equals("1")) {
                    authSystem.login(scanner);
                } else if (option.equals("2")) {
                    authSystem.forgotPassword(scanner);
                } else {
                    System.out.println("Invalid option. Returning to the main menu.");
                }
            } else if (choice.equals("n")) {
                authSystem.signUp(scanner);
            } else {
                System.out.println("Invalid input. Please type 'y' or 'n'.");
            }

            if (authSystem.isLoggedIn()) {
                // User logged in, display event management options
                System.out.println("\nWelcome, " + authSystem.loggedInUser.username + "!");
                while (true) {
                    System.out.println("\n==== Main Menu ====");
                    System.out.println("1. View Events");
                    System.out.println("2. Search and Filter Events");
                    System.out.println("3. Book Ticket");
                    System.out.println("4. View Booking History");
                    System.out.println("5. Share Tickets");
                    System.out.println("6. Logout");
                    System.out.print("Choose an option: ");
                    String option = scanner.nextLine().trim();

                    switch (option) {
                        case "1":
                            EventManagementSystem.viewEvents();
                            break;
                        case "2":
                            searchAndFilterEvents(scanner, searchSystem, categoryIndex, locationIndex, dateTimeIndex, priceIndex);
                            break;
                        case "3":
                            BookTicket.book(EventManagementSystem.viewEvents(), scanner, authSystem.loggedInUser);  // Book ticket functionality
                            break;
                        case "4":
                            viewBookingHistory(authSystem.loggedInUser);  // View booking history functionality
                            break;
                        case "5":
                            shareTicket(scanner);  // Share ticket functionality
                            break;
                        case "6":
                            authSystem.logout();
                            return; // exit the inner loop and go back to main menu
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }

            System.out.println("Do you want to continue? (y/n)");
            String continueChoice = scanner.nextLine().trim().toLowerCase();

            if (continueChoice.equals("n")) {
                System.out.println("Exiting... Goodbye!");
                break;
            }
        }
    }

    private static void searchAndFilterEvents(Scanner scanner, SearchEvents searchSystem,
                                               Map<String, List<Event>> categoryIndex,
                                               Map<String, List<Event>> locationIndex,
                                               Map<String, List<Event>> dateTimeIndex,
                                               Map<Double, List<Event>> priceIndex) {
        System.out.println("\n==== Search and Filter Events ====");
        System.out.println("1. By Category");
        System.out.println("2. By Location");
        System.out.println("3. By Date/Time");
        System.out.println("4. By Price Range");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose a filter: ");
        int filterOption = Integer.parseInt(scanner.nextLine().trim());

        searchSystem.search(EventManagementSystem.viewEvents(), categoryIndex, locationIndex, dateTimeIndex, priceIndex, filterOption, scanner);
    }

    // Method to share a ticket with a friend
    private static void shareTicket(Scanner scanner) {
        System.out.println("\n==== Share Ticket ====");
        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter ticket type (General/VIP): ");
        String ticketType = scanner.nextLine();

        System.out.print("Enter number of tickets to share: ");
        int ticketCount = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your friend's email: ");
        String friendEmail = scanner.nextLine();

        // Call ShareTickets class method to share the ticket
        ShareTickets.shareTicket(eventName, ticketType, ticketCount, friendEmail);
        
    }

    // Method to view the booking history of the logged-in user
    private static void viewBookingHistory(User loggedInUser) {
        System.out.println("\n==== Booking History ====");
        if (loggedInUser.getBookingHistory().isEmpty()) {
            System.out.println("You have no booking history yet.");
        } else {
            for (BookingHistory booking : loggedInUser.getBookingHistory()) {
                System.out.println(booking);  // Calls the toString method of BookingHistory
                System.out.println("------------------------------");
            }
        }
    }
}
