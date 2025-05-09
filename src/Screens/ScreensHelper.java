package Screens;

import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ScreensHelper {
    static <T extends Node> Pane CenterNode(T node) {
        // Create a VBox to center the StackPane vertically
        VBox vBox = new VBox();
        vBox.getChildren().add(node);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    public static void ShowAlert(String error, Alert.AlertType type) {
        Alert alert = new Alert(type);
        var title = type.toString().toUpperCase().charAt(0) + type.toString().toLowerCase().substring(1);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
