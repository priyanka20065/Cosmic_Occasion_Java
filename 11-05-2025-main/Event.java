public class Event {
    private int eventId;
    private String eventname;
    private String date;
    private String venue;

    public Event(int eventId, String eventname, String date, String venue) {
        this.eventId = eventId;
        this.eventname = eventname;
        this.date = date;
        this.venue = venue;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return eventname;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Event Name: " + eventname + ", Date: " + date + ", Venue: " + venue;
    }
}
