package Core;

public class Wallet {
    private double balance;
    private User owner;

    public static final double DEFAULT_BALANCE = 200;

    Wallet(double balance, User owner){
        this.balance = balance;
        this.owner = owner;

        Database.instance.addWallet(this);
    }

    Wallet(User owner){
        this(DEFAULT_BALANCE, owner);
    }

    public double getBalance() {
        return balance;
    }

    public User getOwner() {
        return owner;
    }

    public void updateBalance(double amount){
        balance += amount;
    }
}
