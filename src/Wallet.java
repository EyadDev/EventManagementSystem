public class Wallet {
    private double balance;
    private Person owner;

    public static final double DEFAULT_BALANCE = 0;

    Wallet(double balance, Person owner){
        this.balance = balance;
        this.owner = owner;

        Database.addWallet(this);
    }

    Wallet(Person owner){
        this(DEFAULT_BALANCE, owner);
    }

    public double getBalance() {
        return balance;
    }

    public Person getOwner() {
        return owner;
    }

    public void updateBalance(double amount){
        balance += amount;
    }
}
