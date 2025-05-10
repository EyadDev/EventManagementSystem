package Screens;

import Core.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LoginRegisterScreen {
    private static final GridPane gridPane = new GridPane();
    private static Label loginLabel = new Label();
    private static final Label usernameLabel = new Label("Username");
    private static final TextField usernameField = new TextField();

    private static final Label passwordLabel = new Label("Password");
    private static final TextField passwordField = new TextField();

    private static final Label dateLabel = new Label("Birthday");
    private static final DatePicker datePicker = new DatePicker();

    private static final Label addressLabel = new Label("Address");
    private static final TextField addressField = new TextField();

    private static final Label accountTypeLabel = new Label("Account type");
    private static final ToggleGroup accountToggleGroup = new ToggleGroup();
    private static final RadioButton attendeeButton = new RadioButton("Attendee");
    private static final RadioButton organizerButton = new RadioButton("Organizer");

    private static final Label genderLabel = new Label("Gender");
    private static final ToggleGroup genderToggleGroup = new ToggleGroup();
    private static final RadioButton maleButton = new RadioButton("Male");
    private static final RadioButton femaleButton = new RadioButton("Female");

    private static final Label interestsLabel  = new Label("Interests");
    private static final TextField interestsField = new TextField();

    private static Button button1 = new Button();
    private static Button button2 = new Button();

    public static Scene LoginRegisterScreen(boolean login, RegisterType registerType) {
        // Create a GridPane
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //Create radial buttons to select between organiser and attendee registration
        var radialButtonsHBox = new HBox();
        radialButtonsHBox.setSpacing(20);
        radialButtonsHBox.getChildren().add(accountTypeLabel);
        radialButtonsHBox.getChildren().add(attendeeButton);
        radialButtonsHBox.getChildren().add(organizerButton);

        attendeeButton.setToggleGroup(accountToggleGroup);
        attendeeButton.setOnAction( e -> {
            if (attendeeButton.isSelected()){
                SetAttendeeRegister();
            }
        });

        organizerButton.setToggleGroup(accountToggleGroup);
        organizerButton.setOnAction( e -> {
            if (organizerButton.isSelected()){
                SetOrganizerRegister();
            }
        });

        if (registerType == RegisterType.attendee){
            accountToggleGroup.selectToggle(attendeeButton);
        }
        else if (registerType == RegisterType.organiser){
            accountToggleGroup.selectToggle(organizerButton);
        }

        // Create labels and text fields
        loginLabel = new Label(login ? "Login" : "Register");

        //Create radial buttons to select Gender
        var genderHBox = new HBox();
        genderHBox.setSpacing(20);
        genderHBox.getChildren().add(maleButton);
        genderHBox.getChildren().add(femaleButton);

        maleButton.setToggleGroup(genderToggleGroup);
        femaleButton.setToggleGroup(genderToggleGroup);

        genderToggleGroup.selectToggle(maleButton);

        var loginRegisterHBox = new HBox();
        loginRegisterHBox.setSpacing(10);
        button1 = new Button(login ? "Login" : "Register");
        button2 = new Button("Go to " + (login ? "register" : "login") + " page");
        loginRegisterHBox.getChildren().add(button1);
        loginRegisterHBox.getChildren().add(button2);

        button2.setOnAction((ActionEvent e) -> {
            if (login){
                Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(false, RegisterType.attendee));
            }
            else{
                Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
            }
        });

        button1.setOnAction((ActionEvent e) -> {
            if (login) {
                var userIndex = Database.instance.getIndexOfUser(usernameField.getText());
                if (userIndex == -1) {
                    ScreensHelper.ShowAlert("Username doesn't exist", Alert.AlertType.ERROR);
                    return;
                }

                if (Database.instance.getPerson(userIndex).checkPassword(passwordField.getText())) {
                    switch (Database.instance.getPerson(userIndex).getUserType()){
                        case UserType.Admin -> Main.primaryStage.setScene(AdminScreen.AdminScreen());
                        case UserType.Organizer -> Main.primaryStage.setScene(OrganizerScreen.OrganizerScreen((Organizer) Database.instance.getPerson(userIndex)));
                        case UserType.Attendee -> Main.primaryStage.setScene(AttendeeScreen.AttendeeScreen((Attendee)Database.instance.getPerson(userIndex)));
                    }
                }

                else {
                    ScreensHelper.ShowAlert("Incorrect password", Alert.AlertType.ERROR);
                }
            }

            else{
                if (Database.instance.getIndexOfUser(usernameField.getText()) != -1) {
                    ScreensHelper.ShowAlert("User already exists", Alert.AlertType.ERROR);
                    return;
                }

                if (registerType == RegisterType.attendee){
                    Gender gender = Gender.Male;

                    if (femaleButton.isSelected()){
                        gender = Gender.Female;
                    }

                    new Attendee(usernameField.getText(), passwordField.getText(), datePicker.getValue(), addressField.getText(), gender, interestsField.getText());
                    ScreensHelper.ShowAlert("Created new attendee with username: " + usernameField.getText(), Alert.AlertType.CONFIRMATION);
                }
                else{
                    new Organizer(usernameField.getText(), passwordField.getText());
                    ScreensHelper.ShowAlert("Created new organizer with username: " + usernameField.getText(), Alert.AlertType.CONFIRMATION);
                }

                usernameField.clear();
                passwordField.clear();
                datePicker.cancelEdit();
                addressField.clear();

                Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
            }
        });

        // Add labels and fields to the GridPane

        if (login){
            gridPane.getChildren().clear();
            gridPane.add(loginLabel, 0, 0);
            gridPane.add(usernameLabel, 0, 1);
            gridPane.add(usernameField, 1, 1);
            gridPane.add(passwordLabel, 0, 2);
            gridPane.add(passwordField, 1, 2);
            gridPane.add(loginRegisterHBox, 1, 3);
        }
        else{
            if (registerType == RegisterType.attendee){
                gridPane.getChildren().clear();
                gridPane.add(radialButtonsHBox, 1, 0);
                gridPane.add(loginLabel, 1, 2);
                gridPane.add(usernameLabel, 0, 3);
                gridPane.add(usernameField, 1, 3);
                gridPane.add(passwordLabel, 0, 4);
                gridPane.add(passwordField, 1, 4);
                gridPane.add(dateLabel, 0, 5);
                gridPane.add(datePicker, 1, 5);
                gridPane.add(addressLabel, 0, 6);
                gridPane.add(addressField, 1, 6);
                gridPane.add(genderLabel, 0, 7);
                gridPane.add(genderHBox, 1 , 7);
                gridPane.add(interestsLabel, 0, 8);
                gridPane.add(interestsField, 1, 8);
                gridPane.add(loginRegisterHBox, 1, 9);
            }
            else if(registerType == RegisterType.organiser){
                gridPane.getChildren().clear();
                gridPane.add(radialButtonsHBox, 1, 0);
                gridPane.add(loginLabel, 1, 2);
                gridPane.add(usernameLabel, 0, 3);
                gridPane.add(usernameField, 1, 3);
                gridPane.add(passwordLabel, 0, 4);
                gridPane.add(passwordField, 1, 4);
                gridPane.add(loginRegisterHBox, 1, 5);

            }
        }

        // Bind font size to the width of the stage
        Main.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            UpdateSizes(newVal.doubleValue());
        });

        UpdateSizes(Main.primaryStage.widthProperty().doubleValue());

        // Create a Scene and set it on the Stage
        return new Scene(ScreensHelper.CenterNode(gridPane), Main.primaryStage.widthProperty().doubleValue(), Main.primaryStage.heightProperty().doubleValue());
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
        dateLabel.setFont(new Font(fontSize));
        datePicker.setStyle("-fx-font-size: " + fontSize + "px;");
        addressLabel.setFont(new Font(fontSize));
        addressField.setStyle("-fx-font-size: " + fontSize + "px;");
        button1.setStyle("-fx-font-size: " + fontSize + "px;");
        button2.setStyle("-fx-font-size: " + fontSize + "px;");
        accountTypeLabel.setFont(new Font(fontSize));
        attendeeButton.setStyle("-fx-font-size: " + fontSize + "px;");
        organizerButton.setStyle("-fx-font-size: " + fontSize + "px;");
        genderLabel.setFont(new Font(fontSize));
        maleButton.setStyle("-fx-font-size: " + fontSize + "px;");
        femaleButton.setStyle("-fx-font-size: " + fontSize + "px;");
        interestsLabel.setFont(new Font(fontSize));
        interestsField.setStyle("-fx-font-size: " + fontSize + "px;");
    }

    private static void SetAttendeeRegister(){
        Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(false, RegisterType.attendee));
    }

    private static void SetOrganizerRegister(){
        Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(false, RegisterType.organiser));
    }
}
