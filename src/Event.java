import java.util.Random;

public class Event {
    private String eventName;
    private String eventType;
    private String location;
    private String dateTime;
    private int totalSeats;
    private int availableSeats;
    private double generalTicketPrice;
    private double vipTicketPrice;

    // Constructor with manual price setup
    public Event(String eventName, String eventType, String location, String dateTime, int totalSeats,
                 double generalTicketPrice, double vipTicketPrice) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.location = location;
        this.dateTime = dateTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.generalTicketPrice = generalTicketPrice;
        this.vipTicketPrice = vipTicketPrice;
    }

    // Constructor with random price generation
    public Event(String eventName, String eventType, String location, String dateTime, int totalSeats) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.location = location;
        this.dateTime = dateTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;

        // Randomly generate ticket prices
        Random random = new Random();
        this.generalTicketPrice = 100 + random.nextDouble() * 400; // General: $100 to $500
        this.vipTicketPrice = 500 + random.nextDouble() * 1000;   // VIP: $500 to $1500
    }

    // Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getLocation() {
        return location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getGeneralTicketPrice() {
        return generalTicketPrice;
    }

    public double getVipTicketPrice() {
        return vipTicketPrice;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setGeneralTicketPrice(double generalTicketPrice) {
        this.generalTicketPrice = generalTicketPrice;
    }

    public void setVipTicketPrice(double vipTicketPrice) {
        this.vipTicketPrice = vipTicketPrice;
    }

    // Book tickets for an event
    public boolean bookTickets(int numberOfTickets, boolean isVIP) {
        double price = isVIP ? vipTicketPrice : generalTicketPrice;
        if (availableSeats >= numberOfTickets) {
            availableSeats -= numberOfTickets;
            System.out.println("Booking Successful. Total cost: " + (price * numberOfTickets));
            return true;
        } else {
            System.out.println("Not enough seats available.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName + "\n" +
               "Event Type: " + eventType + "\n" +
               "Location: " + location + "\n" +
               "Date/Time: " + dateTime + "\n" +
               "Price (General): $" + String.format("%.2f", generalTicketPrice) + "\n" +
               "Price (VIP): $" + String.format("%.2f", vipTicketPrice) + "\n" +
               "Total Seats: " + totalSeats + "\n" +
               "Available Seats: " + availableSeats + "\n";
    }
}
