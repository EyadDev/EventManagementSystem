public abstract class Person {

    private String username;
    private String password;
    private Wallet wallet;

    Person(){
        this("default", "12345678");
    }

    Person(String username, String password){
        this.username = username;
        this.password = password;

        wallet = new Wallet(this);
        Database.addPerson(this);
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

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
