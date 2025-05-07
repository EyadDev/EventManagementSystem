package Screens;

import Core.Attendee;
import Core.Database;
import Core.Main;
import Core.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AdminScreen {
    private static Label adminLabel = new Label("Admin interface");
    private static TextField roomNameField = new TextField();

    private static Button addRoomButton = new Button("Add Room");
    private static Button addCategoryButton = new Button("Add Category");
    private static Button showAttendeesButton = new Button("Attendees");
    private static Button showEventsButton = new Button("Events");
    private static Button showRoomsButton = new Button("Rooms");
    private static Button showCategoriesButton = new Button("Categories");

    private static Label attendeesLabel = new Label("Attendees");

    public static Scene AdminScreen() {

        VBox buttonStack = new VBox();
        buttonStack.setSpacing(10);
        buttonStack.getChildren().add(addRoomButton);
        buttonStack.getChildren().add(addCategoryButton);
        buttonStack.getChildren().add(showAttendeesButton);
        buttonStack.getChildren().add(showEventsButton);
        buttonStack.getChildren().add(showRoomsButton);
        buttonStack.getChildren().add(showCategoriesButton);

        // Create a GridPane
        var borderPane = new BorderPane();
        borderPane.setTop(Helper.CenterNode(adminLabel));
        borderPane.setLeft(buttonStack);

        addRoomButton.setOnAction(e -> {
            System.out.println(0);
        });

        // Bind font size to the width of the stage
        Main.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            UpdateSizes(newVal.doubleValue());
        });

        UpdateSizes(Main.primaryStage.widthProperty().doubleValue());

        return new Scene((borderPane), Main.primaryStage.widthProperty().doubleValue(), Main.primaryStage.heightProperty().doubleValue());
    }

    private static void UpdateSizes(double newVal){
        double fontSize = Math.max(12, newVal / 50);
        adminLabel.setFont(new Font(fontSize));
        addRoomButton.setStyle("-fx-font-size: " + fontSize + "px;");
        addCategoryButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showAttendeesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showEventsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showRoomsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showCategoriesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        attendeesLabel.setFont(new Font(fontSize));
    }

    //create a list of attendees that includes their username and wallet balance
    private static GridPane CreateAttendeesList(double newVal){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < Database.getPeopleSize(); i++){
            User user = Database.getPerson(i);

            if(user instanceof Attendee){
                Label attendeeLabel = new Label(user.getUsername());
                grid.add(attendeeLabel, 0, i);
            }
        }

        return grid;
    }
}
