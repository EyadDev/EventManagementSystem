import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Screens {
    public static Scene LoginRegisterScreen(boolean login) {
        // Create a GridPane
        var gridPane = new GridPane();
        gridPane.setPadding(new Insets(20)); // Add padding around the GridPane
        gridPane.setHgap(10); // Horizontal gap between columns
        gridPane.setVgap(10); // Vertical gap between rows

        // Create labels and text fields
        Label loginLabel = new Label(login ? "Login" : "Register");
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password");
        TextField passwordField = new TextField();
        Label addressLabel = new Label("Address");
        TextField addressField = new TextField();

        var loginRegisterHBox = new HBox();
        loginRegisterHBox.setSpacing(10); // Horizontal gap between columns
        Button button1 = new Button(login ? "Login" : "Register");
        Button loginPageButton = new Button("Go to " + (login ? "register" : "login") + " page");
        loginRegisterHBox.getChildren().add(button1);
        loginRegisterHBox.getChildren().add(loginPageButton);

        loginPageButton.setOnAction((ActionEvent e) -> {
            Main.primaryStage.setScene(Screens.LoginRegisterScreen(!login));
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
            double fontSize = Math.max(12, newVal.doubleValue() / 50); // Adjust the divisor for desired scaling
            loginLabel.setFont(new Font(fontSize));
            usernameLabel.setFont(new Font(fontSize));
            passwordLabel.setFont(new Font(fontSize));
            usernameField.setStyle("-fx-font-size: " + fontSize + "px;");
            passwordField.setStyle("-fx-font-size: " + fontSize + "px;");
            button1.setStyle("-fx-font-size: " + fontSize + "px;");
            loginPageButton.setStyle("-fx-font-size: " + fontSize + "px;");
            addressLabel.setFont(new Font(fontSize));
            addressField.setStyle("-fx-font-size: " + fontSize + "px;");
        });

        // Create a Scene and set it on the Stage
        return new Scene(CenterNode(gridPane), 1024, 576); // The Initial size can be set here
    }

    private static <T extends Node> Pane CenterNode(T node) {
        // Create a VBox to center the StackPane vertically
        VBox vBox = new VBox();
        vBox.getChildren().add(node);
        vBox.setAlignment(Pos.CENTER); // Center the StackPane in the VBox

        HBox hBox = new HBox();
        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }
}
