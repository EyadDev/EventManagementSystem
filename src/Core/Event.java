package Core;

import java.util.ArrayList;
import java.util.Date;

class Event implements Comparable<Event> {
    private String name;
    private Date startTime;
    private Date endTime;
    private Category category;
    private ArrayList<Attendee> attendees;
    private Organizer organizer;
    private double price;

    public Event(String name, Date startTime, Date endTime, Category category, Organizer organizer, double price) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;

        this.category = category;
        category.addEvent(this);

        this.attendees = new ArrayList<>();
        this.organizer = organizer;
        this.price = price;

        Database.addEvent(this);
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Category getCategory() {
        return category;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public double getPrice() {
        return price;
    }

    public void updateTime(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateCategory(Category category) {
        this.category.removeEvent(this);
        this.category = category;
        category.addEvent(this);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public void removeAttendee(Attendee attendee) {
        attendees.remove(attendee);
    }

    public Attendee getAttendee(int index) {
        return attendees.get(index);
    }

    public int getNumberOfAttendees(){
        return attendees.size();
    }

    public int compareTo(Event otherEvent) {
        // Compare events based on their start time
        return this.name.compareTo(otherEvent.getName());
    }
}