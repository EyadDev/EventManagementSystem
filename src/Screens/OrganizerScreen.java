package Screens;

import Core.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrganizerScreen {
    private static final Label organizerLabel = new Label("Organizer interface");

    private static final Label walletBalance = new Label("Balance: ");
    private static final Button showAttendeesButton = new Button("Attendees");
    private static final Button showEventsButton = new Button("Events");
    private static final Button showRoomsButton = new Button("Rooms");
    private static final Button addEventButton = new Button("Add Event");
    private static final Button showCategoriesButton = new Button("Categories");
    private static final Button logoutButton = new Button("Logout");

    private static final Label attendeesLabel = new Label("Attendees");
    private static final ArrayList<Button> attendeeButtons = new ArrayList<>();

    private static final Label roomsLabel = new Label("Rooms");
    private static final ArrayList<Button> roomButtons = new ArrayList<>();

    private static final Label eventsLabel = new Label("Events");
    private static final ArrayList<Button> eventButtons = new ArrayList<>();

    private static final Label categoriesLabel = new Label("Categories");
    private static final ArrayList<Button> categoryButtons = new ArrayList<>();

    private static final Label newEventLabel = new Label("Create new Event");
    private static final ComboBox<String> roomToHostEventSelector = new ComboBox<>();
    private static final ComboBox<String> eventCategorySelector = new ComboBox<>();
    static final DatePicker eventDatePicker = new DatePicker(LocalDate.now());
    private static final Button createEventButton = new Button("Add Event");

    private static final Label eventLocationLabel = new Label("Event location");
    private static final Label categorySelectorLabel = new Label("Category");
    private static final Label priceLabel = new Label("Price");
    private static final TextField priceField = new TextField("100");
    private static final Label eventNameLabel = new Label("Event name");
    private static final TextField eventNameField = new TextField("");
    private static final TextField categoryNameField = new TextField("Default");
    private static final Button createCategoryButton = new Button("Add");

    public static Scene OrganizerScreen(Organizer organizer) {

        VBox buttonStack = new VBox();
        buttonStack.setSpacing(10);
        buttonStack.getChildren().add(walletBalance);
        walletBalance.setText("Balance: " + organizer.getWallet().getBalance() + "$");
        buttonStack.getChildren().add(showAttendeesButton);
        buttonStack.getChildren().add(showEventsButton);
        buttonStack.getChildren().add(showRoomsButton);
        buttonStack.getChildren().add(addEventButton);
        buttonStack.getChildren().add(showCategoriesButton);
        buttonStack.getChildren().add(logoutButton);

        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d+\\.?\\d*")) {
                priceField.setText(oldValue); // Revert to the old value if the new value is invalid
            }
        });

        // Create a GridPane
        var borderPane = new BorderPane();
        borderPane.setTop(ScreensHelper.CenterNode(organizerLabel));
        borderPane.setLeft(buttonStack);

        ScrollPane sp1 = new ScrollPane();
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        roomToHostEventSelector.getItems().clear();
        roomToHostEventSelector.getItems().addAll(Database.instance.getRoomNames());

        eventCategorySelector.getItems().clear();
        eventCategorySelector.getItems().addAll(Database.instance.getCategoryNames());


        showAttendeesButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateAttendeesList(organizer));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showRoomsButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(AdminScreen.CreateRoomsList(false, roomButtons, showRoomsButton));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
            AdminScreen.UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        addEventButton.setOnAction(e -> {
            borderPane.setCenter(CreateNewEventPanel());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showEventsButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(AdminScreen.CreateEventsList(eventButtons));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
            AdminScreen.UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showCategoriesButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(AdminScreen.CreateCategoriesList(false, categoryButtons, showCategoriesButton));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
            AdminScreen.UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        logoutButton.setOnAction(e -> {
            Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
        });

        createEventButton.setOnAction(e -> {
            LocalTime openingTime = LocalTime.of(AdminScreen.openingHourSpinner.getValue(), AdminScreen.openingMinuteSpinner.getValue());
            LocalTime closingTime = LocalTime.of(AdminScreen.closingHourSpinner.getValue(), AdminScreen.closingMinuteSpinner.getValue());

            if (eventNameField.getText().trim().isEmpty()) {
                ScreensHelper.ShowAlert("Event name cannot be empty or whitespace.", Alert.AlertType.ERROR);
                return;
            }

            if (openingTime.isBefore(closingTime)){
                for (var i = 0; i < Database.instance.getEventsSize(); i++){
                    if (Database.instance.getEvent(i).getName().equalsIgnoreCase(eventNameField.getText())){
                        ScreensHelper.ShowAlert("An event with the same name already exists", Alert.AlertType.ERROR);
                        return;
                    }
                }

                System.out.println(eventCategorySelector.getSelectionModel().getSelectedIndex());
                System.out.println(roomToHostEventSelector.getSelectionModel().getSelectedIndex());

                if (eventCategorySelector.getSelectionModel().getSelectedIndex() == -1) {
                    ScreensHelper.ShowAlert("Event category not selected.", Alert.AlertType.ERROR);
                    return;
                }

                if (roomToHostEventSelector.getSelectionModel().getSelectedIndex() == -1) {
                    ScreensHelper.ShowAlert("Room to host event not selected.", Alert.AlertType.ERROR);
                    return;
                }

                var eventToAdd = new Event(eventNameField.getText(), eventDatePicker.getValue(), openingTime, closingTime, Database.instance.getCategory(eventCategorySelector.getSelectionModel().getSelectedIndex()), organizer, Double.parseDouble(priceField.getText()));

                if (Database.instance.getRoom(roomToHostEventSelector.getSelectionModel().getSelectedIndex()).addEvent(eventToAdd)){
                    ScreensHelper.ShowAlert("Event added successfully.", Alert.AlertType.CONFIRMATION);
                }

                else{
                    eventToAdd.DeleteEvent();
                    ScreensHelper.ShowAlert("Event is in conflict with another event", Alert.AlertType.ERROR);
                }
            }
            else{
                ScreensHelper.ShowAlert("Closing time can not be the same or before opening time", Alert.AlertType.ERROR);
            }
        });

        createCategoryButton.setOnAction(e -> {
            String name = categoryNameField.getText();

            if (!Category.isDuplicate(name)){
                new Category(name);
                ScreensHelper.ShowAlert("Category succesfully created", Alert.AlertType.CONFIRMATION);
            }
            else{
                ScreensHelper.ShowAlert("This category already exists", Alert.AlertType.ERROR);
            }
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
        organizerLabel.setFont(new Font(fontSize));
        walletBalance.setStyle("-fx-font-size: " + fontSize + "px;");
        showAttendeesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showEventsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showRoomsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        addEventButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showCategoriesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSize + "px;");
        attendeesLabel.setFont(new Font(fontSize));
        roomsLabel.setFont(new Font(fontSize));
        eventsLabel.setFont(new Font(fontSize));
        categoriesLabel.setFont(new Font(fontSize));

        newEventLabel.setFont(new Font(fontSize));
        eventNameLabel.setFont(new Font(fontSize));
        createEventButton.setStyle("-fx-font-size: " + fontSize + "px;");

        eventLocationLabel.setFont(new Font(fontSize));
        categorySelectorLabel.setFont(new Font(fontSize));
        priceLabel.setFont(new Font(fontSize));
        categoryNameField.setStyle("-fx-font-size: " + fontSize + "px;");
        eventNameField.setStyle("-fx-font-size: " + fontSize + "px;");
        priceField.setStyle("-fx-font-size: " + fontSize + "px;");
        createCategoryButton.setStyle("-fx-font-size: " + fontSize + "px;");

        for(int i = 0; i < attendeeButtons.size(); i++){
            attendeeButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < roomButtons.size(); i++){
            roomButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < eventButtons.size(); i++){
            eventButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < categoryButtons.size(); i++){
            categoryButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }


        AdminScreen.openingTimeLabel.setFont(new Font(fontSize));
        AdminScreen.openingHourSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        AdminScreen.openingMinuteSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        AdminScreen.closingTimeLabel.setFont(new Font(fontSize));
        AdminScreen.closingHourSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        AdminScreen.closingMinuteSpinner.setStyle("-fx-font-size: " + fontSize + "px;");

        roomToHostEventSelector.setStyle("-fx-font-size: " + fontSize + "px;");
        eventCategorySelector.setStyle("-fx-font-size: " + fontSize + "px;");
        eventDatePicker.setStyle("-fx-font-size: " + fontSize + "px;");
    }

    //create a list of attendees that includes their username and wallet balance
    private static VBox CreateAttendeesList(Organizer organizer){
        FlowPane attendeesList = new FlowPane();
        attendeesList.setVgap(10);
        attendeesList.setHgap(10);
        attendeesList.setOrientation(Orientation.VERTICAL);

        ArrayList<Attendee> attendees = new ArrayList<>();

        for (int i = 0; i < organizer.getEventsSize(); i++){
            for (int j = 0; j < organizer.getEvent(i).getNumberOfAttendees(); j++){
                User user = organizer.getEvent(i).getAttendee(j);

                if(user instanceof Attendee){
                    boolean isDuplicate = false;

                    for (int k = 0; k < attendees.size(); k++){
                        if (user == attendees.get(k)){
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate){
                        continue;
                    }

                    Button button = new Button("Username: " + user.getUsername() + " | Balance: " + user.getWallet().getBalance() + "$");
                    attendeesList.getChildren().add(button);

                    attendees.add((Attendee) user);
                    attendeeButtons.add(button);
                }
            }
        }

        VBox attendeesBox = new VBox();
        attendeesBox.setSpacing(10);
        attendeesBox.setPadding(new Insets(0, 0, 0,20));
        attendeesBox.getChildren().add(attendeesLabel);
        attendeesBox.getChildren().add(attendeesList);

        return attendeesBox;
    }

    private static GridPane CreateNewEventPanel(){
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 40));
        grid.add(newEventLabel, 0, 0);
        grid.add(eventNameLabel, 0, 1);
        grid.add(eventNameField, 1, 1);
        grid.add(eventLocationLabel, 0, 2);
        grid.add(roomToHostEventSelector, 1, 2);
        grid.add(categorySelectorLabel, 0, 3);
        grid.add(eventCategorySelector, 1, 3);
        grid.add(priceLabel, 0, 4);
        grid.add(priceField, 1, 4);

        grid.add(AdminScreen.openingTimeLabel, 0, 5);
        grid.add(AdminScreen.openingHourSpinner, 1, 5);
        grid.add(AdminScreen.openingMinuteSpinner, 2, 5);
        grid.add(AdminScreen.closingTimeLabel, 0, 6);
        grid.add(AdminScreen.closingHourSpinner, 1, 6);
        grid.add(AdminScreen.closingMinuteSpinner, 2, 6);


        grid.add(eventDatePicker, 0, 7);

        grid.add(createEventButton, 0, 8);

        // (String name, LocalDate date, LocalTime startTime, LocalTime endTime, Category category, Organizer organizer, double price) {


        return grid;
    }
}
