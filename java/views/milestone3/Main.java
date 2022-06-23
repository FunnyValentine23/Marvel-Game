package views.milestone3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws URISyntaxException {
        mainMenu(primaryStage);
    }
    static String pathMainMenuSong = "/becoming-a-hero-epic-inspiring-adventurous-soundtrack-9279.mp3";
    static Media mediaMainMenu;
    static MediaPlayer mediaPlayerMainMenu;
    public static void mainMenu(Stage mainMenuStage) throws URISyntaxException {
        //Main Menu Song Play
        mediaMainMenu = new Media(Objects.requireNonNull(Main.class.getResource(pathMainMenuSong)).toURI().toString());
        mediaPlayerMainMenu = new MediaPlayer(mediaMainMenu);
        mediaPlayerMainMenu.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayerMainMenu.setAutoPlay(true);
        mediaPlayerMainMenu.play();

        //Main Menu Layout
        mainMenuStage.setTitle("Marvel: Ultimate War");
        mainMenuStage.setMaximized(true);
        StackPane layout = new StackPane();
        Scene scene1 = new Scene(layout);
        ImageView imageview = new ImageView("/wp7788914-marvels-avengers-game-wallpapers.jpg");
        imageview.fitWidthProperty().bind(scene1.widthProperty());
        imageview.fitHeightProperty().bind(scene1.heightProperty());
        imageview.setPreserveRatio(true);
        layout.getChildren().addAll(imageview);
        mainMenuStage.initStyle(StageStyle.UNDECORATED);

        Image i1 = new Image("/Game_Button (2) (1)-PhotoRoom-PhotoRoom (1)-depositphotos-bgremover.png");
        String path = "/Fighting_Spirit_2_ital.ttf";


        Image i2 = new Image("/OptionsButton.png");
        Image i3 = new Image("/QuitGameButton.png");

        //Main Menu Buttons
        Button newGame1v1Local = new Button();
        newGame1v1Local.setText("Two Player Mode");
        newGame1v1Local.setFont(Font.loadFont(Main.class.getResourceAsStream(path), 15));
        newGame1v1Local.setStyle("-fx-text-fill: white");
        newGame1v1Local.setBackground(new Background(new BackgroundImage(i1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        newGame1v1Local.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            newGame1v1Local.setEffect(new DropShadow());
        });
        newGame1v1Local.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            newGame1v1Local.setEffect(null);
        });

        Button newGamevComputer = new Button();
        newGamevComputer.setText("One Player Mode");
        newGamevComputer.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            newGamevComputer.setEffect(new DropShadow());
        });
        newGamevComputer.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            newGamevComputer.setEffect(null);
        });

        Button newGameOnline = new Button();
        newGameOnline.setText("Online Multiplayer");
        newGameOnline.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            newGameOnline.setEffect(new DropShadow());
        });
        newGameOnline.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            newGameOnline.setEffect(null);
        });

        Button gameOptions = new Button();
        gameOptions.setFont(Font.loadFont(Main.class.getResourceAsStream(path), 15));
        gameOptions.setStyle("-fx-text-fill: white");
        gameOptions.setBackground(new Background(new BackgroundImage(i2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //newGame1v1Local.setBackground(new Background(new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gameOptions.setText("Settings");
        gameOptions.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            gameOptions.setEffect(new DropShadow());
        });
        gameOptions.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            gameOptions.setEffect(null);
        });

        Button quitGame = new Button();
        quitGame.setFont(Font.loadFont(Main.class.getResourceAsStream(path), 15));
        quitGame.setStyle("-fx-text-fill: white");
        quitGame.setBackground(new Background(new BackgroundImage(i3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //newGame1v1Local.setBackground(new Background(new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        quitGame.setText("Quit Game");
        quitGame.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            quitGame.setEffect(new DropShadow());
        });
        quitGame.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            quitGame.setEffect(null);
        });

//        quitGame.setShape(new Circle(10));
//        quitGame.setMinSize(20, 20);
//        quitGame.setMaxSize(20, 20);

        newGame1v1Local.setPrefHeight(70);
        newGame1v1Local.setPrefWidth(500);

        newGamevComputer.setPrefHeight(50);
        newGamevComputer.setPrefWidth(400);

        newGameOnline.setPrefHeight(50);
        newGameOnline.setPrefWidth(400);

        gameOptions.setPrefHeight(50);
        gameOptions.setPrefWidth(300);

        quitGame.setPrefHeight(50);
        quitGame.setPrefWidth(200);

        VBox v = new VBox(10);
        v.prefHeightProperty().bind(layout.heightProperty());
        v.prefWidthProperty().bind(layout.widthProperty());
        v.getChildren().addAll(newGame1v1Local, gameOptions, quitGame);
        layout.getChildren().add(v);
        layout.setPadding(new Insets(225)); // set to 125 if using 5 buttons
        v.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons Function
        newGame1v1Local.setOnAction(e -> AlertBoxNewGame1v1.display(mainMenuStage));

        quitGame.setOnAction(e -> System.exit(0));

        mainMenuStage.setScene(scene1);
        mainMenuStage.show();
    }

}
