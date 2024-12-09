import java.util.*;

public class SearchEvents {

    // Search events by various criteria with index maps
    public void search(Map<String, Event> events, Map<String, List<Event>> categoryIndex,
                              Map<String, List<Event>> locationIndex, Map<String, List<Event>> dateTimeIndex,
                              Map<Double, List<Event>> priceIndex, int searchType, Scanner scanner) {

        switch (searchType) {
            case 1: // Category
                System.out.print("Enter category (Movies/Shows/Concerts): ");
                String category = scanner.nextLine();
                List<Event> categoryEvents = categoryIndex.get(category.toLowerCase());
                if (categoryEvents != null) {
                    categoryEvents.forEach(event -> {
                        System.out.println(event);
                        System.out.println("-------------------------------");
                    });
                } else {
                    System.out.println("No events found in this category.");
                }
                break;
            case 2: // Location
                System.out.print("Enter location: ");
                String location = scanner.nextLine();
                List<Event> locationEvents = locationIndex.get(location.toLowerCase());
                if (locationEvents != null) {
                    locationEvents.forEach(event -> {
                        System.out.println(event);
                        System.out.println("-------------------------------");
                    });
                } else {
                    System.out.println("No events found at this location.");
                }
                break;
            case 3: // DateTime
                System.out.print("Enter date/time (e.g., 2024-12-10 19:00): ");
                String dateTime = scanner.nextLine();
                List<Event> dateTimeEvents = dateTimeIndex.get(dateTime);
                if (dateTimeEvents != null) {
                    dateTimeEvents.forEach(event -> {
                        System.out.println(event);
                        System.out.println("-------------------------------");
                    });
                } else {
                    System.out.println("No events found at this date/time.");
                }
                break;
            case 4: // Price Range
                System.out.print("Enter price range (min max): ");
                double minPrice = scanner.nextDouble();
                double maxPrice = scanner.nextDouble();
                scanner.nextLine();  // Consume the newline

                // Iterate through priceIndex to find matching events in the price range
                priceIndex.entrySet().forEach(entry -> {
                    if (entry.getKey() >= minPrice && entry.getKey() <= maxPrice) {
                        entry.getValue().forEach(event -> {
                            System.out.println(event);
                            System.out.println("-------------------------------");
                        });
                    }
                });
                break;
            case 5:
                System.out.println("Back to main menu");
                break;
            default:
                System.out.println("Invalid search type.");
        }
    }

    // This method can be used to build the indexes for fast searching
    public static void buildIndexes(Map<String, Event> events, Map<String, List<Event>> categoryIndex,
                                    Map<String, List<Event>> locationIndex, Map<String, List<Event>> dateTimeIndex,
                                    Map<Double, List<Event>> priceIndex) {

        for (Event event : events.values()) {
            // Build category index
            categoryIndex.computeIfAbsent(event.getEventType().toLowerCase(), k -> new ArrayList<>()).add(event);
            
            // Build location index
            locationIndex.computeIfAbsent(event.getLocation().toLowerCase(), k -> new ArrayList<>()).add(event);
            
            // Build dateTime index
            dateTimeIndex.computeIfAbsent(event.getDateTime(), k -> new ArrayList<>()).add(event);

            // Build price index (for general price, you can also index vip prices similarly)
            priceIndex.computeIfAbsent(event.getGeneralTicketPrice(), k -> new ArrayList<>()).add(event);
        }
    }
}
