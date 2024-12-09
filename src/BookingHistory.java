public class BookingHistory {
    private String eventName;
    private boolean isVIP;
    private int numberOfTickets;
    private double totalCost;

    public BookingHistory(String eventName, boolean isVIP, int numberOfTickets, double totalCost) {
        this.eventName = eventName;
        this.isVIP = isVIP;
        this.numberOfTickets = numberOfTickets;
        this.totalCost = totalCost;
    }



    @Override
    public String toString() {
        return "Event Name: " + eventName + "\n" +
               "Ticket Type: " + (isVIP ? "VIP" : "General") + "\n" +
               "Number of Tickets: " + numberOfTickets + "\n" +
               "Total Cost: " + totalCost + "\n";
    }
}

