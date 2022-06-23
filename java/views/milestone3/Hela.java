package views.milestone3;

import exceptions.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.world.Direction;
import java.net.URISyntaxException;
import java.util.Objects;

public class Hela extends GridPane implements Hazem {
    ImageView imageView;
    String name = "Hela";
    int count = 6;
    int columns = 6;
    int offsetX = 0;
    int offsetY = 0;
    int width = 53;
    int height = 89;
    SpriteAnimation animation;

    public Hela(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(400), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }
    public String getName(){
        return name;
    }

    static boolean xResized = false;
    static boolean yResized = false;
    static Direction d = null;
    private static void showFlashMessage(String message) {
        Stage window = GameScene.window;
        GridPane gridPane = GameScene.gridPane;

        Stage w = new Stage();
        w.initStyle(StageStyle.TRANSPARENT);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        Label label = new Label(message);
        label.setFont(Font.font("Aguda", FontWeight.EXTRA_BOLD, 35));
        label.setTextFill(Color.BLUEVIOLET);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(300);
        layout.getChildren().add(label);
        layout.setPadding(new Insets(3));
        layout.setStyle("-fx-background-color: transparent;");
        Scene s = new Scene(layout, Color.TRANSPARENT);
        w.setScene(s);
        w.initOwner(window);

        w.setAlwaysOnTop(true);
        final double midX = (window.getX() + gridPane.getLayoutX() + gridPane.getWidth()/2) ;
        final double midY = (window.getY() + gridPane.getLayoutY() + gridPane.getHeight()/2) ;



        w.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (!xResized && newValue.intValue() > 1) {
                w.setX(midX - newValue.intValue() / 2);
                xResized = true;
            }
        });

        w.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (!yResized && newValue.intValue() > 1) {
                w.setY(midY - newValue.intValue() / 2);
                yResized = true;
            }
        });
        xResized = false;
        yResized = false;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.INDEFINITE, evt -> w.showAndWait(), new KeyValue(layout.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(500), new KeyValue(layout.opacityProperty(), 1.0)),
                new KeyFrame(Duration.millis(1700), new KeyValue(layout.opacityProperty(), 0.2))
        );

        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.UP){
                d = Direction.UP;
                w.close();
            }else if(key.getCode() == KeyCode.DOWN){
                d = Direction.DOWN;
                w.close();
            } else if(key.getCode() == KeyCode.RIGHT){
                d = Direction.RIGHT;
                w.close();
            } else if(key.getCode() == KeyCode.LEFT){
                d = Direction.LEFT;
                w.close();
            }
        });

        timeline.play();
        w.showAndWait();

    }

    private void moveX(int x){
        boolean right = x > 0;
        for(int i = 0 ; i < Math.abs(x);++i){
            if(right) this.setTranslateX(this.getTranslateX()+1);
            else this.setTranslateX(this.getTranslateX()-1);
        }
        //animation.stop();
    }

    private void moveY(int y){
        boolean right = y > 0;
        for(int i = 0 ; i < Math.abs(y);++i){
            if(right) this.setTranslateY(this.getTranslateY()+1);
            else this.setTranslateY(this.getTranslateY()-1);
        }
    }

    public void moveLEFT(){
//        animation.setCount(6);
//        animation.setColumn(6);
//        animation.setWidth(75);
//        animation.setHeight(100);
//        animation.setOffsetX(150);
//        animation.setOffsetY(10);
//        animation.play();
        moveX(-100);
    }

    public void moveUP(){
//        animation.setCount(6);
//        animation.setColumn(6);
//        animation.setWidth(75);
//        animation.setHeight(100);
//        animation.setOffsetX(150);
//        animation.setOffsetY(10);
//        animation.play();
        moveY(-100);
    }

    public void moveRIGHT(){
//        animation.setCount(6);
//        animation.setColumn(6);
//        animation.setWidth(75);
//        animation.setHeight(100);
//        animation.setOffsetX(150);
//        animation.setOffsetY(10);
//        animation.play();
        moveX(100);
    }

    public void moveDOWN(){
//        animation.setCount(6);
//        animation.setColumn(6);
//        animation.setWidth(75);
//        animation.setHeight(100);
//        animation.setOffsetX(150);
//        animation.setOffsetY(10);
//        animation.play();
        moveY(100);
    }

    static MediaPlayer p;
    public void attack(){
        try {
            showFlashMessage("Use Arrow Keys To Choose Attack Direction");
            if(d == Direction.DOWN){
                d = Direction.UP;
            }else if(d == Direction.UP){
                d = Direction.DOWN;
            }
            Controller.g.attack(d);
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/Punch.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
//            animation.setColumn(6);
//            animation.setCount(6);
//            animation.setWidth(70);
//            animation.setHeight(100);
//            animation.setOffsetX(0);
//            animation.setOffsetY(160);
//            animation.play();
        } catch (InvalidTargetException e) {
            GameScene.showFlashMessage("Cannot Attack This Target");
        } catch (UnallowedMovementException e) {
            GameScene.showFlashMessage("Cannot Move To This Cell");
        } catch (ChampionDisarmedException e) {
            GameScene.showFlashMessage("Champion is Disarmed");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NotEnoughResourcesException e) {
            GameScene.showFlashMessage("Not Enough Action Points");
        }
    }

    public void Q() {
        try {
            Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(0));
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/GodessOfDeath.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
//            animation.setColumn(6);
//            animation.setCount(6);
//            animation.setWidth(93);
//            animation.setHeight(100);
//            animation.setOffsetX(887);
//            animation.setOffsetY(158);
//            animation.play();
        } catch (AbilityUseException e) {
            GameScene.showFlashMessage("Cannot Use Ability");
        } catch (InvalidTargetException e) {
            GameScene.showFlashMessage("Cannot Use Ability On This Target");
        } catch (NotEnoughResourcesException e) {
            GameScene.showFlashMessage("Not Enough Action Points");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void E(){
        try {
            Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(1));
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/ThornShield.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
//            animation.setColumn(6);
//            animation.setCount(6);
//            animation.setWidth(93);
//            animation.setHeight(100);
//            animation.setOffsetX(887);
//            animation.setOffsetY(158);
//            animation.play();
        } catch (AbilityUseException e) {
            GameScene.showFlashMessage("Cannot Use Ability");
        } catch (InvalidTargetException e) {
            GameScene.showFlashMessage("Cannot Use Ability On This Target");
        } catch (NotEnoughResourcesException e) {
            GameScene.showFlashMessage("Not Enough Action Points");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void R() {
        try {
            Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(2));
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/ThornShower.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
//            animation.setColumn(6);
//            animation.setCount(6);
//            animation.setWidth(93);
//            animation.setHeight(100);
//            animation.setOffsetX(887);
//            animation.setOffsetY(158);
//            animation.play();
        } catch (AbilityUseException e) {
            GameScene.showFlashMessage("Cannot Use Ability");
        } catch (InvalidTargetException e) {
            GameScene.showFlashMessage("Cannot Use Ability On This Target");
        } catch (NotEnoughResourcesException e) {
            GameScene.showFlashMessage("Not Enough Action Points");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
