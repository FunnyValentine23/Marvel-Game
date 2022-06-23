package views.milestone3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.ArrayList;

public class ChampionSelection {

    /*
    maxhp
    type
    mana
    maxactionpoints
    speed
    attack range
    attack damage
    abilities
     */
    static Stage championSelectionStage;
    static BorderPane root;
    static ToggleButton[] arr;
    static ArrayList<String> playerOne = new ArrayList<>();
    static boolean first = true;
    static HBox firstPlayerChampions;

    public ChampionSelection() throws Exception {
    }

    public static void disableChampions(){
        if(playerOne.contains("Hulk")){
            arr[0].setDisable(true);
        }
        if(playerOne.contains("Captain America")){
            arr[1].setDisable(true);
        }
        if(playerOne.contains("Deadpool")){
            arr[2].setDisable(true);
        }
        if(playerOne.contains("Dr Strange")){
            arr[3].setDisable(true);
        }
        if(playerOne.contains("Electro")){
            arr[4].setDisable(true);
        }
        if(playerOne.contains("Ghost Rider")){
            arr[5].setDisable(true);
        }
        if(playerOne.contains("Hela")){
            arr[6].setDisable(true);
        }
        if(playerOne.contains("Iceman")){
            arr[7].setDisable(true);
        }
        if(playerOne.contains("Ironman")){
            arr[8].setDisable(true);
        }
        if(playerOne.contains("Loki")){
            arr[9].setDisable(true);
        }
        if(playerOne.contains("Quicksilver")){
            arr[10].setDisable(true);
        }
        if(playerOne.contains("Spiderman")){
            arr[11].setDisable(true);
        }
        if(playerOne.contains("Thor")){
            arr[12].setDisable(true);
        }
        if(playerOne.contains("Venom")){
            arr[13].setDisable(true);
        }
        if(playerOne.contains("Yellow Jacket")){
            arr[14].setDisable(true);
        }
        for(int i = 0; i < 15; ++i){
            arr[i].setSelected(false);
        }
    }

    public static void getInfo(){
        firstPlayerChampions = new HBox(40);
        firstPlayerChampions.setAlignment(Pos.CENTER);
        for(String s: playerOne){
            Image img = new Image("/"+s+"Info.jpg",320,600,false,false);
            ImageView imgView = new ImageView(img);
            imgView.setPreserveRatio(true);
            firstPlayerChampions.getChildren().add(imgView);
        }
        root.setTop(firstPlayerChampions);
    }

    public static void leaderPlayerOne(){
        Stage window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);

        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();

        b1.setShape(new Circle(50));
        b1.setMaxSize(100, 100);
        b1.setMinSize(100, 100);

        b2.setShape(new Circle(50));
        b2.setMaxSize(100, 100);
        b2.setMinSize(100, 100);

        b3.setShape(new Circle(50));
        b3.setMaxSize(100, 100);
        b3.setMinSize(100, 100);

        b1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b1.setEffect(new DropShadow());
        });
        b1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b1.setEffect(null);
        });
        b2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b2.setEffect(new DropShadow());
        });
        b2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b2.setEffect(null);
        });
        b3.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b3.setEffect(new DropShadow());
        });
        b3.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b3.setEffect(null);
        });

        String path = "/Fighting_Spirit_2_ital.ttf";
        Image ii2 = new Image("/choosename.jpg");

        Label firstPlayerName = new Label("Choose First Player Leader");
        firstPlayerName.setFont(Font.loadFont(ChampionSelection.class.getResourceAsStream(path), 20));
        firstPlayerName.setStyle("-fx-text-fill: white");
        VBox v = new VBox(30);
        v.setBackground(new Background(new BackgroundImage(ii2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i1 = new Image("/"+Controller.g.getFirstPlayer().getTeam().get(0).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv1 = new ImageView(i1);
        b1.setBackground(new Background(new BackgroundImage(i1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i2 = new Image("/"+Controller.g.getFirstPlayer().getTeam().get(1).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv2 = new ImageView(i2);
        b2.setBackground(new Background(new BackgroundImage(i2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i3 = new Image("/"+Controller.g.getFirstPlayer().getTeam().get(2).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv3 = new ImageView(i3);
        b3.setBackground(new Background(new BackgroundImage(i3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10));
        h.getChildren().addAll(b1, b2, b3);

        b1.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getFirstPlayer().getTeam().get(0).getName(), true);
            leaderSecondPlayer();
            Main.mediaPlayerMainMenu.stop();
            window.close();
        });
        b2.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getFirstPlayer().getTeam().get(1).getName(), true);
            leaderSecondPlayer();
            Main.mediaPlayerMainMenu.stop();
            window.close();
        });
        b3.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getFirstPlayer().getTeam().get(2).getName(), true);
            leaderSecondPlayer();
            Main.mediaPlayerMainMenu.stop();
            window.close();
        });
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(firstPlayerName, h);

        Scene scene = new Scene(v, 500, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void leaderSecondPlayer(){
        Stage window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);

        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();

        b1.setShape(new Circle(50));
        b1.setMaxSize(100, 100);
        b1.setMinSize(100, 100);

        b2.setShape(new Circle(50));
        b2.setMaxSize(100, 100);
        b2.setMinSize(100, 100);

        b3.setShape(new Circle(50));
        b3.setMaxSize(100, 100);
        b3.setMinSize(100, 100);

        b1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b1.setEffect(new DropShadow());
        });
        b1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b1.setEffect(null);
        });
        b2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b2.setEffect(new DropShadow());
        });
        b2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b2.setEffect(null);
        });
        b3.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            b3.setEffect(new DropShadow());
        });
        b3.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            b3.setEffect(null);
        });

        String path = "/Fighting_Spirit_2_ital.ttf";
        Image ii2 = new Image("/choosename.jpg");

        Label firstPlayerName = new Label("Choose Second Player Leader");
        firstPlayerName.setFont(Font.loadFont(ChampionSelection.class.getResourceAsStream(path), 20));
        firstPlayerName.setStyle("-fx-text-fill: white");

        VBox v = new VBox(20);
        v.setBackground(new Background(new BackgroundImage(ii2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i1 = new Image("/"+Controller.g.getSecondPlayer().getTeam().get(0).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv1 = new ImageView(i1);
        b1.setBackground(new Background(new BackgroundImage(i1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i2 = new Image("/"+Controller.g.getSecondPlayer().getTeam().get(1).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv2 = new ImageView(i2);
        b2.setBackground(new Background(new BackgroundImage(i2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Image i3 = new Image("/"+Controller.g.getSecondPlayer().getTeam().get(2).getName()+"ButtonFinalNo.png", 100, 100, false, false);
        ImageView iv3 = new ImageView(i3);
        b3.setBackground(new Background(new BackgroundImage(i3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(10));
        h.getChildren().addAll(b1, b2, b3);

        b1.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getSecondPlayer().getTeam().get(0).getName(), false);
            try {
                GameScene.display();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            window.close();
            championSelectionStage.close();
        });
        b2.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getSecondPlayer().getTeam().get(1).getName(), false);
            try {
                GameScene.display();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            window.close();
            championSelectionStage.close();
        });
        b3.setOnAction(e -> {
            Controller.chooseLeader(Controller.g.getSecondPlayer().getTeam().get(2).getName(), false);
            try {
                GameScene.display();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            window.close();
            championSelectionStage.close();
        });

        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(firstPlayerName, h);
        Scene scene = new Scene(v, 500, 300);
        window.setScene(scene);
        window.showAndWait();
    }
    // "/Abilities.csv"



    public static void display(){
        championSelectionStage = new Stage();
        championSelectionStage.setTitle("Marvel: Ultimate War");
        championSelectionStage.setMaximized(true);
        championSelectionStage.initStyle(StageStyle.UNDECORATED);



        root = new BorderPane();
        Scene s = new Scene(root);
        ImageView imageview = new ImageView("/ChampionSelectionScreen.jpg");
        imageview.fitWidthProperty().bind(s.widthProperty());
        imageview.fitHeightProperty().bind(s.heightProperty());
        imageview.setPreserveRatio(true);
        root.getChildren().addAll(imageview);


//        root.getCenter().prefWidth(0);
//        root.getBottom().prefHeight(0);
//        root.getTop().prefHeight(0);
//        root.getLeft().prefWidth(0);

        arr = new ToggleButton[15];

        ToggleButton hulk = new ToggleButton();
        ToggleButton captainAmerica = new ToggleButton();
        ToggleButton deadPool = new ToggleButton();
        ToggleButton drStrange = new ToggleButton();
        ToggleButton electro = new ToggleButton();
        ToggleButton ghostRider = new ToggleButton();
        ToggleButton hela = new ToggleButton();
        ToggleButton iceMan = new ToggleButton();
        ToggleButton ironMan = new ToggleButton();
        ToggleButton loki = new ToggleButton();
        ToggleButton quickSilver = new ToggleButton();
        ToggleButton spiderMan = new ToggleButton();
        ToggleButton thor = new ToggleButton();
        ToggleButton venom = new ToggleButton();
        ToggleButton yellowJacket = new ToggleButton();

        HBox v = new HBox(10);
        HBox h = new HBox(10);

//        hulk.setText("Hulk");
//        hulk.setStyle("-fx-font-size:0");
//        hulk.setTextFill(Color.WHITE);
//        Image hulkImg = new Image("/Hulk.jpg",70,70,false,false);
//        ImageView hulkImgView = new ImageView(hulkImg);
//        hulk.setStyle("-fx-background-color: #0b2370; ");
//        hulk.setGraphic(hulkImgView);
//        hulkImgView.setPreserveRatio(true);
//        hulk.setContentDisplay(ContentDisplay.TOP);
        hulk.setShape(new Circle(60));
        hulk.setMaxSize(120, 120);
        hulk.setMinSize(120, 120);
        Image hulkImg = new Image("/HulkButtonFinalNo.png", 120, 120, false, false);
        hulk.setBackground(new Background(new BackgroundImage(hulkImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[0] = hulk;
        hulk.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            hulk.setEffect(new DropShadow());
        });
        hulk.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            hulk.setEffect(null);
        });

//        captainAmerica.setText("Captain America");
//        captainAmerica.setStyle("-fx-font-size:0");
//        captainAmerica.setTextFill(Color.WHITE);
//        Image captainAmericaImg = new Image("/Captain America.jpg",70,70,false,false);
//        ImageView captainAmericaImgView = new ImageView(captainAmericaImg);
//        captainAmerica.setStyle("-fx-background-color: #0b2370; ");
//        captainAmerica.setGraphic(captainAmericaImgView);
//        captainAmericaImgView.setPreserveRatio(true);
//        captainAmerica.setContentDisplay(ContentDisplay.TOP);
        captainAmerica.setShape(new Circle(60));
        captainAmerica.setMaxSize(120, 120);
        captainAmerica.setMinSize(120, 120);
        Image captainAmericaImg = new Image("/Captain AmericaButtonFinalNo.png", 120, 120, false, false);
        captainAmerica.setBackground(new Background(new BackgroundImage(captainAmericaImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[1] = captainAmerica;
        captainAmerica.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            captainAmerica.setEffect(new DropShadow());
        });
        captainAmerica.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            captainAmerica.setEffect(null);
        });

//        deadPool.setText("Deadpool");
//        deadPool.setStyle("-fx-font-size:0");
//        deadPool.setTextFill(Color.WHITE);
//        Image deadPoolImg = new Image("/Deadpool.jpg",70,70,false,false);
//        ImageView deadPoolImgView = new ImageView(deadPoolImg);
//        deadPool.setStyle("-fx-background-color: #0b2370; ");
//        deadPool.setGraphic(deadPoolImgView);
//        deadPoolImgView.setPreserveRatio(true);
//        deadPool.setContentDisplay(ContentDisplay.TOP);
        deadPool.setShape(new Circle(60));
        deadPool.setMaxSize(120, 120);
        deadPool.setMinSize(120, 120);
        Image deadPoolImg = new Image("/DeadpoolButtonFinalNo.png", 120, 120, false, false);
        deadPool.setBackground(new Background(new BackgroundImage(deadPoolImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[2] = deadPool;
        deadPool.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            deadPool.setEffect(new DropShadow());
        });
        deadPool.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            deadPool.setEffect(null);
        });

//        drStrange.setText("Dr Strange");
//        drStrange.setStyle("-fx-font-size:0");
//        drStrange.setTextFill(Color.WHITE);
//        Image drStrangeImg = new Image("/Dr Strange.jpg",70,70,false,false);
//        ImageView drStrangeImgView = new ImageView(drStrangeImg);
//        drStrange.setStyle("-fx-background-color: #0b2370; ");
//        drStrange.setGraphic(drStrangeImgView);
//        drStrangeImgView.setPreserveRatio(true);
//        drStrange.setContentDisplay(ContentDisplay.TOP);
        drStrange.setShape(new Circle(60));
        drStrange.setMaxSize(120, 120);
        drStrange.setMinSize(120, 120);
        Image drStrangeImg = new Image("/Dr StrangeButtonFinalNo.png", 120, 120, false, false);
        drStrange.setBackground(new Background(new BackgroundImage(drStrangeImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[3] = drStrange;
        drStrange.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            drStrange.setEffect(new DropShadow());
        });
        drStrange.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            drStrange.setEffect(null);
        });

//        electro.setText("Electro");
//        electro.setStyle("-fx-font-size:0");
//        electro.setTextFill(Color.WHITE);
//        Image electroImg = new Image("/Electro.jpg",70,70,false,false);
//        ImageView electroImgView = new ImageView(electroImg);
//        electro.setStyle("-fx-background-color: #0b2370; ");
//        electro.setGraphic(electroImgView);
//        electroImgView.setPreserveRatio(true);
//        electro.setContentDisplay(ContentDisplay.TOP);
        electro.setShape(new Circle(60));
        electro.setMaxSize(120, 120);
        electro.setMinSize(120, 120);
        Image electroImg = new Image("/ElectroButtonFinalNo.png", 120, 120, false, false);
        electro.setBackground(new Background(new BackgroundImage(electroImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[4] = electro;
        electro.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            electro.setEffect(new DropShadow());
        });
        electro.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            electro.setEffect(null);
        });

//        ghostRider.setText("Ghost Rider");
//        ghostRider.setStyle("-fx-font-size:0");
//        ghostRider.setTextFill(Color.WHITE);
//        Image ghostRiderImg = new Image("/Ghost Rider.jpg",70,70,false,false);
//        ImageView ghostRiderImgView = new ImageView(ghostRiderImg);
//        ghostRider.setStyle("-fx-background-color: #0b2370; ");
//        ghostRider.setGraphic(ghostRiderImgView);
//        ghostRiderImgView.setPreserveRatio(true);
//        ghostRider.setContentDisplay(ContentDisplay.TOP);
        ghostRider.setShape(new Circle(60));
        ghostRider.setMaxSize(120, 120);
        ghostRider.setMinSize(120, 120);
        Image ghostRiderImg = new Image("/Ghost RiderButtonFinalNo.png", 120, 120, false, false);
        ghostRider.setBackground(new Background(new BackgroundImage(ghostRiderImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[5] = ghostRider;
        ghostRider.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            ghostRider.setEffect(new DropShadow());
        });
        ghostRider.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            ghostRider.setEffect(null);
        });

//        hela.setText("Hela");
//        hela.setStyle("-fx-font-size:0");
//        hela.setTextFill(Color.WHITE);
//        Image helaImg = new Image("/Hela.jpg",70,70,false,false);
//        ImageView helaImgView = new ImageView(helaImg);
//        hela.setStyle("-fx-background-color: #0b2370; ");
//        hela.setGraphic(helaImgView);
//        helaImgView.setPreserveRatio(true);
//        hela.setContentDisplay(ContentDisplay.TOP);
        hela.setShape(new Circle(60));
        hela.setMaxSize(120, 120);
        hela.setMinSize(120, 120);
        Image helaImg = new Image("/HelaButtonFinalNo.png", 120, 120, false, false);
        hela.setBackground(new Background(new BackgroundImage(helaImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[6] = hela;
        hela.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            hela.setEffect(new DropShadow());
        });
        hela.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            hela.setEffect(null);
        });

//        iceMan.setText("Iceman");
//        iceMan.setStyle("-fx-font-size:0");
//        iceMan.setTextFill(Color.WHITE);
//        Image iceManImg = new Image("/Iceman.jpg",70,70,false,false);
//        ImageView iceManImgView = new ImageView(iceManImg);
//        iceMan.setStyle("-fx-background-color: #0b2370; ");
//        iceMan.setGraphic(iceManImgView);
//        iceManImgView.setPreserveRatio(true);
//        iceMan.setContentDisplay(ContentDisplay.TOP);
        iceMan.setShape(new Circle(60));
        iceMan.setMaxSize(120, 120);
        iceMan.setMinSize(120, 120);
        Image iceManImg = new Image("/IcemanButtonFinalNo.png", 120, 120, false, false);
        iceMan.setBackground(new Background(new BackgroundImage(iceManImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[7] = iceMan;
        iceMan.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            iceMan.setEffect(new DropShadow());
        });
        iceMan.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            iceMan.setEffect(null);
        });

//        ironMan.setText("Ironman");
//        ironMan.setStyle("-fx-font-size:0");
//        ironMan.setTextFill(Color.WHITE);
//        Image ironManImg = new Image("/Ironman.jpg",70,70,false,false);
//        ImageView ironManImgView = new ImageView(ironManImg);
//        ironMan.setStyle("-fx-background-color: #0b2370; ");
//        ironMan.setGraphic(ironManImgView);
//        ironManImgView.setPreserveRatio(true);
//        ironMan.setContentDisplay(ContentDisplay.TOP);
        ironMan.setShape(new Circle(60));
        ironMan.setMaxSize(120, 120);
        ironMan.setMinSize(120, 120);
        Image ironManImg = new Image("/IronmanButtonFinalNo.png", 120, 120, false, false);
        ironMan.setBackground(new Background(new BackgroundImage(ironManImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[8] = ironMan;
        ironMan.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            ironMan.setEffect(new DropShadow());
        });
        ironMan.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            ironMan.setEffect(null);
        });

//        loki.setText("Loki");
//        loki.setStyle("-fx-font-size:0");
//        loki.setTextFill(Color.WHITE);
//        Image lokiImg = new Image("/Loki.jpg",70,70,false,false);
//        ImageView lokiImgView = new ImageView(lokiImg);
//        loki.setStyle("-fx-background-color: #0b2370; ");
//        loki.setGraphic(lokiImgView);
//        lokiImgView.setPreserveRatio(true);
//        loki.setContentDisplay(ContentDisplay.TOP);
        loki.setShape(new Circle(60));
        loki.setMaxSize(120, 120);
        loki.setMinSize(120, 120);
        Image lokiImg = new Image("/LokiButtonFinalNo.png", 120, 120, false, false);
        loki.setBackground(new Background(new BackgroundImage(lokiImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[9] = loki;
        loki.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            loki.setEffect(new DropShadow());
        });
        loki.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            loki.setEffect(null);
        });

//        quickSilver.setText("Quicksilver");
//        quickSilver.setStyle("-fx-font-size:0");
//        quickSilver.setTextFill(Color.WHITE);
//        Image quickSilverImg = new Image("/Quicksilver.jpg",70,70,false,false);
//        ImageView quickSilverImgView = new ImageView(quickSilverImg);
//        quickSilver.setStyle("-fx-background-color: #0b2370; ");
//        quickSilver.setGraphic(quickSilverImgView);
//        quickSilverImgView.setPreserveRatio(true);
//        quickSilver.setContentDisplay(ContentDisplay.TOP);
        quickSilver.setShape(new Circle(60));
        quickSilver.setMaxSize(120, 120);
        quickSilver.setMinSize(120, 120);
        Image quickSilverImg = new Image("/QuicksilverButtonFinalNo.png", 120, 120, false, false);
        quickSilver.setBackground(new Background(new BackgroundImage(quickSilverImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[10] = quickSilver;
        quickSilver.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            quickSilver.setEffect(new DropShadow());
        });
        quickSilver.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            quickSilver.setEffect(null);
        });

//        spiderMan.setText("Spiderman");
//        spiderMan.setStyle("-fx-font-size:0");
//        spiderMan.setTextFill(Color.WHITE);
//        Image spiderManImg = new Image("/Spiderman.jpg",70,70,false,false);
//        ImageView spiderManImgView = new ImageView(spiderManImg);
//        spiderMan.setStyle("-fx-background-color: #0b2370; ");
//        spiderMan.setGraphic(spiderManImgView);
//        spiderManImgView.setPreserveRatio(true);
//        spiderMan.setContentDisplay(ContentDisplay.TOP);
        spiderMan.setShape(new Circle(60));
        spiderMan.setMaxSize(120, 120);
        spiderMan.setMinSize(120, 120);
        Image spiderManImg = new Image("/SpidermanButtonFinalNo.png", 120, 120, false, false);
        spiderMan.setBackground(new Background(new BackgroundImage(spiderManImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[11] = spiderMan;
        spiderMan.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            spiderMan.setEffect(new DropShadow());
        });
        spiderMan.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            spiderMan.setEffect(null);
        });

//        thor.setText("Thor");
//        thor.setStyle("-fx-font-size:0");
//        thor.setTextFill(Color.WHITE);
//        Image thorImg = new Image("/Thor.jpg",70,70,false,false);
//        ImageView thorImgView = new ImageView(thorImg);
//        thor.setStyle("-fx-background-color: #0b2370; ");
//        thor.setGraphic(thorImgView);
//        thorImgView.setPreserveRatio(true);
//        thor.setContentDisplay(ContentDisplay.TOP);
        thor.setShape(new Circle(60));
        thor.setMaxSize(120, 120);
        thor.setMinSize(120, 120);
        Image thorImg = new Image("/ThorButtonFinalNo.png", 120, 120, false, false);
        thor.setBackground(new Background(new BackgroundImage(thorImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[12] = thor;
        thor.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            thor.setEffect(new DropShadow());
        });
        thor.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            thor.setEffect(null);
        });

//        venom.setText("Venom");
//        venom.setStyle("-fx-font-size:0");
//        venom.setTextFill(Color.WHITE);
//        Image venomImg = new Image("/Venom.jpg",70,70,false,false);
//        ImageView venomImgView = new ImageView(venomImg);
//        venom.setStyle("-fx-background-color: #0b2370; ");
//        venom.setGraphic(venomImgView);
//        venomImgView.setPreserveRatio(true);
//        venom.setContentDisplay(ContentDisplay.TOP);
        venom.setShape(new Circle(60));
        venom.setMaxSize(120, 120);
        venom.setMinSize(120, 120);
        Image venomImg = new Image("/VenomButtonFinalNo.png", 120, 120, false, false);
        venom.setBackground(new Background(new BackgroundImage(venomImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[13] = venom;
        venom.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            venom.setEffect(new DropShadow());
        });
        venom.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            venom.setEffect(null);
        });

//        yellowJacket.setText("Yellow Jacket");
//        yellowJacket.setStyle("-fx-font-size:0");
//        yellowJacket.setTextFill(Color.WHITE);
//        Image yellowJacketImg = new Image("/Yellow Jacket.jpg",70,70,false,false);
//        ImageView yellowJacketImgView = new ImageView(yellowJacketImg);
//        yellowJacket.setStyle("-fx-background-color: #0b2370; ");
//        yellowJacket.setGraphic(yellowJacketImgView);
//        yellowJacketImgView.setPreserveRatio(true);
//        yellowJacket.setContentDisplay(ContentDisplay.TOP);
        yellowJacket.setShape(new Circle(60));
        yellowJacket.setMaxSize(120, 120);
        yellowJacket.setMinSize(120, 120);
        Image yellowJacketImg = new Image("/Yellow JacketButtonFinalNo.png", 120, 120, false, false);
        yellowJacket.setBackground(new Background(new BackgroundImage(yellowJacketImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arr[14] = yellowJacket;
        yellowJacket.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            yellowJacket.setEffect(new DropShadow());
        });
        yellowJacket.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            yellowJacket.setEffect(null);
        });

        v.getChildren().addAll(hulk, captainAmerica, deadPool, drStrange, electro, ghostRider, hela, iceMan);
        h.getChildren().addAll(ironMan, loki, quickSilver, spiderMan, thor, venom, yellowJacket);


        Image i3 = new Image("/ConfirmButtonLOL.png", 105, 50, false, false);
        String path = "/Fighting_Spirit_2_ital.ttf";
        Button confirm = new Button();
        confirm.setText("Confirm");
        confirm.setTextAlignment(TextAlignment.JUSTIFY);
        confirm.setFont(Font.loadFont(ChampionSelection.class.getResourceAsStream(path), 15));
        confirm.setStyle("-fx-text-fill: white");
        confirm.setBackground(new Background(new BackgroundImage(i3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        confirm.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
            confirm.setEffect(new DropShadow());
        });
        confirm.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
            confirm.setEffect(null);
        });



        VBox ver = new VBox();

        confirm.setOnAction(e -> {
            if(playerOne.size() < 3){

            }else {
                disableChampions();
                if(first){
                    try {
                        Controller.chooseChampions(playerOne, true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    first = false;
                }else{
                    try {
                        Controller.chooseChampions(playerOne, false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    leaderPlayerOne();
                }
                playerOne = new ArrayList<>();
            }
        });
        v.getChildren().add(confirm);
        confirm.setAlignment(Pos.BOTTOM_RIGHT);
        ver.prefHeightProperty().bind(root.heightProperty());
        ver.prefWidthProperty().bind(root.widthProperty());
        //root.setPadding(new Insets(20, 20, 20, 20));
        ver.getChildren().addAll(v, h);
        ver.setAlignment(Pos.BOTTOM_RIGHT);
        //ver.setTranslateX(350);
        root.setCenter(ver);
        BorderPane.setAlignment(ver, Pos.CENTER);


        hulk.setOnAction(e -> {
            if(!hulk.isSelected()){
                playerOne.remove("Hulk");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(hulk.isSelected()){
                playerOne.add("Hulk");
            }
            getInfo();
        });
        captainAmerica.setOnAction(e -> {
            if(!captainAmerica.isSelected()){
                playerOne.remove("Captain America");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(captainAmerica.isSelected()){
                playerOne.add("Captain America");
            }
            getInfo();
        });
        deadPool.setOnAction(e -> {
            if(!deadPool.isSelected()){
                playerOne.remove("Deadpool");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(deadPool.isSelected()){
                playerOne.add("Deadpool");
            }
            getInfo();
        });
        drStrange.setOnAction(e -> {
            if(!drStrange.isSelected()){
                playerOne.remove("Dr Strange");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(drStrange.isSelected()){
                playerOne.add("Dr Strange");
            }
            getInfo();
        });
        electro.setOnAction(e -> {
            if(!electro.isSelected()){
                playerOne.remove("Electro");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(electro.isSelected()){
                playerOne.add("Electro");
            }
            getInfo();
        });
        ghostRider.setOnAction(e -> {
            if(!ghostRider.isSelected()){
                playerOne.remove("Ghost Rider");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(ghostRider.isSelected()){
                playerOne.add("Ghost Rider");
            }
            getInfo();
        });
        hela.setOnAction(e -> {
            if(!hela.isSelected()){
                playerOne.remove("Hela");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(hela.isSelected()){
                playerOne.add("Hela");
            }
            getInfo();
        });
        ironMan.setOnAction(e -> {
            if(!ironMan.isSelected()){
                playerOne.remove("Ironman");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(ironMan.isSelected()){
                playerOne.add("Ironman");
            }
            getInfo();
        });
        iceMan.setOnAction(e -> {
            if(!iceMan.isSelected()){
                playerOne.remove("Iceman");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(iceMan.isSelected()){
                playerOne.add("Iceman");
            }
            getInfo();
        });
        loki.setOnAction(e -> {
            if(!loki.isSelected()){
                playerOne.remove("Loki");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(loki.isSelected()){
                playerOne.add("Loki");
            }
            getInfo();
        });
        quickSilver.setOnAction(e -> {
            if(!quickSilver.isSelected()){
                playerOne.remove("Quicksilver");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(quickSilver.isSelected()){
                playerOne.add("Quicksilver");
            }
            getInfo();
        });
        spiderMan.setOnAction(e -> {
            if(!spiderMan.isSelected()){
                playerOne.remove("Spiderman");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(spiderMan.isSelected()){
                playerOne.add("Spiderman");
            }
            getInfo();
        });
        thor.setOnAction(e -> {
            if(!thor.isSelected()){
                playerOne.remove("Thor");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(thor.isSelected()){
                playerOne.add("Thor");
            }
           getInfo();
        });
        venom.setOnAction(e -> {
            if(!venom.isSelected()){
                playerOne.remove("Venom");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(venom.isSelected()){
                playerOne.add("Venom");
            }
            getInfo();
        });
        yellowJacket.setOnAction(e -> {
            if(!yellowJacket.isSelected()){
                playerOne.remove("Yellow Jacket");
            }
            else if(playerOne.size() == 3){
                //Do nothing
            }else if(yellowJacket.isSelected()){
                playerOne.add("Yellow Jacket");
            }
            getInfo();
        });


        championSelectionStage.setScene(s);
        championSelectionStage.show();
    }

}
