public class Booking {
    private Client client;
    private Event event;

    public Booking(Client client, Event event) {
        this.client = client;
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Booking - Client ID: " + client.getClientId() + ", Event ID: " + event.getEventId() +
               ", Client Name: " + client.getName() + ", Event Name: " + event.getName();
    }
}
