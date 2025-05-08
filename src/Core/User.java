package Core;

import Core.Database;
import Core.HashHelper;
import Core.UserType;
import Core.Wallet;

public abstract class User implements Comparable<User> {

    private String username;
    private String passwordHash;
    private Wallet wallet;
    private UserType userType;

    protected User(String username, String password, UserType userType) {
        this.username = username;
        this.passwordHash = HashHelper.hashPassword(password);
        this.userType = userType;

        wallet = new Wallet(this);
        Database.addUser(this);
    }

    public static boolean isValidUser(String username){
        for (int i = 0; i < Database.getPeopleSize(); i++){
            if (Database.getPerson(i).getUsername().equals(username)){
                System.out.println("Account already exists with the same username");
                return false;
            }
        }

        if (username.length() > 20){
            System.out.println("username cannot be longer than 20 characters");
            return false;
        }

        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password){
        return HashHelper.checkPassword(password, passwordHash);
    }

    public UserType getUserType() { return userType; }

    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public int compareTo(User o) {
        return getUsername().compareTo(o.getUsername());
    }
}