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
    Button p1Increment        = new Button(),
           p2Increment        = new Button(),
           reset              = new Button(),
           undo               = new Button(),
           wimbledonMode      = new Button();
    Label  Scoreboard         = new Label("");
    Score  score              = new Score();
    ScoreHistory scoreHistory = new ScoreHistory();

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
        undo.setText("Undo");
        undo.setOnAction(this);
        wimbledonMode.setText("Wimbledon");
        wimbledonMode.setOnAction(this);

        Group layout = new Group();

        Scoreboard.setLayoutX(150);    Scoreboard.setLayoutY(150);
        reset.setLayoutX(150);         reset.setLayoutY(220);
        p1Increment.setLayoutX(240);   p1Increment.setLayoutY(220);
        p2Increment.setLayoutX(290);   p2Increment.setLayoutY(220);
        undo.setLayoutX(150);          undo.setLayoutY(250);
        wimbledonMode.setLayoutX(240); wimbledonMode.setLayoutY(250);

        Scoreboard.setFont(new Font("Times New Roman", 24));
        Font defaultFont = new Font("Times New Roman", 14);
        reset.setFont(defaultFont);
        p1Increment.setFont(defaultFont);
        p2Increment.setFont(defaultFont);
        undo.setFont(defaultFont);
        wimbledonMode.setFont(defaultFont);

        layout.getChildren().add(p1Increment);
        layout.getChildren().add(p2Increment);
        layout.getChildren().add(reset);
        layout.getChildren().add(Scoreboard);
        layout.getChildren().add(undo);
        layout.getChildren().add(wimbledonMode);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        Scoreboard.setText(score.toString());
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == p1Increment) {
            scoreHistory.add(new Score(score));
            score.incrementPlayer1();
        }
        if(event.getSource() == p2Increment) {
            scoreHistory.add(new Score(score));
            score.incrementPlayer2();
        }
        if(event.getSource() == reset){
            scoreHistory.add(new Score(score));
            score.reset();
        }
        if(event.getSource() == undo){
            if(!scoreHistory.isEmpty()) {
                Score.Mode mode = score.getMode();
                score = scoreHistory.getLastScore();
                scoreHistory.removeLastScore();
                score.setMode(mode);
            }
        }
        if(event.getSource() == wimbledonMode){
            score.setMode(score.getMode() == Score.Mode.WIMBLEDON ? Score.Mode.REGULAR : Score.Mode.WIMBLEDON);
            wimbledonMode.setText(wimbledonMode.getText().equals("Wimbledon") ? "Regular" : "Wimbledon");
        }
        Scoreboard.setText(score.toString());
    }
}