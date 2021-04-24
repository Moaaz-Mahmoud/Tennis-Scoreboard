package com.company;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
    Button p1Increment = new Button(),
           p2Increment = new Button(),
           reset       = new Button();
    Score  score       = new Score();
    Label  Scoreboard  = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tennis Scoreboard");

        p1Increment.setText("P1+");
        p1Increment.setOnAction(this);
        p2Increment.setText("P2+");
        p2Increment.setOnAction(this);
        reset.setText("Reset");
        reset.setOnAction(this);

        Group layout = new Group();

        Scoreboard.setLayoutX(150);  Scoreboard.setLayoutY(150);
        reset.setLayoutX(150);       reset.setLayoutY(220);
        p1Increment.setLayoutX(240); p1Increment.setLayoutY(220);
        p2Increment.setLayoutX(290); p2Increment.setLayoutY(220);

        Scoreboard.setFont(new Font("Times New Roman", 24));
        Font defaultFont = new Font("Times New Roman", 14);
        reset      .setFont(defaultFont);
        p1Increment.setFont(defaultFont);
        p2Increment.setFont(defaultFont);

        layout.getChildren().add(p1Increment);
        layout.getChildren().add(p2Increment);
        layout.getChildren().add(reset);
        layout.getChildren().add(Scoreboard);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        Scoreboard.setText(score.toString());
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event){
        final int x = 100, y = 100;

        if(event.getSource() == p1Increment) {
            score.incrementPlayer1();
        }
        if(event.getSource() == p2Increment) {
            score.incrementPlayer2();
        }
        if(event.getSource() == reset){
            score.reset();
        }
        Scoreboard.setText(score.toString());
    }
}