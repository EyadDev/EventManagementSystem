package Core;

import java.util.ArrayList;

public class Room implements Comparable<Room>{
    private ArrayList<Event> events;

    public Room() {}

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
        for (Event addedEvent : events) {
            if ((eventToAdd.getStartTime().getTime() > addedEvent.getStartTime().getTime() && eventToAdd.getStartTime().getTime() < addedEvent.getEndTime().getTime()) ||
                (eventToAdd.getEndTime().getTime() > addedEvent.getStartTime().getTime() && eventToAdd.getEndTime().getTime() < addedEvent.getEndTime().getTime())
            ) return true;
        }

        return false;
    }
}
