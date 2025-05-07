package Core;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Wallet> wallets = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();

    public static void addWallet(Wallet wallet) { wallets.add(wallet); }

    public static void removeWallet(Wallet wallet) { wallets.remove(wallet); }

    public static Wallet getWallet(int index) { return wallets.get(index); }

    public static int getWalletsSize() { return wallets.size(); }

    public static void addUser(User user) { users.add(user); }

    public static void removePerson(User user) { users.remove(user); }

    public static User getPerson(int index) { return users.get(index); }

    public static int getIndexOfUser(String username) {
        for (var i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                return i;
            }
        }

        return -1;
    }

    public static int getPeopleSize(){
        return users.size();
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
