public class Wallet {
    private double balance;
    private Attendee owner;

    public static final double DEFAULT_BALANCE = 0;

    Wallet(double balance, Attendee owner){
        this.balance = balance;
        this.owner = owner;

        Database.addWallet(this);
    }

    Wallet(Attendee owner){
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
