package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.dictionary;
import static sample.Main.trie;

public class Delete implements Initializable { // back ground
    @FXML
    private Image backgroundImage;
    @FXML
    private ImageView imageView;

    // text file explain, word target and notification when add successfully
    @FXML
    private TextField textFieldWord;
    @FXML
    private Label labelNotification;

    //button home and add
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonDelete;


    // back to FindWord scene
    public void pressButtonFindWord(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    // press ok when add new word
    public void pressAgreeDeleteWord() throws Exception {
        String wt = textFieldWord.getText();
        if (trie.search(wt) != -1) {
            labelNotification.setText("Word " + wt + " has been deleted successfully");
            dictionary.deleteWordTrie(wt);
        } else {
            labelNotification.setText("Sorry !! Word " + wt + " can't be found in my data");
        }
        labelNotification.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //  labelNotification.setVisible(true);
        backgroundImage = new Image("img/addbgr.jpg");
        imageView.setImage(backgroundImage);
        imageView.setFitWidth(950);
        imageView.setFitHeight(550);
        imageView.setVisible(true);

        // home button
        Image iconImage = new Image("img/home1.jpg");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(49);
        iconImageView.setFitWidth(51);
        buttonHome.setGraphic(iconImageView);
        buttonHome.setStyle("-fx-border-color : rgb(189,18,19);-fx-background-color: rgb(255,255,255) ");

        // add button
        iconImage = new Image("img/subtract.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(49);
        iconImageView.setFitWidth(51);
        buttonDelete.setGraphic(iconImageView);
        buttonDelete.setStyle("-fx-border-color : rgb(189,18,19);-fx-background-color: rgb(255,255,255) ");
    }
}
