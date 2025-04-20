import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Person loggedInAccount = null;

        //These are dummy accounts to test login
        new Organizer("James", "12345678");
        new Admin("Rick", "12345678", new Date(), "CEO", "7AM-7PM");
        new Attendee("Charlie", "123", new Date(), "Berlin, Germany", Gender.Male);

        int decision = mainMenu();
        emptyConsole();

        switch(decision){
            case 0:
                loggedInAccount = login();
                break;
            case 1:
                loggedInAccount = register();
                break;
        }

        emptyConsole();
        if (loggedInAccount instanceof Organizer){
            System.out.println("You are an Organizer!");
        }
        else if(loggedInAccount instanceof Attendee){
            System.out.println("You are an Attendee!");
        }
        else{
            System.out.println("You are an Admin!");
        }
    }

    static int mainMenu(){
        boolean correctChoice;
        Scanner input = new Scanner(System.in);

        do{
            correctChoice = true;
            System.out.println("Event Management System\n");

            System.out.println("(1) Login");
            System.out.println("(2) Register");
            System.out.println("(3) Quit\n");

            System.out.println("Enter your choice: ");

            try{
                switch(input.nextInt()){
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        throw new InputMismatchException();
                }
            }
            catch(InputMismatchException e){
                emptyConsole();
                System.out.println("Invalid input! try again");
                input.nextLine();
                correctChoice = false;
            }
        }
        while(!correctChoice);

        return -1;
    }

    static Person login(){
        boolean noError;
        Scanner input = new Scanner(System.in);

        do{
            noError = true;
            System.out.println("Login page\n");

            System.out.println("Enter username: ");
            String user = input.nextLine();

            System.out.println("Enter password: ");
            String password = input.nextLine();

            for (int i = 0; i < Database.getPeopleSize(); i++){
                if (user.equals(Database.getPerson(i).getUsername()) && password.equals(Database.getPerson(i).getPassword())){
                    return Database.getPerson(i);
                }
                else if (user.equals(Database.getPerson(i).getUsername()) && !password.equals(Database.getPerson(i).getPassword())){
                    emptyConsole();
                    System.out.println("Incorrect password! try again");
                    noError = false;
                }
            }

            if (!noError){
                continue;
            }

            emptyConsole();
            System.out.println("Username does not exist! try again");
            noError = false;
        }
        while(!noError);

        return null;
    }

    static Person register(){
        return null;
    }

    private static void emptyConsole(){
        System.out.println("\n\n\n\n\n\n\n");
    }
}