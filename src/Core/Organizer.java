package Core;

import java.util.ArrayList;

public class Organizer extends User {
    private ArrayList<Event> organizedEvents;

    Organizer(String username, String password){
        super(username, password, UserType.Organizer);
    }

    public void addEvent(Event event){
        organizedEvents.add(event);
    }

    public void removeEvent(Event event){
        organizedEvents.remove(event);
    }

    public void getEvent(int index){
        organizedEvents.get(index);
    }
}
