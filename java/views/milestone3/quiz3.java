package views.milestone3;

import engine.Game;
import engine.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class quiz3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static int clicks = 0;
    static int r = (int)(Math.random()*15);
    @Override
    public void start(Stage stage) throws Exception {
        Button component1 = new Button();
        r = (int)(Math.random()*15);
        stage.setTitle("Quiz");
        Controller.player1 = new Player("LOL");
        Controller.player2 = new Player("LMAO");
        TextField component2 = new TextField();
        TextField component3 = new TextField();
        TextField component4 = new TextField();
        component1.setPrefWidth(600);
        component2.setPrefSize(400, 200);
        component3.setPrefSize(400, 200);
        component4.setPrefSize(400, 200);
        Controller.initGame();
        component1.setText(Game.getAvailableChampions().get(r).getName()+"");
        component1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(e.getSource()==component1){
                    ++clicks;
                    if(clicks == 1){
                        component2.setText(Game.getAvailableChampions().get(r).getAbilities().get(0).getName());
                    }else if(clicks == 2){
                        component3.setText(Game.getAvailableChampions().get(r).getAbilities().get(1).getName());
                    }else if(clicks == 3){
                        component4.setText(Game.getAvailableChampions().get(r).getAbilities().get(2).getName());
                    }else if(clicks == 4){
                        component2.setText("");
                        component3.setText("");
                        component4.setText("");
                        r = (int)(Math.random()*15);
                        component1.setText(Game.getAvailableChampions().get(r).getName());
                        clicks = 0;
                    }
                }
            }
        });
        VBox v = new VBox();
        HBox h = new HBox(component2, component3, component4);
        //h.setPadding(new Insets(50));
        h.setAlignment(Pos.BOTTOM_LEFT);
        v.getChildren().addAll(component1, h);
        Scene scene = new Scene(v, 600, 200);
        stage.setScene(scene);
        stage.show();
    }
}
