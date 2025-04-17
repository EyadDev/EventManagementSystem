import java.util.ArrayList;

public class Database {
    private static ArrayList<Wallet> wallets = new ArrayList<Wallet>();
    private static ArrayList<Person> people = new ArrayList<Person>();

    public static void addWallet(Wallet wallet){
        wallets.add(wallet);
    }

    public static void removeWallet(Wallet wallet){
        wallets.remove(wallet);
    }

    public static Wallet getWallet(int index){
        return wallets.get(index);
    }

    public static int getWalletSize(){
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
}
