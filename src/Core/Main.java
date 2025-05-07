package Core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import Screens.AdminScreen;
import Screens.LoginRegisterScreen;
import Screens.RegisterType;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        //These are dummy accounts to test login
        new Admin("admin", "1", LocalDate.of(1970, 1,1), "CEO", "7AM-7PM");
        new Organizer("org", "1");
        new Attendee("att", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male);
        new Attendee("atta", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male);
        new Attendee("at", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male);
        new Attendee("a", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male);
        new Attendee("b", "1", LocalDate.of(1970, 1,1), "Berlin, Germany", Gender.Male);
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