import java.util.*;

public class EventManagementSystem {
    private static TreeMap<String, Event> events = new TreeMap<>(); // TreeMap to store events sorted by event name

    // Add a new event
    public static void addEvent(String eventName, String eventType, String location, String dateTime,
                                 int totalSeats, double generalTicketPrice, double vipTicketPrice) {
        Event event = new Event(eventName, eventType, location, dateTime, totalSeats, generalTicketPrice, vipTicketPrice);
        events.put(eventName, event);
        System.out.println("Event added successfully.");
    }

    // Update event details (Admin only)
    public static void updateEvent(String eventName, String newEventName, String newEventType, String newLocation,
                                    String newDateTime, int newTotalSeats, double newGeneralTicketPrice, double newVipTicketPrice) {
        if (events.containsKey(eventName)) {
            Event event = events.get(eventName);
            event.setEventName(newEventName);
            event.setEventType(newEventType);
            event.setLocation(newLocation);
            event.setDateTime(newDateTime);
            event.setGeneralTicketPrice(newGeneralTicketPrice);
            event.setVipTicketPrice(newVipTicketPrice);
            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    // Remove event (Admin only)
    public static void removeEvent(String eventName) {
        if (events.containsKey(eventName)) {
            events.remove(eventName);
            System.out.println("Event removed successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    // View all events
    public static Map<String, Event> viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events.values()) {
                System.out.println(event);
                System.out.println("-------------------------------");
            }
        }
                return events;
    }

    // View event by name
    public static void viewEventByName(String eventName) {
        Event event = events.get(eventName);
        if (event != null) {
            System.out.println(event);
        } else {
            System.out.println("Event not found.");
        }
    }
}
