package views.milestone3;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.Objects;

public class AlertBoxNewGame1v1 {

    public static void display(Stage mainMenuStage) {
        Stage window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);

        String path = "/Fighting_Spirit_2_ital.ttf";

        HBox layout = new HBox(10);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Image i2 = new Image("/choosename.jpg");

        Label firstPlayerName = new Label("First Player Name: ");
        GridPane.setConstraints(firstPlayerName, 0, 1);

        //Name Input
        TextField playerOne = new TextField("Player One");
        GridPane.setConstraints(playerOne, 1, 1);

        playerOne.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 20));
        //playerOne.setStyle("-fx-text-fill: white");
        firstPlayerName.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 20));
        firstPlayerName.setStyle("-fx-text-fill: red");

        Label secondPlayerName = new Label("Second Player Name: ");
        GridPane.setConstraints(secondPlayerName, 0, 2);

        TextField playerTwo = new TextField("Player Two");
        GridPane.setConstraints(playerTwo, 1, 2);

        playerTwo.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 20));
        //playerTwo.setStyle("-fx-text-fill: white");
        secondPlayerName.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 20));
        secondPlayerName.setStyle("-fx-text-fill: red");

        grid.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(firstPlayerName, playerOne, secondPlayerName, playerTwo);


        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setBottom(layout);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(e -> {
            String pn1 = playerOne.getText();
            String pn2 = playerTwo.getText();
            if(Objects.equals(pn1, "") || Objects.equals(pn2, "")){

            }else {
                window.close();
                Controller.playerNames(pn1, pn2);
                try {
                    Controller.initGame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    ChampionSelection.display();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mainMenuStage.close();
            }
        });

        Button close = new Button("Cancel");
        close.setOnAction(e -> window.close());

        layout.getChildren().addAll(confirm, close);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setPadding(new Insets(30));


        Image i1 = new Image("/playernamebutton.png");

        close.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            close.setEffect(new DropShadow());
        });
        close.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            close.setEffect(null);
        });

        confirm.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            confirm.setEffect(new DropShadow());
        });
        confirm.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            confirm.setEffect(null);
        });

        close.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 10));
        close.setStyle("-fx-text-fill: white");
        close.setBackground(new Background(new BackgroundImage(i1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        confirm.setFont(Font.loadFont(AlertBoxNewGame1v1.class.getResourceAsStream(path), 10));
        confirm.setStyle("-fx-text-fill: white");
        confirm.setBackground(new Background(new BackgroundImage(i1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        close.setPrefHeight(40);
        close.setPrefWidth(80);

        confirm.setPrefHeight(40);
        confirm.setPrefWidth(80);


        //Display window and wait for it to be closed before returning
        border.setBackground(new Background(new BackgroundImage(i2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Scene scene = new Scene(border, 500, 300);
        window.setScene(scene);
        window.showAndWait();
    }

}