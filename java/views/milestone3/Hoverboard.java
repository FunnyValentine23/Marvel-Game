package views.milestone3;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Hoverboard {

    public class TextChooser extends StackPane {
        private Label label = new Label();
        private ComboBox<String> combo = new ComboBox<>();

        public TextChooser(String... options) {
            StackPane.setAlignment(label, Pos.CENTER_LEFT);
            StackPane.setAlignment(combo, Pos.CENTER_LEFT);

            label.textProperty().bind(
                    combo.getSelectionModel().selectedItemProperty()
            );
            label.visibleProperty().bind(
                    combo.visibleProperty().not()
            );
            label.setPadding(new Insets(0, 0, 0, 9));

            combo.getItems().setAll(options);
            combo.getSelectionModel().select(0);
            combo.setVisible(false);

            label.setOnMouseEntered(event -> combo.setVisible(true));
            combo.showingProperty().addListener(observable -> {
                if (!combo.isShowing()) {
                    combo.setVisible(false);
                }
            });
            combo.setOnMouseExited(event -> {
                if (!combo.isShowing()) {
                    combo.setVisible(false);
                }
            });

            getChildren().setAll(label, combo);
        }
    }
}