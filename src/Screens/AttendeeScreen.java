package Screens;

import Core.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class AttendeeScreen {
    private static final Label attendeeLabel = new Label("Attendee interface");

    private static final Label walletBalance = new Label("Balance: ");
    private static final Button showEventsButton = new Button("Events");
    private static final Button showProfileButton = new Button("Profile");
    private static final Button logoutButton = new Button("Logout");

    private static final Label availableEventsLabel = new Label("Available Events");
    private static final Label attendedEventsLabel = new Label("Attended Events");
    private static final ArrayList<Button> eventsButtons = new ArrayList<>();

    private static final Label profileLabel = new Label("Profile");
    private static final Button profileButton = new Button();

    public static Scene AttendeeScreen(Attendee attendee) {

        VBox buttonStack = new VBox();
        buttonStack.setSpacing(10);
        buttonStack.getChildren().add(walletBalance);
        walletBalance.setText("Balance: " + attendee.getWallet().getBalance() + "$");
        buttonStack.getChildren().add(showEventsButton);
        buttonStack.getChildren().add(showProfileButton);
        buttonStack.getChildren().add(logoutButton);

        // Create a GridPane
        var borderPane = new BorderPane();
        borderPane.setTop(ScreensHelper.CenterNode(attendeeLabel));
        borderPane.setLeft(buttonStack);

        ScrollPane sp1 = new ScrollPane();
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        showEventsButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateEventsList(attendee));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showProfileButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            borderPane.setCenter(createAttendeeProfile(attendee));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        logoutButton.setOnAction(e -> {
            Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
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
        attendeeLabel.setFont(new Font(fontSize));
        walletBalance.setFont(new Font(fontSize));
        showEventsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showProfileButton.setStyle("-fx-font-size: " + fontSize + "px;");
        availableEventsLabel.setFont(new Font(fontSize));
        attendedEventsLabel.setFont(new Font(fontSize));
        profileLabel.setFont(new Font(fontSize));
        profileButton.setStyle("-fx-font-size: " + fontSize + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSize + "px;");

        for(int i = 0; i < eventsButtons.size(); i++){
            eventsButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }
    }

    private static VBox CreateEventsList(Attendee attendee){
        FlowPane availableEventsList = new FlowPane();
        availableEventsList.setVgap(10);
        availableEventsList.setHgap(10);
        availableEventsList.setOrientation(Orientation.VERTICAL);

        FlowPane attendedEventsList = new FlowPane();
        attendedEventsList.setVgap(10);
        attendedEventsList.setHgap(10);
        attendedEventsList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.instance.getEventsSize(); i++){
            Event event = Database.instance.getEvent(i);
            boolean alreadyAttends = false;

            for(int j = 0; j < event.getNumberOfAttendees(); j++){
                if (event.getAttendee(j).equals(attendee)){
                    alreadyAttends = true;
                    break;
                }
            }

            if (!alreadyAttends){

                HBox box =  new HBox();

                box.setSpacing(10);

                Button button = new Button(event.getName() + " | on " + event.getDate() + " | starts at " + event.getStartTime() + " | ends at " + event.getEndTime() + " | Category: " + event.getCategory());
                box.getChildren().add(button);
                eventsButtons.add(button);

                Button buyButton = new Button("Buy: $" + event.getPrice());
                box.getChildren().add(buyButton);
                eventsButtons.add(buyButton);

                buyButton.setOnAction(e -> {
                    if (attendee.getWallet().getBalance() >= event.getPrice()){
                        attendee.getWallet().updateBalance(-event.getPrice());
                        event.getOrganizer().getWallet().updateBalance(event.getPrice());
                        walletBalance.setText("Balance: $" + attendee.getWallet().getBalance());
                        event.addAttendee(attendee);
                        showEventsButton.fire();
                    }
                    else{
                        ScreensHelper.ShowAlert("Insufficient balance for payment of ticket", Alert.AlertType.ERROR);
                    }
                });

                availableEventsList.getChildren().add(box);
            }
            else{
                Button button = new Button(event.getName() + " | on " + event.getDate() + " | starts at " + event.getStartTime() + " | ends at " + event.getEndTime() + " | Category: " + event.getCategory());
                eventsButtons.add(button);

                attendedEventsList.getChildren().add(button);
            }
        }

        VBox eventsBox = new VBox();
        eventsBox.setSpacing(10);
        eventsBox.setPadding(new Insets(0, 0, 0,20));
        eventsBox.getChildren().add(availableEventsLabel);
        eventsBox.getChildren().add(availableEventsList);
        eventsBox.getChildren().add(attendedEventsLabel);
        eventsBox.getChildren().add(attendedEventsList);

        return eventsBox;
    }

    public static VBox createAttendeeProfile(Attendee attendee){
        VBox box = new VBox();
        box.setPadding(new Insets(0, 0, 0,20));

        box.getChildren().add(profileLabel);
        profileButton.setText("Username: " + attendee.getUsername() +
                "\nBirthday: " + attendee.getDateOfBirth() +
                "\nAddress: " + attendee.getAddress() +
                "\nGender: " + attendee.getGender() +
                "\nInterests: " + attendee.getInterests());

        box.getChildren().add(profileButton);
        return box;
    }
}
