package Screens;

import Core.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Date;

public class LoginRegisterScreen {
    private static Label loginLabel = new Label();
    private static Label usernameLabel = new Label("Username");
    private static TextField usernameField = new TextField();
    private static Label passwordLabel = new Label("Password");
    private static TextField passwordField = new TextField();
    private static Label addressLabel = new Label("Address");
    private static TextField addressField = new TextField();
    private static Button button1 = new Button();
    private static Button button2 = new Button();

    public static Scene LoginRegisterScreen(boolean login) {
        // Create a GridPane
        var gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create labels and text fields
        loginLabel = new Label(login ? "Login" : "Register");

        var loginRegisterHBox = new HBox();
        loginRegisterHBox.setSpacing(10);
        button1 = new Button(login ? "Login" : "Register");
        button2 = new Button("Go to " + (login ? "register" : "login") + " page");
        loginRegisterHBox.getChildren().add(button1);
        loginRegisterHBox.getChildren().add(button2);

        button2.setOnAction((ActionEvent e) -> {
            Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(!login));
        });

        button1.setOnAction((ActionEvent e) -> {
            if (login) {
                var userIndex = Database.getIndexOfUser(usernameField.getText());
                if (userIndex == -1) {
                    Helper.ShowAlert("Username doesn't exist", Alert.AlertType.ERROR);
                    return;
                }

                if (Database.getPerson(userIndex).checkPassword(passwordField.getText())) {
                    switch (Database.getPerson(userIndex).getUserType()){
                        case UserType.Admin -> Main.primaryStage.setScene(AdminScreen.AdminScreen());
                        case UserType.Organizer -> Helper.ShowAlert("You are already an organizer", Alert.AlertType.ERROR);
                        case UserType.Attendee -> Helper.ShowAlert("You are already an attendee", Alert.AlertType.ERROR);
                    }
                }

                else {
                    Helper.ShowAlert("Incorrect password", Alert.AlertType.ERROR);
                }
            }

            else{
                if (Database.getIndexOfUser(usernameField.getText()) != -1) {
                    Helper.ShowAlert("User already exists", Alert.AlertType.ERROR);
                    return;
                }

                new Attendee(usernameField.getText(), passwordField.getText(), new Date(), addressField.getText(), Gender.Male);
                Helper.ShowAlert("Created new attendee with username: " + usernameField.getText(), Alert.AlertType.CONFIRMATION);
            }
        });

        // Add labels and fields to the GridPane
        gridPane.add(loginLabel, 1, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);

        if (!login){
            gridPane.add(addressLabel, 0, 3);
            gridPane.add(addressField, 1, 3);
        }

        gridPane.add(loginRegisterHBox, 1, login ? 3 : 4);

        // Bind font size to the width of the stage
        Main.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            UpdateSizes(newVal.doubleValue());
        });

        UpdateSizes(Main.primaryStage.widthProperty().doubleValue());

        // Create a Scene and set it on the Stage
        return new Scene(Helper.CenterNode(gridPane), Main.primaryStage.widthProperty().doubleValue(), Main.primaryStage.heightProperty().doubleValue());
    }

    private static void UpdateSizes(double newVal){
        double fontSize = Math.max(12, newVal / 50);
        loginLabel.setFont(new Font(fontSize));
        usernameLabel.setFont(new Font(fontSize));
        passwordLabel.setFont(new Font(fontSize));
        usernameField.setStyle("-fx-font-size: " + fontSize + "px;");
        passwordField.setStyle("-fx-font-size: " + fontSize + "px;");
        button1.setStyle("-fx-font-size: " + fontSize + "px;");
        button2.setStyle("-fx-font-size: " + fontSize + "px;");
        addressLabel.setFont(new Font(fontSize));
        addressField.setStyle("-fx-font-size: " + fontSize + "px;");
        button1.setStyle("-fx-font-size: " + fontSize + "px;");
        button2.setStyle("-fx-font-size: " + fontSize + "px;");
    }
}
