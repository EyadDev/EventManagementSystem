package Screens;

import Core.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.time.LocalTime;
import java.util.ArrayList;

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
    private static ArrayList<Button> attendeeButtons = new ArrayList<>();

    private static Label roomsLabel = new Label("Rooms");
    private static ArrayList<Button> roomsButtons = new ArrayList<>();

    private static Label eventsLabel = new Label("Events");
    private static ArrayList<Button> eventsButtons = new ArrayList<>();

    private static Label categoriesLabel = new Label("Categories");
    private static ArrayList<Button> categoriesButtons = new ArrayList<>();

    private static Label newRoomLabel = new Label("Create new Room");
    private static Label openingTimeLabel = new Label("Opening time");
    private static Spinner<Integer> openingHourSpinner = new Spinner(0, 24, 12, 1);
    private static Spinner<Integer> openingMinuteSpinner = new Spinner(0, 55, 0, 5);
    private static Label closingTimeLabel = new Label("Closing time");
    private static Spinner<Integer> closingHourSpinner = new Spinner(0, 24, 12, 1);
    private static Spinner<Integer> closingMinuteSpinner = new Spinner(0, 55, 0, 5);
    private static Button createRoomButton = new Button("Add");

    private static Label newCategoryLabel = new Label("Create new Category");
    private static Label categoryNameLabel = new Label("Name");
    private static TextField categoryNameField = new TextField("Default");
    private static Button createCategoryButton = new Button("Add");

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

        showAttendeesButton.setOnAction(e -> {
            borderPane.setCenter(CreateAttendeesList());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showRoomsButton.setOnAction(e -> {
            borderPane.setCenter(CreateRoomsList());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showEventsButton.setOnAction(e -> {
            borderPane.setCenter(CreateEventsList());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showCategoriesButton.setOnAction(e -> {
            borderPane.setCenter(CreateCategoriesList());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        addRoomButton.setOnAction(e -> {
            borderPane.setCenter(CreateNewRoomPanel());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        addCategoryButton.setOnAction(e -> {
            borderPane.setCenter(CreateNewCategoryPanel());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        createRoomButton.setOnAction(e -> {
            LocalTime openingTime = LocalTime.of(openingHourSpinner.getValue(), openingMinuteSpinner.getValue());
            LocalTime closingTime = LocalTime.of(closingHourSpinner.getValue(), closingMinuteSpinner.getValue());

            if (openingTime.isBefore(closingTime)){
                new Room(openingTime, closingTime);
                Helper.ShowAlert("Room succesfully created", Alert.AlertType.CONFIRMATION);
            }
            else{
                Helper.ShowAlert("Closing time can not be the same or before opening time", Alert.AlertType.ERROR);
            }

        });

        createCategoryButton.setOnAction(e -> {
            String name = categoryNameField.getText();

            if (!Category.isDuplicate(name)){
                new Category(name);
                Helper.ShowAlert("Category succesfully created", Alert.AlertType.CONFIRMATION);
            }
            else{
                Helper.ShowAlert("This category already exists", Alert.AlertType.ERROR);
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
        adminLabel.setFont(new Font(fontSize));
        addRoomButton.setStyle("-fx-font-size: " + fontSize + "px;");
        addCategoryButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showAttendeesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showEventsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showRoomsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showCategoriesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        attendeesLabel.setFont(new Font(fontSize));
        roomsLabel.setFont(new Font(fontSize));
        eventsLabel.setFont(new Font(fontSize));
        categoriesLabel.setFont(new Font(fontSize));

        newRoomLabel.setFont(new Font(fontSize));
        openingTimeLabel.setFont(new Font(fontSize));
        openingHourSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        openingMinuteSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        closingTimeLabel.setFont(new Font(fontSize));
        closingHourSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        closingMinuteSpinner.setStyle("-fx-font-size: " + fontSize + "px;");
        createRoomButton.setStyle("-fx-font-size: " + fontSize + "px;");

        newCategoryLabel.setFont(new Font(fontSize));
        categoryNameLabel.setFont(new Font(fontSize));
        categoryNameField.setStyle("-fx-font-size: " + fontSize + "px;");
        createCategoryButton.setStyle("-fx-font-size: " + fontSize + "px;");

        for(int i = 0; i < attendeeButtons.size(); i++){
            attendeeButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < roomsButtons.size(); i++){
            roomsButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < eventsButtons.size(); i++){
            eventsButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }

        for(int i = 0; i < categoriesButtons.size(); i++){
            categoriesButtons.get(i).setStyle("-fx-font-size: " + fontSize + "px;");
        }
    }

    //create a list of attendees that includes their username and wallet balance
    private static VBox CreateAttendeesList(){
        FlowPane attendeesList = new FlowPane();
        attendeesList.setVgap(10);
        attendeesList.setHgap(10);
        attendeesList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.getPeopleSize(); i++){
            User user = Database.getPerson(i);

            if(user instanceof Attendee){
                Button button = new Button("Username: " + user.getUsername() + " | Balance: " + user.getWallet().getBalance() + "$");
                attendeesList.getChildren().add(button);

                attendeeButtons.add(button);
            }
        }

        VBox attendeesBox = new VBox();
        attendeesBox.setSpacing(10);
        attendeesBox.setPadding(new Insets(0, 0, 0,20));
        attendeesBox.getChildren().add(attendeesLabel);
        attendeesBox.getChildren().add(attendeesList);

        return attendeesBox;
    }

    //create a list of attendees that includes their username and wallet balance
    private static VBox CreateRoomsList(){
        FlowPane roomsList = new FlowPane();
        roomsList.setVgap(10);
        roomsList.setHgap(10);
        roomsList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.getRoomsSize(); i++){
            Room room = Database.getRoom(i);

            HBox box =  new HBox();

            Button button = new Button("Room " + i + " | Opens at " + room.getOpeningTime() + " | closes at " + room.getClosingTime() + " | events: " + room.getEventsSize());
            box.getChildren().add(button);
            roomsButtons.add(button);

            Button removeButton = new Button("Remove");
            box.getChildren().add(removeButton);
            roomsButtons.add(removeButton);

            removeButton.setOnAction(e -> {
                if (room.getEventsSize() == 0){
                    Database.removeRoom(room);
                    showRoomsButton.fire();
                }
                else{
                    Helper.ShowAlert("Cannot remove Room as it has active events", Alert.AlertType.ERROR);
                }
            });

            roomsList.getChildren().add(box);
        }

        VBox roomsBox = new VBox();
        roomsBox.setSpacing(10);
        roomsBox.setPadding(new Insets(0, 0, 0,20));
        roomsBox.getChildren().add(roomsLabel);
        roomsBox.getChildren().add(roomsList);

        return roomsBox;
    }

    private static VBox CreateEventsList(){
        FlowPane eventsList = new FlowPane();
        eventsList.setVgap(10);
        eventsList.setHgap(10);
        eventsList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.getEventsSize(); i++){
            Event event = Database.getEvent(i);

            Button button = new Button(event.getName() + " | on " + event.getDate() + " | starts at " + event.getStartTime() + " | ends at " + event.getEndTime() + " | Category: " + event.getCategory());
            eventsList.getChildren().add(button);

            eventsButtons.add(button);
        }

        VBox eventsBox = new VBox();
        eventsBox.setSpacing(10);
        eventsBox.setPadding(new Insets(0, 0, 0,20));
        eventsBox.getChildren().add(eventsLabel);
        eventsBox.getChildren().add(eventsList);

        return eventsBox;
    }

    private static VBox CreateCategoriesList(){
        FlowPane categoriesList = new FlowPane();
        categoriesList.setVgap(10);
        categoriesList.setHgap(10);
        categoriesList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.getCategoriesSize(); i++){
            Category category = Database.getCategory(i);

            HBox box =  new HBox();

            Button button = new Button(category + " | " + "events: " + category.getEventsSize());
            box.getChildren().add(button);
            categoriesButtons.add(button);

            Button removeButton = new Button("Remove");
            box.getChildren().add(removeButton);
            categoriesButtons.add(removeButton);

            removeButton.setOnAction(e -> {
                if (category.getEventsSize() == 0){
                    Database.removeCategory(category);
                    showCategoriesButton.fire();
                }
                else{
                    Helper.ShowAlert("Cannot remove Category as there are events in this category", Alert.AlertType.ERROR);
                }
            });

            categoriesList.getChildren().add(box);
        }

        VBox categoriesBox = new VBox();
        categoriesBox.setSpacing(10);
        categoriesBox.setPadding(new Insets(0, 0, 0,20));
        categoriesBox.getChildren().add(categoriesLabel);
        categoriesBox.getChildren().add(categoriesList);

        return categoriesBox;
    }

    private static GridPane CreateNewRoomPanel(){
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 40));
        grid.add(newRoomLabel, 0, 0);
        grid.add(openingTimeLabel, 0, 1);
        grid.add(openingHourSpinner, 1, 1);
        grid.add(openingMinuteSpinner, 2, 1);
        grid.add(closingTimeLabel, 0, 2);
        grid.add(closingHourSpinner, 1, 2);
        grid.add(closingMinuteSpinner, 2, 2);
        grid.add(createRoomButton, 0, 3);

        return grid;
    }

    private static GridPane CreateNewCategoryPanel(){
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 40));
        grid.add(newCategoryLabel, 0, 0);
        grid.add(categoryNameLabel, 0, 1);
        grid.add(categoryNameField, 1, 1);
        grid.add(createCategoryButton, 0, 2);

        return grid;
    }
}
