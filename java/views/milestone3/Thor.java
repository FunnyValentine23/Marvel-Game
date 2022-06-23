package views.milestone3;

import exceptions.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.world.Direction;
import java.net.URISyntaxException;
import java.util.Objects;

public class Thor extends GridPane implements Hazem{
    ImageView imageView;
    String name = "Thor";
    int count = 8;
    int columns = 8;
    int offsetX = 70;
    int offsetY = 30;
    int width = 100;
    int height = 100;
    SpriteAnimation animation;

    public Thor(ImageView imageView){
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
        animation.setCount(8);
        animation.setColumn(8);
        animation.setWidth(100);
        animation.setHeight(100);
        animation.setOffsetX(30);
        animation.setOffsetY(30);
        animation.play();
        moveX(-100);
    }

    public void moveUP(){
        animation.setCount(8);
        animation.setColumn(8);
        animation.setWidth(100);
        animation.setHeight(100);
        animation.setOffsetX(30);
        animation.setOffsetY(30);
        animation.play();
        moveY(-100);
    }

    public void moveRIGHT(){
        animation.setCount(8);
        animation.setColumn(8);
        animation.setWidth(100);
        animation.setHeight(100);
        animation.setOffsetX(30);
        animation.setOffsetY(30);
        animation.play();
        moveX(100);
    }

    public void moveDOWN(){
        animation.setCount(8);
        animation.setColumn(8);
        animation.setWidth(100);
        animation.setHeight(100);
        animation.setOffsetX(30);
        animation.setOffsetY(30);
        animation.play();
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
            animation.setColumn(2);
            animation.setCount(3);
            animation.setWidth(100);
            animation.setHeight(100);
            animation.setOffsetX(40);
            animation.setOffsetY(573);
            animation.play();
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
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/GodOfThunder.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
            animation.setColumn(3);
            animation.setCount(8);
            animation.setWidth(100);
            animation.setHeight(100);
            animation.setOffsetX(0);
            animation.setOffsetY(1330);
            animation.play();
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

    public static void displaySingle(){
        window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        gridPane = new GridPane();
        initialize();

        borderPane.setTop(new Label("Click On Cell To Apply Ability On"));

        gridPane.setBackground(new Background(new BackgroundImage(GameScene.img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //gridPane.setStyle("-fx-background-insets: 0, 1");
        borderPane.setCenter(gridPane);

        gridPane.setPrefHeight(500);
        gridPane.setPrefWidth(500);

        window.setScene(new Scene(borderPane));
        window.showAndWait();
    }
    static GridPane gridPane;
    public static void initialize() {
        int numCols = 5;
        int numRows = 5;

        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }
    static Stage window;

    private static void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            x = colIndex;
            y = rowIndex;
            System.out.println(x+" "+y);
            if(x != 10 && y != 10){
                window.close();
            }
        });
        gridPane.add(pane, colIndex, rowIndex);
    }
    static int x = 10, y = 10;
    public void E() {
        displaySingle();
        try {
            Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(1), y, x);
            Media m = null;
            try {
                m = new Media(Objects.requireNonNull(getClass().getResource("/MijlllonirThrow.mp3")).toURI().toString());
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
            animation.setColumn(7);
            animation.setCount(8);
            animation.setWidth(100);
            animation.setHeight(100);
            animation.setOffsetX(460);
            animation.setOffsetY(770);
            animation.play();
        } catch (AbilityUseException ex) {
            GameScene.showFlashMessage("Cannot Use Ability");
        } catch (InvalidTargetException ex) {
            GameScene.showFlashMessage("Cannot Use Ability On This Target");
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        } catch (NotEnoughResourcesException ex) {
            GameScene.showFlashMessage("Not Enough Action Points");
        }
        x = 10;
        y = 10;
    }

    public void R(){
        try {
            Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(2));
            Media m = new Media(Objects.requireNonNull(getClass().getResource("/BringMeThanos.mp3")).toURI().toString());
            p = new MediaPlayer(m);
            p.setCycleCount(1);
            p.setAutoPlay(true);
            p.play();
            animation.setColumn(7);
            animation.setCount(8);
            animation.setWidth(100);
            animation.setHeight(100);
            animation.setOffsetX(460);
            animation.setOffsetY(770);
            animation.play();
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
