import java.util.ArrayList;

public class Database {
    private static ArrayList<Wallet> wallets = new ArrayList<Wallet>();
    private static ArrayList<Person> people = new ArrayList<Person>();
    private static ArrayList<Event> events = new ArrayList<Event>();
    private static ArrayList<Category> categories = new ArrayList<Category>();

    public static void addWallet(Wallet wallet){
        wallets.add(wallet);
    }

    public static void removeWallet(Wallet wallet){
        wallets.remove(wallet);
    }

    public static Wallet getWallet(int index){
        return wallets.get(index);
    }

    public static int getWalletsSize(){
        return wallets.size();
    }

    public static void addPerson(Person person){
        people.add(person);
    }

    public static void removePerson(Person person){
        people.remove(person);
    }

    public static Person getPerson(int index){
        return people.get(index);
    }

    public static int getPeopleSize(){
        return people.size();
    }

    public static void addEvent(Event event){
        events.add(event);
    }

    public static void removeEvent(Event event){
        events.remove(event);
    }

    public static Event getEvent(int index){
        return events.get(index);
    }

    public static int getEventsSize(){
        return events.size();
    }

    public static void addCategory(Category category){
        categories.add(category);
    }

    public static void removeCategory(Category category){
        categories.remove(category);
    }

    public static Category getCategory(int index){
        return categories.get(index);
    }

    public static int getCategoriesSize(){
        return categories.size();
    }
}
