package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.rmi.Naming;



public class DictionaryApp extends Application{

    static Scene homescene;
    static WordDictionary stub;

    @Override
    public void start(Stage homeStage) {
        try {

            stub = (WordDictionary) Naming.lookup("rmi://127.0.0.1:5432/WordDictionary");

            Button Add_word = new Button("Add Word");
            Add_word.setMnemonicParsing(false);
            Add_word.setPrefHeight(53);
            Add_word.setPrefWidth(164);
            Add_word.setStyle("-fx-background-color: #ee8e19");
            Add_word.setTextFill(Color.BLACK);
            Add_word.setFont(new Font("SansSerif Bold", 16));
            Add_word.setOnAction(e -> {
                org.example.Add_word addWord = new Add_word();
                addWord.start(homeStage);

            });

            Button Search_word = new Button("Search Word");
            Search_word.setMnemonicParsing(false);
            Search_word.setPrefHeight(53);
            Search_word.setPrefWidth(164);
            Search_word.setStyle("-fx-background-color: #ee8e19");
            Search_word.setTextFill(Color.BLACK);
            Search_word.setFont(new Font("SansSerif Bold", 16));
            Search_word.setOnAction(e -> {
                org.example.Search_word searchWord = new Search_word();
                searchWord.start(homeStage);

            });


            HBox center = new HBox();
            center.setAlignment(Pos.CENTER_RIGHT);
            center.setPrefHeight(439);
            center.setPrefWidth(175);
            center.setStyle("-fx-background-color: white");
            center.getChildren().addAll(Add_word, Search_word);
            HBox.setMargin(Add_word, new Insets(100, 30, 100, 0));
            HBox.setMargin(Search_word, new Insets(100, 230, 100, 0));



            Label title = new Label("Word Dictionary");
            title.setAlignment(Pos.CENTER);
            title.setPrefHeight(64);
            title.setPrefWidth(2000);
            title.setStyle("-fx-background-color: #ee8e19;");
            title.setTextFill(Color.BLACK);
            title.setFont(new Font("SansSerif Bold", 24));


            BorderPane homePane = new BorderPane();
            homePane.setPrefHeight(400);
            homePane.setPrefWidth(800);
            homePane.setStyle("-fx-background-color: white");
            homePane.setTop(title);
            homePane.setCenter(center);
            BorderPane.setAlignment(title, Pos.CENTER);
            BorderPane.setAlignment(center, Pos.CENTER);


            homescene = new Scene(homePane);
            homeStage.setScene(homescene);
            homeStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main() {
        launch();
    }


}