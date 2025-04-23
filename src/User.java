public abstract class User {

    private String username;
    private String passwordHash;

    User(String username, String password){
        this.username = username;
        this.passwordHash = HashHelper.hashPassword(password);

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
}
