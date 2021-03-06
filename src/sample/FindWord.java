package sample;

import ggTranslate.Translator;
import ggTranslate.Audio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import sample.Main;

import static sample.Main.trie;
import static sample.Main.wordsRecently;
public class FindWord implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    // audio button
    @FXML
    private Button audioButton;
    @FXML
    private Button audioButtonVN;

    //audio
    private Audio audio = Audio.getInstance();

    // GG translate
    private Translator translator = new Translator();

    //Label
    @FXML
    private Label labelExplain;
    @FXML
    private Label labelRecommend;

    @FXML
    private VBox vBoxExplain;
    @FXML
    private HBox hBoxRecommend;

    // Button
    @FXML
    private Button soundButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button googleButton;
    @FXML
    private Button addButton;
    @FXML
    private Button subtractButton;
    @FXML
    private Button replaceButton;
    @FXML
    private Button recentlyButton;
    @FXML
    private Button likeButton;

    @FXML
    private ListView listView;

    // input  ,recommend,explain word
    @FXML
    private TextField inputWordTextField;
    @FXML
    private TextArea recommendTextArea;
    @FXML
    private TextArea explainTextArea;

    // press home button
    public void pressButtonHome(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    // press add button
    public void pressButtonAdd(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("Add.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    // press replace button
    public void pressButtonReplace(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("Replace.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    // press replace button
    public void pressButtonRecent(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("Recently.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    //press delete button
    public void pressButtonDelete(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("Delete.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    public void audioButton() throws IOException, JavaLayerException {
        String word = inputWordTextField.getText();
        if (word.length() == 0) return;
        InputStream sound = audio.getAudio(word, "en");
        audio.play(sound);
    }
    public void setAudioButtonVN() throws Exception {
        String word = inputWordTextField.getText();
        String ggTranslate = translator.callUrlAndParseResult("en", "vi", word);
        if (word.length() == 0) return;
        try (InputStream sound = audio.getAudio(ggTranslate, "vi")) {
            audio.play(sound);
        }
    }

    // Find word in GG
    public void buttonFindPressGG() throws Exception {
        String word = inputWordTextField.getText();
        String ggTranslate = translator.callUrlAndParseResult("en", "vi", word);
        //recommendTextArea.setFont(javafx.scene.text.Font.font("Monospaced",28));
        wordsRecently.addWord(word);
        explainTextArea.setText(ggTranslate);
    }


    //text area key press (word recommend)
    public void textAreaKeyPressListView(KeyEvent t) {
        String word = inputWordTextField.getText();
        if (word.length() == 0) return;
        if (t.getCode() != KeyCode.ENTER) {
            word = inputWordTextField.getText();
            ArrayList<String> rcm = trie.recommendWord(word);
            String recommend = "";
            listView.getItems().clear();
            ObservableList<String> recommends = FXCollections.observableArrayList();
            recommends.addAll(rcm);
            listView.getItems().addAll(recommends);
        } else {
            word = inputWordTextField.getText();
            wordsRecently.addWord(word);
            String mean = trie.search(word);
            explainTextArea.setText(mean);
        }
    }

    // click on list view to see explanation of word
    public void listViewClick() {
        String text = new String();
        text = listView.getSelectionModel().getSelectedItem().toString();
        inputWordTextField.setText(text);
        wordsRecently.addWord(text);
        String mean = trie.search(text);
        explainTextArea.setText(mean);
    }

    /**
     * Init function.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // setup listview styles
        listView.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Monospaced; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00;-fx-font-size : 22;-fx-background-radius: 10 ");
        // explain area setup
        explainTextArea.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Monospaced; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00;-fx-font-size : 18;-fx-background-radius: 10 ");

        vBoxExplain.setStyle("-fx-background-color :rgb(175, 28, 73);-fx-background-radius: 10");
        hBoxRecommend.setStyle("-fx-background-color :rgb(175, 28, 73);-fx-background-radius: 10");

        //  icon sound
        Image iconImage = new Image("img/icons8_Audio_20px.png");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(25);
        iconImageView.setFitWidth(25);
        soundButton.setGraphic(iconImageView);
        soundButton.setShape(new Circle(2));

        iconImage = new Image("img/icons8_Audio_20px_2.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(25);
        iconImageView.setFitWidth(25);
        audioButtonVN.setGraphic(iconImageView);
        audioButtonVN.setShape(new Circle(2));

        // icon Home
        iconImage = new Image("img/home1.jpg");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        homeButton.setGraphic(iconImageView);
        homeButton.setStyle("-fx-background-radius: 10 ");

        // add icon
        iconImage = new Image("img/add.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        addButton.setGraphic(iconImageView);
        addButton.setStyle("-fx-background-radius: 10 ");
        // addButton.setShape(new Circle(4));

        // subtract icon
        iconImage = new Image("img/subtract.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        subtractButton.setGraphic(iconImageView);
        subtractButton.setStyle("-fx-background-radius: 10 ");
        //  subtractButton.setShape(new Circle(4));

        // recently icon
        iconImage = new Image("img/recently.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        recentlyButton.setGraphic(iconImageView);
        recentlyButton.setStyle("-fx-background-radius: 10 ");

        /* find icon from database and gg translate */
        Image iconImage1 = new Image("img/replace.png");
        iconImageView = new ImageView(iconImage1);
        iconImageView.setFitHeight(36);
        iconImageView.setFitWidth(39);
        replaceButton.setGraphic(iconImageView);
        replaceButton.setStyle("-fx-background-radius: 10");

        iconImage = new Image("img/Google_Translate_Icon.jpg");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(50);
        iconImageView.setFitWidth(60);
        googleButton.setGraphic(iconImageView);
        googleButton.setStyle("-fx-border-color : rgb(155,0,125) ;");

    }
}
