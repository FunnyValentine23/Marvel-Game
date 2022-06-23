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
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class quiz1 extends Application {
    static GridPane layoutGridPane;
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
    public void mainMenu(Stage mainMenuStage1) {
        Player playerOne = new Player("Qotoz");
        Player playerTwo = new Player("3anteel el Gam3a");
        game = new Game(playerOne, playerTwo);
        try {
            game.loadAbilities("Abilities.csv");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        layoutGridPane = new GridPane();
        scene = new Scene(layoutGridPane,300,300);
        int  i = (int)(Math.random()*game.getAvailableAbilities().size());
        abilityName = new Button(game.getAvailableAbilities().get(i).getName());
        abilityName.setAlignment(Pos.TOP_LEFT);
        abilityType = new Button();
        abilityType.setAlignment(Pos.TOP_RIGHT);
        counter = new Button();
        counter.setAlignment(Pos.CENTER_LEFT);
        nextButton = new Button("Next");
        layoutGridPane.add(abilityName,0,0);
        layoutGridPane.add(abilityType,0,1);
        layoutGridPane.add(counter,1,0);
        layoutGridPane.add(nextButton,1,1);


        nextButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(e.getSource()==nextButton){

                    int  i = (int)(Math.random()*game.getAvailableAbilities().size());

                    abilityName.setText(game.getAvailableAbilities().get(i).getName());
                    if(game.getAvailableAbilities().get(i) instanceof HealingAbility) {

                        abilityType.setText("HealingAbility");
                    }
                    else if(game.getAvailableAbilities().get(i) instanceof DamagingAbility) {

                        abilityType.setText("DamagingAbility");
                    }
                    else if(game.getAvailableAbilities().get(i) instanceof CrowdControlAbility) {

                        abilityType.setText("CrowdControlAbility");
                    }

                    counter.setText(""+i+"");


                }
            }
        });
        mainMenuStage1.setScene(scene);
        mainMenuStage1.show();

    }

}