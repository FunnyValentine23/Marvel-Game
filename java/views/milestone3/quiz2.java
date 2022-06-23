package views.milestone3;



import engine.Game;
import engine.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class quiz2 extends Application {

    static Scene scene;
    static Button abilityName;
    static Button abilityType;
    static Button counter;
    static Button nextButton;
    static Game game;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage1) throws URISyntaxException {
        mainMenu(primaryStage1);
    }
    public void mainMenu(Stage mainMenuStage1)  {
        Player playerOne = new Player("Qotoz");
        Player playerTwo = new Player("3anteel el Gam3a");
        game = new Game(playerOne, playerTwo);
        try {
            game.loadAbilities("Abilities.csv");
            game.loadChampions("Champions.csv");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        VBox layoutGridPane = new VBox(10);
        scene = new Scene(layoutGridPane,300,300);
        int  i = (int)(Math.random()*game.getAvailableChampions().size());
        int j = (int)(Math.random()*game.getAvailableChampions().size());
        int r = (int)(Math.random()*game.getAvailableChampions().size());
        abilityName = new Button(""+game.getAvailableChampions().get(i).getName()+"  "+game.getAvailableChampions().get(i).getCurrentHP());
        abilityName.setAlignment(Pos.TOP_LEFT);
        abilityType = new Button(""+game.getAvailableChampions().get(j).getName()+"  "+game.getAvailableChampions().get(j).getCurrentHP());
        abilityType.setAlignment(Pos.TOP_RIGHT);
        counter = new Button(""+game.getAvailableChampions().get(r).getName()+"  "+game.getAvailableChampions().get(r).getCurrentHP());
        counter.setAlignment(Pos.CENTER_LEFT);
        layoutGridPane.getChildren().addAll(abilityName,abilityType,counter);
        counter.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(e.getSource()==counter){
                    game.getAvailableChampions().get(r).setCurrentHP( game.getAvailableChampions().get(r).getCurrentHP()-500);
                    counter.setText(""+game.getAvailableChampions().get(r).getName()+"  "+game.getAvailableChampions().get(r).getCurrentHP());
                }
            }
        });
        abilityType.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(e.getSource()==abilityType){
                    game.getAvailableChampions().get(j).setCurrentHP( game.getAvailableChampions().get(j).getCurrentHP()-500);
                    abilityType.setText(""+game.getAvailableChampions().get(j).getName()+"  "+game.getAvailableChampions().get(j).getCurrentHP());
                }
            }
        });


        abilityName.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(e.getSource()==abilityName){
                    game.getAvailableChampions().get(i).setCurrentHP( game.getAvailableChampions().get(i).getCurrentHP()-500);
                    abilityName.setText(""+game.getAvailableChampions().get(i).getName()+"  "+game.getAvailableChampions().get(i).getCurrentHP());
                }
            }
        });
        mainMenuStage1.setScene(scene);
        mainMenuStage1.show();

    }

}