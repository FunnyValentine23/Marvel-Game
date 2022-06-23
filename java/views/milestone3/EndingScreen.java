package views.milestone3;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EndingScreen{
    static String pathMainMenuSong = "/becoming-a-hero-epic-inspiring-adventurous-soundtrack-9279.mp3";
    static Media mediaMainMenu;
    static MediaPlayer mediaPlayerMainMenu;
    public static void display(){
//        mediaMainMenu = new Media(Objects.requireNonNull(EndingScreen.class.getResource(pathMainMenuSong)).toURI().toString());
//        mediaPlayerMainMenu = new MediaPlayer(mediaMainMenu);
//        mediaPlayerMainMenu.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayerMainMenu.setAutoPlay(true);
//        mediaPlayerMainMenu.play();

        //Main Menu Layout
        Stage ending = new Stage();
        ending.setTitle("Marvel: Ultimate War");
        ending.setMaximized(true);
        StackPane layout = new StackPane();



        Label label = new Label();
        String path = "/Fighting_Spirit_2_ital.ttf";
        if(Controller.g.getFirstPlayer().getTeam().size()==0){
            label.setText("CONGRATULATIONS "+Controller.g.getSecondPlayer().getName()+", YOU WON!");
            label.setFont(Font.loadFont(EndingScreen.class.getResourceAsStream(path), 45));
            label.setTextFill(Color.WHITE);
        }
        else{
            label.setText("CONGRATULATIONS "+Controller.g.getFirstPlayer().getName()+", YOU WON!");
            label.setFont(Font.loadFont(EndingScreen.class.getResourceAsStream(path), 45));
            label.setTextFill(Color.WHITE);
        }



        Scene scene1 = new Scene(layout);
        ImageView imageview = new ImageView("/VictoryScreen.gif");
        imageview.fitWidthProperty().bind(scene1.widthProperty());
        imageview.fitHeightProperty().bind(scene1.heightProperty());
        imageview.setPreserveRatio(true);
        layout.getChildren().addAll(imageview);
        layout.getChildren().addAll(label);
        ending.setScene(scene1);
        ending.show();

    }
}


