package Screens;

import Core.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.time.LocalTime;
import java.util.ArrayList;

public class AdminScreen {
    private static final Label adminLabel = new Label("Admin interface");
    private static final TextField roomNameField = new TextField();

    private static final Button addRoomButton = new Button("Add Room");
    private static final Button addCategoryButton = new Button("Add Category");
    private static final Button showAttendeesButton = new Button("Attendees");
    private static final Button showEventsButton = new Button("Events");
    private static final Button showRoomsButton = new Button("Rooms");
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

    private static final Label newRoomLabel = new Label("Create new Room");
    private static final Label roomNameLabel = new Label("Room Name");
    static final Label openingTimeLabel = new Label("Opening time");
    static final Spinner<Integer> openingHourSpinner = new Spinner(0, 24, 12, 1);
    static final Spinner<Integer> openingMinuteSpinner = new Spinner(0, 55, 0, 5);
    static final Label closingTimeLabel = new Label("Closing time");
    static final Spinner<Integer> closingHourSpinner = new Spinner(0, 24, 12, 1);
    static final Spinner<Integer> closingMinuteSpinner = new Spinner(0, 55, 0, 5);
    private static final Button createRoomButton = new Button("Add");

    private static final Label newCategoryLabel = new Label("Create new Category");
    private static final Label categoryNameLabel = new Label("Name");
    private static final TextField categoryNameField = new TextField("Default");
    private static final Button createCategoryButton = new Button("Add");

    public static Scene AdminScreen() {

        VBox buttonStack = new VBox();
        buttonStack.setSpacing(10);
        buttonStack.getChildren().add(addRoomButton);
        buttonStack.getChildren().add(addCategoryButton);
        buttonStack.getChildren().add(showAttendeesButton);
        buttonStack.getChildren().add(showEventsButton);
        buttonStack.getChildren().add(showRoomsButton);
        buttonStack.getChildren().add(showCategoriesButton);
        buttonStack.getChildren().add(logoutButton);

        // Create a GridPane
        var borderPane = new BorderPane();
        borderPane.setTop(ScreensHelper.CenterNode(adminLabel));
        borderPane.setLeft(buttonStack);

        ScrollPane sp1 = new ScrollPane();
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        showAttendeesButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateAttendeesList());
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showRoomsButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateRoomsList(true, roomButtons, showRoomsButton));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showEventsButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateEventsList(eventButtons));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        showCategoriesButton.setOnAction(e -> {
            borderPane.setCenter(sp1);
            sp1.setContent(CreateCategoriesList(true, categoryButtons, showCategoriesButton));
            UpdateSizes(Main.primaryStage.widthProperty().doubleValue());
        });

        logoutButton.setOnAction(e -> {
            Main.primaryStage.setScene(LoginRegisterScreen.LoginRegisterScreen(true, RegisterType.none));
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
                new Room(roomNameField.getText(), openingTime, closingTime);
                ScreensHelper.ShowAlert("Room successfully created", Alert.AlertType.CONFIRMATION);
            }
            else{
                ScreensHelper.ShowAlert("Closing time can not be the same or before opening time", Alert.AlertType.ERROR);
            }

        });

        createCategoryButton.setOnAction(e -> {
            String name = categoryNameField.getText();

            if (!Category.isDuplicate(name)){
                new Category(name);
                ScreensHelper.ShowAlert("Category successfully created", Alert.AlertType.CONFIRMATION);
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

    public static void UpdateSizes(double newVal){
        double fontSize = Math.max(12, newVal / 50);
        adminLabel.setFont(new Font(fontSize));
        addRoomButton.setStyle("-fx-font-size: " + fontSize + "px;");
        addCategoryButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showAttendeesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showEventsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showRoomsButton.setStyle("-fx-font-size: " + fontSize + "px;");
        showCategoriesButton.setStyle("-fx-font-size: " + fontSize + "px;");
        logoutButton.setStyle("-fx-font-size: " + fontSize + "px;");
        attendeesLabel.setFont(new Font(fontSize));
        roomsLabel.setFont(new Font(fontSize));
        eventsLabel.setFont(new Font(fontSize));
        categoriesLabel.setFont(new Font(fontSize));

        newRoomLabel.setFont(new Font(fontSize));
        roomNameLabel.setFont(new Font(fontSize));
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
        roomNameField.setStyle("-fx-font-size: " + fontSize + "px;");
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
    }

    //create a list of attendees that includes their username and wallet balance
    private static VBox CreateAttendeesList(){
        FlowPane attendeesList = new FlowPane();
        attendeesList.setVgap(10);
        attendeesList.setHgap(10);
        attendeesList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.instance.getPeopleSize(); i++){
            User user = Database.instance.getPerson(i);

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
    static VBox CreateRoomsList(boolean showRemoveButton, ArrayList<Button> roomButtons, Button buttonToFire){
        FlowPane roomsList = new FlowPane();
        roomsList.setVgap(10);
        roomsList.setHgap(10);
        roomsList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.instance.getRoomsSize(); i++){
            Room room = Database.instance.getRoom(i);

            HBox box =  new HBox();

            box.setSpacing(10);

            Button button = new Button(room.getRoomName() + " | Opens at " + room.getOpeningTime() + " | Closes at " + room.getClosingTime() + " | Number of Events: " + room.getEventsSize());
            box.getChildren().add(button);
            roomButtons.add(button);

            if (showRemoveButton) {
                Button removeButton = new Button("Remove");
                box.getChildren().add(removeButton);
                roomButtons.add(removeButton);

                removeButton.setOnAction(e -> {
                    if (room.getEventsSize() == 0) {
                        Database.instance.removeRoom(room);
                        buttonToFire.fire();
                    } else {
                        ScreensHelper.ShowAlert("Cannot remove Room as it has active events", Alert.AlertType.ERROR);
                    }
                });
            }

            roomsList.getChildren().add(box);
        }

        VBox roomsBox = new VBox();
        roomsBox.setSpacing(10);
        roomsBox.setPadding(new Insets(0, 0, 0,20));
        roomsBox.getChildren().add(roomsLabel);
        roomsBox.getChildren().add(roomsList);

        return roomsBox;
    }

    static VBox CreateEventsList(ArrayList<Button> eventButtons){
        FlowPane eventsList = new FlowPane();
        eventsList.setVgap(10);
        eventsList.setHgap(10);
        eventsList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.instance.getEventsSize(); i++){
            Event event = Database.instance.getEvent(i);

            Button button = new Button(event.getName() + " | on " + event.getDate() + " | starts at " + event.getStartTime() + " | ends at " + event.getEndTime() + " | Category: " + event.getCategory() + " | " + event.getNumberOfAttendees() + " attending");
            eventsList.getChildren().add(button);

            eventButtons.add(button);
        }

        VBox eventsBox = new VBox();
        eventsBox.setSpacing(10);
        eventsBox.setPadding(new Insets(0, 0, 0,20));
        eventsBox.getChildren().add(eventsLabel);
        eventsBox.getChildren().add(eventsList);

        return eventsBox;
    }

    static VBox CreateCategoriesList(boolean showRemoveButton, ArrayList<Button> categoryButtons, Button buttonToFire){
        FlowPane categoriesList = new FlowPane();
        categoriesList.setVgap(10);
        categoriesList.setHgap(10);
        categoriesList.setOrientation(Orientation.VERTICAL);

        for (int i = 0; i < Database.instance.getCategoriesSize(); i++){
            Category category = Database.instance.getCategory(i);

            HBox box =  new HBox();

            Button button = new Button(category + " | " + "Events: " + category.getEventsSize());
            box.getChildren().add(button);
            categoryButtons.add(button);

            if (showRemoveButton) {
                Button removeButton = new Button("Remove");
                box.getChildren().add(removeButton);
                categoryButtons.add(removeButton);

                removeButton.setOnAction(e -> {
                    if (category.getEventsSize() == 0){
                        Database.instance.removeCategory(category);
                        buttonToFire.fire();
                    }
                    else{
                        ScreensHelper.ShowAlert("Cannot remove Category as there are events in this category", Alert.AlertType.ERROR);
                    }
                });
            }

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
        grid.add(roomNameLabel, 0, 1);
        grid.add(roomNameField, 1, 1);
        grid.add(openingTimeLabel, 0, 2);
        grid.add(openingHourSpinner, 1, 2);
        grid.add(openingMinuteSpinner, 2, 2);
        grid.add(closingTimeLabel, 0, 3);
        grid.add(closingHourSpinner, 1, 3);
        grid.add(closingMinuteSpinner, 2, 3);
        grid.add(createRoomButton, 0, 4);

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