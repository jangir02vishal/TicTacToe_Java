package com.example.tictactoe_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TicTacToe extends Application {

    private Button buttons[][] = new Button[3][3];

    private Label playerXScoreLabel, playerOScoreLabel;

    private boolean playerXTurn = true;

    private int playerXScore = 0, playerOScore = 0;

    private BorderPane createContent(){
        BorderPane root = new BorderPane();

        root.setPadding(new Insets(20, 0, 20, 0));
        root.setStyle("-fx-background-color: #e3eefa;");

        //title
        Label titalLabel = new Label("Tic Tac Toe");
        titalLabel.setStyle("-fx-font-size: 32pt; -fx-font-weight: bold;-fx-text-fill: #24667d");
        root.setTop(titalLabel);
        BorderPane.setAlignment(titalLabel, Pos.CENTER);
        titalLabel.setPadding(new Insets(0, 0, 20, 0));

        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        gridPane.setPrefWidth(500);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setFocusTraversable(false);
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-text-fill: #24667d");
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        root.setCenter(gridPane);


        //Score
        HBox scoreBoard = new HBox(30);
        scoreBoard.setAlignment(Pos.CENTER);
        scoreBoard.setPrefWidth(500);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size: 13pt; -fx-font-weight: bold;-fx-text-fill: #24667d");
        playerOScoreLabel = new Label("Player O : 0");
        playerOScoreLabel.setStyle("-fx-font-size: 13pt; -fx-font-weight: bold;-fx-text-fill: #24667d");
        Button button = new Button("Restart");
        button.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-text-fill: #24667d");
        button.setPrefHeight(24);
        button.setFocusTraversable(false);
        button.setOnAction(event -> resetButtonClicked(button));

        Image image = new Image("C:\\Users\\jangi\\IdeaProjects\\TicTacToe_JavaFX\\images\\reload-button.jpg");
        ImageView imageView = new ImageView(image);

        // Set the image as the graphic of the button
        button.setGraphic(imageView);

        scoreBoard.getChildren().addAll(playerXScoreLabel, playerOScoreLabel, button);
        scoreBoard.setPadding(new Insets(20, 0, 0, 0));

        root.setBottom(scoreBoard);

        return root;
    }

    private void resetButtonClicked(Button button){
        playerXScore=0;
        playerOScore=0;
        playerXScoreLabel.setText("Player X : " + playerXScore);
        playerOScoreLabel.setText("Player O : " + playerOScore);
        resetBoard();
        return;
    }


    private void buttonClicked(Button button){

        if(button.getText().equals("")){
            if(playerXTurn){
                button.setText("X");
            }else{
                button.setText("O");
            }

            playerXTurn = !playerXTurn;
        }
        checkWinner();
        return;
    }

    private void checkWinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().isEmpty()
            ){
                //winner
                String winner = buttons[row][0].getText();
                //set color of winning combination
                buttons[row][0].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[row][1].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[row][2].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //column
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().isEmpty()
            ){
                //winner
                String winner = buttons[0][col].getText();
                //set color of winning combination
                buttons[0][col].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[1][col].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[2][col].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

        }

        //diagonals
            if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                    !buttons[0][0].getText().isEmpty()
            ){
                //winner
                String winner = buttons[0][0].getText();
                //set color of winning combination
                buttons[0][0].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[1][1].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[2][2].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

            if(buttons[2][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[0][2].getText()) &&
                    !buttons[2][0].getText().isEmpty()
            ){
                //winner
                String winner = buttons[2][0].getText();
                //set color of winning combination
                buttons[2][0].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[1][1].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                buttons[0][2].setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: #24667d;-fx-border-width: 2;");
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

        //Tie
        boolean tie = true;
        for(Button row[]: buttons){
            for(Button button: row){
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }

        if(tie){
            showTieDialog();
            resetBoard();
        }

    }

    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations " + winner + "! You won the game.");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLabel.setText("Player X : " + playerXScore);
        }else{
            playerOScore++;
            playerOScoreLabel.setText("Player O : " + playerOScore);
        }
    }

    private void resetBoard(){
        for(Button row[]: buttons){
            for(Button button: row){
                button.setText("");
                button.setStyle("-fx-font-size: 22pt;-fx-background-color: #c6ddf5; -fx-font-weight: bold;-fx-background-radius: 9px;-fx-border-color: null;-fx-border-width: 2;");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        // Load the favicon image (replace "favicon.ico" with your actual file path)
        Image favicon = new Image("C:\\Users\\jangi\\IdeaProjects\\TicTacToe_JavaFX\\images\\tic-tac-toe.png");

        // Set the favicon for the primary stage
        stage.getIcons().add(favicon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}