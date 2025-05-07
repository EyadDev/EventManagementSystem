package Core;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;

public class Room implements Comparable<Room>{
    private ArrayList<Event> events = new ArrayList<>();
    private LocalTime openingTime;
    private LocalTime closingTime;

    public Room(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public int compareTo(Room o) {
        // Compare based on number of events
        return Integer.compare(this.events.size(), o.events.size());
    }

    // Returns true if the event was added successfully
    public boolean addEvent(Event e){
        if (checkForConflicts(e)) return false;
        events.add(e);
        return true;
    }

    public void removeEvent(Event e){
        events.remove(e);
    }

    public Event getEvent(int index){
        return events.get(index);
    }

    public boolean checkForConflicts(Event eventToAdd) {
        if (eventToAdd.getStartTime().isBefore(openingTime) || eventToAdd.getEndTime().isAfter(closingTime)){
            System.out.println("Event has conflict with room times!");
            return true;
        }

        if (events.isEmpty()){
            System.out.println("Event has no conflicts!");
            return false;
        }

        for (Event addedEvent : events) {
            boolean sameDate = eventToAdd.getDate().equals(addedEvent.getDate());
            boolean eventAfterOther = eventToAdd.getStartTime().isAfter(addedEvent.getEndTime()) && eventToAdd.getEndTime().isAfter(addedEvent.getEndTime());
            boolean eventBeforeOther = eventToAdd.getStartTime().isBefore(addedEvent.getStartTime()) && eventToAdd.getEndTime().isBefore(addedEvent.getStartTime());

            if (sameDate && !eventAfterOther && !eventBeforeOther) {
                System.out.println("Event is in conflict with another event");
                return true;
            }
        }

        System.out.println("Event has no conflicts!");
        return false;
    }
}
