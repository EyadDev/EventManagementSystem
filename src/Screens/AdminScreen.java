package Screens;

import Core.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AdminScreen {
    private static Label adminLabel = new Label("Admin interface");
    private static TextField roomNameField = new TextField();
    private static Button addRoomButton = new Button("Add Room");

    public static Scene AdminScreen() {
        // Create a GridPane
        var borderPane = new BorderPane();
        borderPane.setTop(Helper.CenterNode(adminLabel));
        borderPane.setCenter(addRoomButton);

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
//        passwordField.setStyle("-fx-font-size: " + fontSize + "px;");
    }
}
