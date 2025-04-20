import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Event> events;

    Category(String name){
        this.name = name;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void removeEvent(int index){
        events.remove(index);
    }

    public void getEvent(int index){
        events.get(index);
    }

    @Override
    public String toString() {
        return name;
    }
}
