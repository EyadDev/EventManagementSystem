package Core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import Screens.AdminScreen;
import Screens.LoginRegisterScreen;
import Screens.RegisterType;
import com.sun.javafx.event.EventHandlerManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        // These are dummy accounts to test login
        new Admin("admin", "1", LocalDate.of(1970, 1,1), "CEO", "7AM-7PM");
        var organizer = new Organizer("org", "1");

        new Attendee("att1", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male, "Fun");
        new Attendee("att2", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male  , "Fun");

        Room room1 = new Room("911A", LocalTime.of(8, 0,0), LocalTime.of(20, 0 ,0));
        Room room2 = new Room("924A", LocalTime.of(8, 0,0), LocalTime.of(19, 30 ,0));

        new Room("505B", LocalTime.of(8, 0,0), LocalTime.of(18, 0 ,0));
        new Room("Hall 1", LocalTime.of(8, 0,0), LocalTime.of(10, 45 ,0));

        Category category = new Category("Party");

        room1.addEvent(new Event("Wedding", LocalDate.of(2025, 5, 10), LocalTime.of(8, 0), LocalTime.of(10, 0), category, organizer, 100));
        room1.addEvent(new Event("Birthday", LocalDate.of(2025, 5, 10), LocalTime.of(11, 0), LocalTime.of(13, 0), category, organizer, 100));
        room2.addEvent(new Event("Meeting", LocalDate.of(2025, 6, 10), LocalTime.of(8, 0), LocalTime.of(15, 0), category, organizer, 100));

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.minWidthProperty().set(1024);
        stage.minHeightProperty().set(576);
        stage.setTitle("CSE241'25 - Object Oriented Programing Project");
        stage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
        stage.show();
    }
}