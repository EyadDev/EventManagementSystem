package Core;

import java.util.Date;

import Screens.AdminScreen;
import Screens.LoginRegisterScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        //These are dummy accounts to test login
        new Admin("admin", "1", new Date(), "CEO", "7AM-7PM");
        new Organizer("org", "1");
        new Attendee("att", "1", new Date(), "Berlin, Germany", Gender.Male);

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.minWidthProperty().set(1024);
        stage.minHeightProperty().set(576);
        stage.setTitle("CSE241'25 - Object Oriented Programing Project");
        stage.setScene(AdminScreen.AdminScreen());
        stage.show();
    }
}