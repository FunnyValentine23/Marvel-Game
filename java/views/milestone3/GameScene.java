package views.milestone3;


import engine.PriorityQueue;
import exceptions.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.effects.Effect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import java.awt.Point;


import java.util.ArrayList;


public class GameScene {

    static BorderPane borderPane;
    static Scene s;
    static Stage window;
    static GridPane gridPane;
    static GameScene s1 = new GameScene();


    public void displayStats(){
        // Group for the two rectangles
        ArrayList<Integer> cur = Controller.getdeets();
        Group healthBargroup = new Group();
        Group manaBargroup = new Group();

        double manawidth = 430.0;
        double manapercentage = (double) cur.get(2)/(double) cur.get(3);
        double healthwidth = 430.0;
        double healthpercentage = (double)cur.get(1)/(double)cur.get(0);

// Your background image rectangle
        Rectangle healthbackground = new Rectangle(0, 0, healthwidth, 30);
        healthbackground.setFill(Color.GREEN);
        healthbackground.setStroke(Color.rgb(1,1,1,0.88));
        healthbackground.setStrokeWidth(0.88);

        Rectangle manabackground = new Rectangle(0, 0, healthwidth, 30);
        manabackground.setFill(Color.BLUE);
        manabackground.setStroke(Color.rgb(1,1,1,0.88));
        manabackground.setStrokeWidth(0.88);

// Second rectangle to cover parts of the background
        Rectangle healthrect = new Rectangle(healthpercentage * healthwidth, 0, (1 - healthpercentage) * healthwidth, 30);
        healthrect.setFill(Color.BLACK);
        healthrect.setStroke(Color.rgb(1,1,1,0.88));
        healthrect.setStrokeWidth(0.88);

        Rectangle manarect = new Rectangle(manapercentage * manawidth, 0, (1 - manapercentage) * manawidth, 30);
        manarect.setFill(Color.BLACK);
        manarect.setStroke(Color.rgb(1,1,1,0.88));
        manarect.setStrokeWidth(0.88);

// Group the two rectangles together
        healthBargroup.getChildren().add(healthbackground);
        healthBargroup.getChildren().add(healthrect);

        manaBargroup.getChildren().add(manabackground);
        manaBargroup.getChildren().add(manarect);

// ... show the group

        String path = "/Fighting_Spirit_2_ital.ttf";
        VBox vleft = new VBox(20);
        Label ability1label = new Label();
        Tooltip tooltip = new Tooltip("Gay");

        ImageView ability1imgback = new ImageView(new Image("/fp1_03_copy1.png",400,100,false,false));

        Text text = new Text("Ability 1: " + Controller.g.getCurrentChampion().getAbilities().get(0).getName()+ "\n"
                + "   PRESS Q TO USE" + '\n');

        text.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        text.setFill(Color.WHITE);
        ability1label.setGraphic(ability1imgback);

        ability1label.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
           window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
          VBox pane = new VBox(20);
           Scene scene = new Scene(pane,400,400);
           Label label = new Label(Controller.getdeetsAbility1(Controller.g.getCurrentChampion().getName()));
           pane.getChildren().addAll(label,b);
           window.setScene(scene);
           window.showAndWait();
       });
//       ability1label.addEventHandler(MouseEvent.MOUSE_EXITED,e ->{
//            window9.close();
//       });

//        Tooltip.install(ability1label,tooltip);
//        Tooltip.install(text,tooltip);
//        ability1label.setTooltip(tooltip);
//        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);

        ImageView ability2imgback = new ImageView(new Image("/fp1_03_copy1.png",400,100,false,false));
        Text text2 = new Text("Ability 2: " + Controller.g.getCurrentChampion().getAbilities().get(1).getName()+ "\n"
                + "         PRESS E TO USE");
        text2.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        text2.setFill(Color.WHITE);
        ability2imgback.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,400,400);
            Label label = new Label(Controller.getdeetsAbility2(Controller.g.getCurrentChampion().getName()));
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });

        ImageView ability3imgback = new ImageView(new Image("/fp1_03_copy1.png",400,100,false,false));
        Text text3 = new Text("Ability 3: " + Controller.g.getCurrentChampion().getAbilities().get(2).getName()+ "\n"
                + "         PRESS R TO USE" );
        text3.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        text3.setFill(Color.WHITE);
        ability3imgback.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,400,400);
            Label label = new Label(Controller.getdeetsAbility3(Controller.g.getCurrentChampion().getName()));
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });
        ImageView leaderAbilityimgback = new ImageView(new Image("/fp1_03_copy1.png",400,100,false,false));
        Text leaderAbility = new Text();
        if(Controller.g.getFirstPlayer().getTeam().contains(Controller.g.getCurrentChampion())){
           leaderAbility.setText("Press X to Use First Player Leader's Ability");
        }
        else{
            leaderAbility.setText("Press X to Use Second Player Leader's Ability");
        }
        leaderAbility.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        leaderAbility.setFill(Color.WHITE);
        leaderAbilityimgback.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,500,100);
            Label label = new Label(Controller.leaderAbilityInfo());
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });
        ImageView punchAbility = new ImageView(new Image("/fp1_03_copy1.png",400,100,false,false));
        Text punchAbilitytext = new Text("Press C to Use Punch Ability");
        punchAbilitytext.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
       punchAbilitytext.setFill(Color.WHITE);
       punchAbility.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,500,200);
            Label label = new Label("Gain a SINGLETARGET damaging ability called (Punch) " +"\n"+
                    "costs: 0, damage: 50, cooldown: 1, range: 1, actions: 1");
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });
        String p1team = "";
        for(int i = 0; i < Controller.g.getFirstPlayer().getTeam().size(); ++i){
            if(Controller.g.getFirstPlayer().getTeam().get(i).getName().equals(Controller.g.getFirstPlayer().getLeader().getName()))
            p1team += "Champion #" + (i+1) + " "+Controller.g.getFirstPlayer().getTeam().get(i).getName() + " (Current Leader)"+'\n';
            else
                p1team += "Champion #" + (i+1) +" "+ Controller.g.getFirstPlayer().getTeam().get(i).getName() + '\n';

        }


        String p2team = "";
        for(int i = 0; i < Controller.g.getSecondPlayer().getTeam().size(); ++i){
            if(Controller.g.getSecondPlayer().getTeam().get(i).getName().equals(Controller.g.getSecondPlayer().getLeader().getName()))
            p2team += "Champion #" + (i+1) +" " +Controller.g.getSecondPlayer().getTeam().get(i).getName() +" (Current Leader)"+ '\n';
            else
                p2team += "Champion #" + (i+1) + " " +Controller.g.getSecondPlayer().getTeam().get(i).getName() + '\n';
        }


        ImageView playerOne = new ImageView(new Image("/fp1_03_copy1.png",500,100,false,false));
        Text playerOneName = new Text(""+Controller.g.getFirstPlayer().getName()+"\n"+p1team);
        playerOneName.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        playerOneName.setFill(Color.WHITE);
        playerOne.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,750,750);
            Label label = new Label(Controller.team1Info(Controller.g.getFirstPlayer().getName()));
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });


        ImageView playerTwo = new ImageView(new Image("/fp1_03_copy1.png",500,100,false,false));
        Text playerTwoName = new Text(""+Controller.g.getSecondPlayer().getName()+"\n"+p2team);
        playerTwoName.setFont(Font.loadFont(getClass().getResourceAsStream(path), 15));
        playerTwoName.setFill(Color.WHITE);
        playerTwo.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.initModality(Modality.APPLICATION_MODAL);
            Button b = new Button("Exit");
            b.setOnAction(f -> {
                window.close();
            });
            VBox pane = new VBox(20);
            Scene scene = new Scene(pane,750,750);
            Label label = new Label(Controller.team2Info(Controller.g.getSecondPlayer().getName()));
            pane.getChildren().addAll(label,b);
            window.setScene(scene);
            window.showAndWait();
        });

        ImageView champInfo = new ImageView(new Image("/fp1_03_copy1.png",300,400,false,false));
        Text champInfoText = new Text("" + Controller.championInfo() + "");
       champInfoText.setFont(Font.loadFont(getClass().getResourceAsStream(path), 14));
       champInfoText.setFill(Color.WHITE);

        VBox vright = new VBox(20);
        StackPane layout = new StackPane();
        StackPane layout2 = new StackPane();
        StackPane layout3 = new StackPane();
        StackPane layout4 = new StackPane();
        StackPane layout5 = new StackPane();
        StackPane team1 = new StackPane();
        StackPane team2 = new StackPane();
        StackPane champInfoPlace = new StackPane();

        team1.getChildren().addAll(playerOne,playerOneName);
        team2.getChildren().addAll(playerTwo,playerTwoName);
        champInfoPlace.getChildren().addAll(champInfo,champInfoText);

        HBox teams = new HBox(530);
        layout.getChildren().addAll(ability1label,text);
        layout2.getChildren().addAll(ability2imgback,text2);
        layout3.getChildren().addAll(ability3imgback,text3);
        layout4.getChildren().addAll(leaderAbilityimgback,leaderAbility);
        layout5.getChildren().addAll(punchAbility,punchAbilitytext);
        teams.getChildren().addAll(team1,team2);

        vright.getChildren().addAll(layout,layout2,layout3,layout4,layout5);

        vleft.getChildren().addAll(healthBargroup , manaBargroup,champInfoPlace);
        vleft.setMaxWidth(200);
        borderPane.setLeft(vleft);
        borderPane.setRight(vright);
        borderPane.setPadding(new Insets(20));
        borderPane.setTop(teams);

    }

    static ArrayList<Hazem> champions = new ArrayList<>();
    static Hazem cur = null;
    public static void coverUpdateHP() {
        ArrayList<Label> labelsCovers = new ArrayList<>();
        ArrayList<Cover> c = Controller.getCoverLoc();
        for (int i = 0; i < c.size(); ++i) {
            labelsCovers.add(new Label());
            StackPane stackPane = new StackPane();
            String t = "/Cover" + covertype + ".png";
            Image i1 = new Image(t, 100, 100, false, false);
            ImageView imageView = new ImageView(i1);
            labelsCovers.get(i).setGraphic(imageView);
            Text text = new Text("" + c.get(i).getCurrentHP() + "");
            String path = "/Fighting_Spirit_2_ital.ttf";
            text.setFont(Font.loadFont(GameScene.class.getResourceAsStream(path), 20));
            text.setFill(Color.WHITE);
            stackPane.getChildren().addAll(labelsCovers.get(i), text);
            GridPane.setConstraints(stackPane, c.get(i).getLocation().y, c.get(i).getLocation().x);
            gridPane.getChildren().add(stackPane);
        }
    }
    public static void update(){
        for (Hazem champion : champions) {
            if (champion.getName().equals(Controller.g.getCurrentChampion().getName())) {
                cur = champion;
                break;
            }
        }
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.D){
                try{
                    Controller.g.move(Direction.RIGHT);
                    cur.moveRIGHT();
                } catch (UnallowedMovementException e) {
                    showFlashMessage("Cannot Move To This Cell");
                } catch (NotEnoughResourcesException e) {
                    showFlashMessage("Not Enough Action Points");
                }
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.W){
                try{
                    Controller.g.move(Direction.DOWN);
                    cur.moveUP();
                } catch (UnallowedMovementException e) {
                    showFlashMessage("Cannot Move To This Cell");
                } catch (NotEnoughResourcesException e) {
                    showFlashMessage("Not Enough Action Points");
                }
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.A){
                try{
                    Controller.g.move(Direction.LEFT);
                    cur.moveLEFT();
                } catch (UnallowedMovementException e) {
                    showFlashMessage("Cannot Move To This Cell");
                } catch (NotEnoughResourcesException e) {
                    showFlashMessage("Not Enough Action Points");
                }
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.S){
                try{
                    Controller.g.move(Direction.UP);
                    cur.moveDOWN();
                } catch (UnallowedMovementException e) {
                    showFlashMessage("Cannot Move To This Cell");
                } catch (NotEnoughResourcesException e) {
                    showFlashMessage("Not Enough Action Points");
                }
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.Q){
                cur.Q();
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.E){
                cur.E();
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.R){
                cur.R();
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.X){
                try{
                    Controller.g.useLeaderAbility();
                } catch (LeaderAbilityAlreadyUsedException e) {
                    showFlashMessage("Leader Ability Already Used");
                } catch (LeaderNotCurrentException e) {
                    showFlashMessage("Current Champion is not The Leader");
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.F){
                cur.attack();
            }
        });
        s.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.C){
                if(Controller.g.getCurrentChampion().getAbilities().size() == 4){
                    displaySingle();
                    try {
                        Controller.g.castAbility(Controller.g.getCurrentChampion().getAbilities().get(3), y, x);
                    } catch (AbilityUseException e) {
                        showFlashMessage("Cannot Cast Abiltiy");
                    } catch (InvalidTargetException e) {
                        showFlashMessage("Invalid Target");
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    } catch (NotEnoughResourcesException e) {
                        showFlashMessage("Not Enough Action Points");
                    }
                    x = 10;
                    y = 10;
                }
                else{
                    showFlashMessage("Champion Not Disarmed");
                }
            }
        });
    }
    public static void displaySingle(){
        windowd = new Stage();
        windowd.initStyle(StageStyle.UNDECORATED);
        windowd.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        gridPaned = new GridPane();
        initialize();

        borderPane.setTop(new Label("Click On Cell To Apply Ability On"));

        gridPaned.setBackground(new Background(new BackgroundImage(GameScene.img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        //gridPane.setStyle("-fx-background-insets: 0, 1");
        borderPane.setCenter(gridPaned);

        gridPaned.setPrefHeight(500);
        gridPaned.setPrefWidth(500);

        windowd.setScene(new Scene(borderPane));
        windowd.showAndWait();
    }
    static GridPane gridPaned;
    public static void initialize() {
        int numCols = 5;
        int numRows = 5;

        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            gridPaned.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            gridPaned.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }
    static Stage windowd;

    private static void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            x = colIndex;
            y = rowIndex;
            System.out.println(x+" "+y);
            if(x != 10 && y != 10){
                windowd.close();
            }
        });
        gridPaned.add(pane, colIndex, rowIndex);
    }
    static int x = 10, y = 10;
    static Image img;
    //static Thor iron;
    public static void display(){
        Controller.place();
        window = new Stage();
        window.setMaximized(true);
        borderPane = new BorderPane();
        window.initStyle(StageStyle.UNDECORATED);
        s = new Scene(borderPane);
        window.initStyle(StageStyle.TRANSPARENT);
        s.setFill(Color.TRANSPARENT);
        ImageView imageview = new ImageView("/133256.jpg");
        imageview.fitWidthProperty().bind(s.widthProperty());
        imageview.fitHeightProperty().bind(s.heightProperty());
        imageview.setPreserveRatio(true);
        borderPane.getChildren().addAll(imageview);

        gridPane = new GridPane();

        borderPane.setCenter(gridPane);

        gridPane.setPrefHeight(500);
        gridPane.setPrefWidth(500);

        for(int i = 0; i < 5; ++i){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(columnConstraints);
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(100);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        gridPane.setAlignment(Pos.CENTER);

        int r = (int)(Math.random()*14)+1;
        backgroundtype = r;
        String name = "/Background" + r + ".jpg";
        img = new Image(name);
        gridPane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        r = (int)(Math.random()*6)+1;

        Label l = new Label();
        //Image i1 = new Image("/Dr Strange.jpg", 100, 100,false,false);

        //ImageView imgView= new ImageView(i1);
        //l.setGraphic(imgView);

        GridPane.setConstraints(l,2,2);

        //ArrayList<Controller.ChampNameLoc> a = Controller.getChampNameLoc();
        ArrayList<Label> labelsChampions = new ArrayList<>();
        //Image iii = new Image("/ThorSprite.png");
        //iron = new Thor(new ImageView(iii));
        //gridPane.getChildren().add(iron);
//        ImageViewSprite anim = new ImageViewSprite(new ImageView(iron),iron , 8, 1, 7, 3, 4, 24);
//        anim.start();
        champsize = Controller.g.getFirstPlayer().getTeam().size() + Controller.g.getSecondPlayer().getTeam().size();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    update();
                    s1.displayStats();
                    coverUpdateHP();
                    if(champsize > Controller.g.getFirstPlayer().getTeam().size() + Controller.g.getSecondPlayer().getTeam().size()){
                        updateDeadBoard();
                        --champsize;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        labelsChampions.add(new Label());
        for(int i = 0; i < Controller.g.getFirstPlayer().getTeam().size(); ++i){
            Point p = Controller.g.getFirstPlayer().getTeam().get(i).getLocation();
            //GridPane.setConstraints((Node) getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
            //champions.add((GridPane)getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()));
            gridPane.add((Node) getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
        }

        for(int i = 0; i < Controller.g.getSecondPlayer().getTeam().size(); ++i){
            Point p = Controller.g.getSecondPlayer().getTeam().get(i).getLocation();
            //GridPane.setConstraints((Node) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
            //champions.add((GridPane) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()));
            gridPane.add((Node) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
        }
        ArrayList<Label> labelsCovers = new ArrayList<>();
        ArrayList<Cover> c = Controller.getCoverLoc();
        covertype = r;
        for(int i = 0; i < c.size(); ++i){
            labelsCovers.add(new Label());
            StackPane stackPane = new StackPane();
            String t = "/Cover" + r + ".png";
            Image i1 = new Image(t, 100, 100,false,false);
            ImageView imageView = new ImageView(i1);
            labelsCovers.get(i).setGraphic(imageView);
            Text text = new Text(""+c.get(i).getCurrentHP()+"");
            String path = "/Fighting_Spirit_2_ital.ttf";
            text.setFont(Font.loadFont(GameScene.class.getResourceAsStream(path), 20));
            text.setFill(Color.WHITE);
            stackPane.getChildren().addAll(labelsCovers.get(i),text);
            GridPane.setConstraints(stackPane, c.get(i).getLocation().y, c.get(i).getLocation().x);
            gridPane.getChildren().add(stackPane);
        }




        s1.displayStats();
        turn(bottomBorderPane);

        //System.out.println(gridPane.getChildren().size());

        String path = "/Fighting_Spirit_2_ital.ttf";
        ImageView endturn = new ImageView(new Image("/fp1_03_copy2.png",150,70,false,false));
        Text endturntext = new Text("ENDTURN");


        endturntext.setFont(Font.loadFont(GameScene.class.getResourceAsStream(path), 15));
        endturntext.setOnMouseClicked(e -> {
            bottomBorderPane = new HBox(215);
            Controller.g.endTurn();
            turn(bottomBorderPane);
            s1.displayStats();
            endGame();
        });

        endturn.setOnMouseClicked(e -> {
            bottomBorderPane = new HBox(215);
            Controller.g.endTurn();
            turn(bottomBorderPane);
            s1.displayStats();
            endGame();
        });

        endturnlayout.getChildren().addAll(endturn,endturntext);


//        Rectangle hp = new Rectangle(100.0, 10.0);
//        DoubleProperty p = new SimpleDoubleProperty(1);
//        DoubleBinding b = p.multiply(hp.widthProperty().get());
//        borderPane.getChildren().add(hp);

        window.setScene(s);
        window.show();
    }
    public static Object getChampions(String name){
        if(name.equals("Hulk")){
            Hulk h = new Hulk(new ImageView("/HulkSprite.png"));
            champions.add(h);
            return h;
        }
        if(name.equals("Captain America")){
            CaptainAmerica c = new CaptainAmerica(new ImageView("/CaptainAmericaSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Deadpool")){
            Deadpool c =  new Deadpool(new ImageView("/DeadpoolSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Dr Strange")){
            DrStrange c =  new DrStrange(new ImageView("/Dr StrangeSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Electro")){
            Electro c =  new Electro(new ImageView("/ElectroSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Ghost Rider")){
            GhostRider c = new GhostRider(new ImageView("/Ghost RiderSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Hela")){
            Hela c = new Hela(new ImageView("/HelaSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Iceman")){
            Iceman c = new Iceman(new ImageView("/IcemanSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Ironman")){
            Ironman c = new Ironman(new ImageView("/IronmanSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Loki")){
            Loki c = new Loki(new ImageView("/LokiSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Quicksilver")){
            Quicksilver c = new Quicksilver(new ImageView("/QuicksilverSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Spiderman")){
            Spiderman c = new Spiderman(new ImageView("/SpidermanSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Thor")){
            Thor c =  new Thor(new ImageView("/ThorSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Venom")){
            Venom c =  new Venom(new ImageView("/VenomSprite.png"));
            champions.add(c);
            return c;
        }
        if(name.equals("Yellow Jacket")){
            YellowJacket c =  new YellowJacket(new ImageView("/Yellow JacketSprite.png"));
            champions.add(c);
            return c;
        }
        return null;
    }
    static int covertype = 0;
    static int champsize = 0;
    static int backgroundtype = 0;

    public static void updateDeadBoard(){
        gridPane = new GridPane();
        champions = new ArrayList<>();
        String name = "/Background" + backgroundtype + ".jpg";
        img = new Image(name);
        gridPane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        borderPane.setCenter(gridPane);

        gridPane.setPrefHeight(500);
        gridPane.setPrefWidth(500);

        ArrayList<Label> labelsChampions = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(columnConstraints);
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(100);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        gridPane.setAlignment(Pos.CENTER);
        labelsChampions.add(new Label());
        for(int i = 0; i < Controller.g.getFirstPlayer().getTeam().size(); ++i){
            Point p = Controller.g.getFirstPlayer().getTeam().get(i).getLocation();
            //GridPane.setConstraints((Node) getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
            //champions.add((GridPane)getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()));
            gridPane.add((Node) getChampions(Controller.g.getFirstPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
        }

        for(int i = 0; i < Controller.g.getSecondPlayer().getTeam().size(); ++i){
            Point p = Controller.g.getSecondPlayer().getTeam().get(i).getLocation();
            //GridPane.setConstraints((Node) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
            //champions.add((GridPane) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()));
            gridPane.add((Node) getChampions(Controller.g.getSecondPlayer().getTeam().get(i).getName()), (int)p.getY(), (int)p.getX());
        }
        ArrayList<Label> labelsCovers = new ArrayList<>();
        ArrayList<Cover> c = Controller.getCoverLoc();
        for(int i = 0; i < c.size(); ++i){
            labelsCovers.add(new Label());
            StackPane stackPane = new StackPane();
            String t = "/Cover" + covertype + ".png";
            Image i1 = new Image(t, 100, 100,false,false);
            ImageView imageView = new ImageView(i1);
            labelsCovers.get(i).setGraphic(imageView);
            Text text = new Text(""+c.get(i).getCurrentHP()+"");
            String path = "/Fighting_Spirit_2_ital.ttf";
            text.setFont(Font.loadFont(GameScene.class.getResourceAsStream(path), 15));
            text.setFill(Color.WHITE);
            stackPane.getChildren().addAll(labelsCovers.get(i),text);
            GridPane.setConstraints(stackPane, c.get(i).getLocation().y, c.get(i).getLocation().x);
            gridPane.getChildren().add(stackPane);
        }
    }

    static StackPane endturnlayout = new StackPane();
    public static void turn(HBox bottomBorderPane){
        HBox t = new HBox(10);
        PriorityQueue q = new PriorityQueue(6);
        while(!Controller.g.getTurnOrder().isEmpty()){
            Image i = new Image("/"+((Champion)Controller.g.getTurnOrder().peekMin()).getName()+"TurnOrder.png", 70, 70, false, false);
            t.getChildren().add(new ImageView(i));
            q.insert(Controller.g.getTurnOrder().remove());
        }
        System.out.println(Controller.g.getTurnOrder().size());
        while(!q.isEmpty()){
            Controller.g.getTurnOrder().insert(q.remove());
        }
        bottomBorderPane.getChildren().add(t);
        HBox endTurnHBox = new HBox(0);

        endTurnHBox.setAlignment(Pos.BOTTOM_LEFT);
        endTurnHBox.getChildren().addAll(endturnlayout);
        bottomBorderPane.getChildren().addAll(endTurnHBox);
        borderPane.setBottom(bottomBorderPane);
    }
    static HBox bottomBorderPane = new HBox(215);

    static boolean xResized = false;
    static boolean yResized = false;
    public static void showFlashMessage(String message) {

        Stage w = new Stage();
        w.initStyle(StageStyle.TRANSPARENT);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        Label label = new Label(message);
        label.setFont(Font.font("Aguda", FontWeight.EXTRA_BOLD, 35));
        label.setTextFill(Color.DARKRED);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(500);
        layout.getChildren().add(label);
        layout.setPadding(new Insets(3));
        layout.setStyle("-fx-background-color: transparent;");
        w.setScene(new Scene(layout, Color.TRANSPARENT));
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
                new KeyFrame(Duration.ZERO, evt -> w.show(), new KeyValue(layout.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(500), new KeyValue(layout.opacityProperty(), 1.0)),
                new KeyFrame(Duration.millis(1700), new KeyValue(layout.opacityProperty(), 0.2))
        );
        timeline.setOnFinished(evt -> w.close());


        timeline.play();

    }
    public static void endGame(){
        if(Controller.g.getFirstPlayer().getTeam().size() == 0|| Controller.g.getSecondPlayer().getTeam().size() == 0){
            EndingScreen.display();
            window.close();
        }
    }

}
