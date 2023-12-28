package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;


public class Search_word extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage view_bookStage) {



        Label title = new Label("Search word");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(66);
        title.setPrefWidth(1180);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("SansSerif Bold", 20));
        title.setPadding(new Insets(0, 0, 0, 20));


        ObservableList<Word> Words = FXCollections.observableArrayList();


        TableColumn<Word, String> word = new TableColumn<>("Word");
        word.setPrefWidth(75);
        word.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Word, String> Meaning = new TableColumn<>("Meaning");
        Meaning.setPrefWidth(800);
        Meaning.setCellValueFactory(new PropertyValueFactory<>("meaning"));

        TableColumn<Word, Button> Option = new TableColumn<>("");
        Option.setPrefWidth(200);
        Option.setCellValueFactory(new PropertyValueFactory<>("delete"));


        TableView<Word> Table = new TableView<>();
        Table.setLayoutX(20);
        Table.setLayoutY(258);
        Table.setPrefHeight(369);
        Table.setPrefWidth(1140);
        Table.setMaxHeight(1.7976931348623157E308);
        Table.setMaxWidth(1.7976931348623157E308);
        Table.setItems(Words);
        Table.getColumns().addAll(word, Meaning, Option);


        Button exit = new Button("Back");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold", 14));
        exit.setOnAction(e -> {
            DictionaryApp home = new DictionaryApp();
            home.start(view_bookStage);
        });
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setMaxHeight(1.7976931348623157E308);
        hBox2.setMaxWidth(1.7976931348623157E308);
        hBox2.setPrefHeight(48);
        hBox2.setPrefWidth(148);
        hBox2.setLayoutX(1027);
        hBox2.setLayoutY(654);
        hBox2.setStyle("-fx-background-color: white;");
        hBox2.getChildren().add(exit);
        HBox.setMargin(exit, new Insets(0, 10, 10, 0));

        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.BLACK);
        mssg.setPrefHeight(17);
        mssg.setPrefWidth(666);
        mssg.setLayoutX(234);
        mssg.setLayoutY(231);
        mssg.setStyle("-fx-background-color: white;");
        mssg.setTextAlignment(TextAlignment.CENTER);


        TextField search = new TextField();
        search.setLayoutX(329);
        search.setLayoutY(170);
        search.setPrefHeight(37);
        search.setPrefWidth(238);
        search.setPromptText("Enter Word");

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(590);
        searchButton.setLayoutY(170);
        searchButton.setPrefHeight(37);
        searchButton.setPrefWidth(183);
        searchButton.setStyle("-fx-background-color: #ee8e19;");
        searchButton.setFont(new Font("Arial Bold", 14));
        searchButton.setOnAction(e -> {
            String searchWord = search.getText();

            try {

                HashMap<Word, Integer> searchResults = DictionaryApp.stub.searchByName(searchWord);

                if (searchResults.isEmpty()) {
                    mssg.setText("No matching words found for: " + searchWord);

                } else {
                    Words.clear();
                    for (Map.Entry<Word, Integer> entry : searchResults.entrySet()) {
                        Word wordValue = entry.getKey();
                        int index = entry.getValue();
                        wordValue.delete = new Button("delete");
                        wordValue.delete.setPrefHeight(20);
                        wordValue.delete.setPrefWidth(70);
                        wordValue.delete.setStyle("-fx-background-color: #ee8e19");
                        wordValue.delete.setFont(new Font("Arial Bold",14));
                        wordValue.delete.setOnAction(x -> {
                            try {
                                boolean removed = DictionaryApp.stub.removeWordByIndex(index);
                                if (removed) {
                                    Words.remove(wordValue); // Remove from the table view
                                    mssg.setText("Word removed: " + searchWord);
                                } else {
                                    mssg.setText("Error removing word. Word not found.");
                                }
                            } catch (RemoteException ex) {
                                mssg.setText("Error removing word: " + ex.getMessage());
                            }
                        });
                        Words.add(wordValue);
                    }
                }
            } catch (RemoteException ex) {
                mssg.setText("Error searching for word: " + ex.getMessage());
            }


        });


        Pane pane = new Pane();
        pane.setPrefHeight(698);
        pane.setPrefWidth(1180);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(Table, title, hBox2, search, searchButton, mssg);

        Scene view_bookScene = new Scene(pane);
        view_bookStage.setScene(view_bookScene);
        view_bookStage.show();





    }
}