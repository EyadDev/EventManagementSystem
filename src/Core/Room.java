package Core;

import Screens.ScreensHelper;
import javafx.scene.control.Alert;

import java.time.LocalTime;
import java.util.ArrayList;

public class Room implements Comparable<Room>{
    private String roomName;
    private ArrayList<Event> events = new ArrayList<>();
    private LocalTime openingTime;
    private LocalTime closingTime;

    public Room(String roomName, LocalTime openingTime, LocalTime closingTime) {
        this.roomName = roomName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

        Database.instance.addRoom(this);
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    @Override
    public int compareTo(Room o) {
        // Compare based on number of events
        return Integer.compare(this.getEventsSize(), o.getEventsSize());
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

    public int getEventsSize(){
        return events.size();
    }

    public boolean checkForConflicts(Event eventToAdd) {
        if (eventToAdd.getStartTime().isBefore(openingTime) || eventToAdd.getEndTime().isAfter(closingTime)){
            ScreensHelper.ShowAlert("Event has conflict with room times.", Alert.AlertType.ERROR);
            return true;
        }

        for (Event addedEvent : events) {
            boolean sameDate = eventToAdd.getDate().equals(addedEvent.getDate());
            boolean eventAfterOther = eventToAdd.getStartTime().isAfter(addedEvent.getEndTime()) && eventToAdd.getEndTime().isAfter(addedEvent.getEndTime());
            boolean eventBeforeOther = eventToAdd.getStartTime().isBefore(addedEvent.getStartTime()) && eventToAdd.getEndTime().isBefore(addedEvent.getStartTime());

            if (sameDate && !eventAfterOther && !eventBeforeOther) {
                ScreensHelper.ShowAlert("Event is in conflict with another event.", Alert.AlertType.ERROR);
                return true;
            }
        }

        return false;
    }
}
