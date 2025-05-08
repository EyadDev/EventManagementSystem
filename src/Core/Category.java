package Core;

import java.util.ArrayList;
import java.util.Date;

public class Category {
    private String name;
    private ArrayList<Event> events = new ArrayList<>();

    public Category(String name){
        this.name = name;
        Database.addCategory(this);
    }

    public static boolean isDuplicate(String name){
        for (int i = 0; i < Database.getCategoriesSize(); i++){
            if (Database.getCategory(i).toString().equals(name)){
                return true;
            }
        }

        return false;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public void getEvent(int index){
        events.get(index);
    }

    public int getEventsSize(){
        return events.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
