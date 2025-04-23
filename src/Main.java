import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(args);
        User loggedInAccount = null;

        //These are dummy accounts to test login
        new Organizer("James", "12345678");
        new Admin("Rick", "12345678", new Date(), "CEO", "7AM-7PM");
        new Attendee("Charlie", "123", new Date(), "Berlin, Germany", Gender.Male);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.minWidthProperty().set(1024);
        stage.minHeightProperty().set(576);
        stage.setTitle("CSE241'23 - Object Oriented Programing Project");
        stage.setScene(Screens.LoginRegisterScreen(true));
        stage.show();
    }
}