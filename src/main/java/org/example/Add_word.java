package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.rmi.RemoteException;


public class Add_word extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage add_wordStage) {

        Label title = new Label("Add Word");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(75);
        title.setPrefWidth(625);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setFont(new Font("SansSerif Bold", 20));
        title.setPadding(new Insets(0,0,0,20));

        TextField Word = new TextField();
        Word.setPrefHeight(35);
        Word.setPrefWidth(276);
        Word.setPromptText("Word");

        TextArea Meaning = new TextArea();
        Meaning.setPrefHeight(135);
        Meaning.setPrefWidth(276);
        Meaning.setPromptText("Meaning");


        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.BLACK);
        mssg.setPrefHeight(23);
        mssg.setPrefWidth(391);
        mssg.setTextAlignment(TextAlignment.CENTER);


        Button add = new Button("ADD");
        add.setPrefHeight(42);
        add.setPrefWidth(184);
        add.setStyle("-fx-background-color: #ee8e19");
        add.setFont(new Font("Arial Bold",14));


        add.setOnAction(e-> {
            String wordName = Word.getText();
            String meaning = Meaning.getText();
            Word word = new Word(wordName, meaning);
            try {
                boolean added = DictionaryApp.stub.addWord(word);
                if (added) {
                    mssg.setText("Word added: " + wordName + " - " + meaning);
                } else {
                    mssg.setText("Word not added. Duplicate found.");
                }
            } catch (RemoteException ex) {
                mssg.setText("Error adding word: " + ex.getMessage());
                ex.printStackTrace();
            }


        });



        VBox textfileds = new VBox();
        textfileds.setAlignment(Pos.CENTER);

        textfileds.setPrefWidth(485);
        textfileds.setPrefHeight(471);
        textfileds.setSpacing(10);
        textfileds.setStyle("-fx-background-color: white;");
        textfileds.getChildren().addAll(Word,Meaning,add,mssg);
        VBox.setMargin(Word,new Insets(0,100,0,100));
        VBox.setMargin(Meaning,new Insets(0,100,0,100));
        VBox.setMargin(add,new Insets(0,100,0,100));


        Button exit = new Button("Back");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            DictionaryApp home = new DictionaryApp();
            home.start(add_wordStage);
        });


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setMaxHeight(1.7976931348623157E308);
        hBox.setMaxWidth(1.7976931348623157E308);
        hBox.setPrefHeight(48);
        hBox.setPrefWidth(517);
        hBox.setStyle("-fx-background-color: white;");
        hBox.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));






        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setMaxHeight(1.7976931348623157E308);
        vBox.setMaxWidth(1.7976931348623157E308);
        vBox.setPrefHeight(400);
        vBox.setPrefWidth(517);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(title,textfileds,hBox);


        Scene add_bookScene = new Scene(vBox);
        add_wordStage.setScene(add_bookScene);
        add_wordStage.show();



    }
}
