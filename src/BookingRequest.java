// BookingRequest.java
public class BookingRequest {
    private String eventName;
    private int numberOfTickets;
    private boolean isVIP;

    public BookingRequest(String eventName, int numberOfTickets, boolean isVIP) {
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.isVIP = isVIP;
    }

    public String getEventName() {
        return eventName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public boolean isVIP() {
        return isVIP;
    }
}

