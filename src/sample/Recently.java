package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static sample.Main.trie;
import static sample.Main.wordsRecently;

public class Recently implements Initializable {

    @FXML private ListView recentlyListView;
    @FXML private Button homeButton ;

    // press home back
    public void pressButtonBack(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image iconImage = new Image("img/back.png");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(70);
        iconImageView.setFitWidth(70);
        homeButton.setGraphic(iconImageView);
        homeButton.setStyle("-fx-background-radius: 10 ");
        recentlyListView.setStyle(" -fx-font-family: Monospaced;-fx-font-size : 22;-fx-background-radius: 10 ");

        //for(int i=)

        recentlyListView.getItems().addAll(wordsRecently.getReverseWordRecently());
    }
}
