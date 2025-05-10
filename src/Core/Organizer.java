package Core;

import java.util.ArrayList;

public class Organizer extends User {
    private ArrayList<Event> organizedEvents = new ArrayList<>();

    public Organizer(String username, String password){
        super(username, password, UserType.Organizer);
    }

    public void addEvent(Event event){
        organizedEvents.add(event);
    }

    public void removeEvent(Event event){
        organizedEvents.remove(event);
    }

    public Event getEvent(int index){
        return organizedEvents.get(index);
    }

    public int getEventsSize(){
        return organizedEvents.size();
    }
}
