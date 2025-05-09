package Core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class Database {
    public static Database instance = new Database();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Wallet> wallets = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public Database() {
        // TODO: Load from file
    }

    private void SaveDataToFile() {
        // TODO
    }

    public void addWallet(Wallet wallet) {
        wallets.add(wallet);
        SaveDataToFile();
    }

    public void removeWallet(Wallet wallet) {
        wallets.remove(wallet);
        SaveDataToFile();
    }

    public Wallet getWallet(int index) { return wallets.get(index); }

    public int getWalletsSize() { return wallets.size(); }

    public void addUser(User user) {
        users.add(user);
        sortLists();
        SaveDataToFile();
    }

    public void removeUser(User user) {
        users.remove(user);
        sortLists();
        SaveDataToFile();
    }

    public User getPerson(int index) { return users.get(index); }

    public int getIndexOfUser(String username) {
        for (var i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                return i;
            }
        }

        return -1;
    }

    public int getPeopleSize(){
        return users.size();
    }

    public void addEvent(Event event){
        events.add(event);
        sortLists();
        SaveDataToFile();
    }

    public void removeEvent(Event event){
        events.remove(event);
        sortLists();
        SaveDataToFile();
    }

    public Event getEvent(int index){
        return events.get(index);
    }

    public int getEventsSize(){
        return events.size();
    }

    public void addCategory(Category category){
        categories.add(category);
        SaveDataToFile();
    }

    public void removeCategory(Category category){
        categories.remove(category);
        SaveDataToFile();
    }

    public Category getCategory(int index){
        return categories.get(index);
    }

    public int getCategoriesSize(){
        return categories.size();
    }

    public String[] getCategoryNames(){
        var names = new String[categories.size()];

        for(int i = 0; i < categories.size(); i++){
            names[i] = categories.get(i).getName();
        }

        return names;
    }

    public void addRoom(Room room){
        rooms.add(room);
        sortLists();
        SaveDataToFile();
    }

    public void removeRoom(Room room){
        rooms.remove(room);
        sortLists();
        SaveDataToFile();
    }

    public Room getRoom(int index){
        return rooms.get(index);
    }

    public String[] getRoomNames(){
        var names = new String[rooms.size()];

        for(int i = 0; i < rooms.size(); i++){
            names[i] = rooms.get(i).getRoomName();
        }

        return names;
    }

    public int getRoomsSize(){
        return rooms.size();
    }

    private void sortLists(){
        users.sort(Comparator.naturalOrder());
        rooms.sort(Comparator.reverseOrder());
        events.sort(Comparator.naturalOrder());
    }
}