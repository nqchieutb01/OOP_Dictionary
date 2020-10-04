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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

import static sample.Main.dictionary;
import static sample.Main.Dict;
import static sample.Main.trie;

public class FindWord implements Initializable {

    @FXML private AnchorPane anchorPane;

    //audio
    private Audio audio = Audio.getInstance();

    // GG translate
    private Translator translator = new Translator();
    @FXML private Button soundButton;
    @FXML private Button homeButton;
    @FXML private Button googleButton;
    @FXML private Button myPcButton;
    @FXML private Button addButton;
    @FXML private Button subtractButton;

    // input  ,recommend,explain word
    @FXML private TextField inputWordTextField;
    @FXML private TextArea recommendTextArea;
    @FXML private TextArea explainTextArea;

    public void pressButtonHome(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }
    @FXML private Button audioButton;
    public void audioButton() throws IOException, JavaLayerException {
        String word = inputWordTextField.getText();
        if(word.length()==0) return;
        InputStream sound = audio.getAudio(word,"en");
        audio.play(sound);
    }

    // Find word in GG
    public void buttonFindPressGG() throws Exception {
        String word = inputWordTextField.getText();
        String ggTranslate = translator.callUrlAndParseResult("en","vi",word);
        //recommendTextArea.setFont(javafx.scene.text.Font.font("Monospaced",28));
        explainTextArea.setText(ggTranslate);
    }

    // Find word from database
    public void buttonFindPressDB() throws Exception {
        String word = inputWordTextField.getText();
      //  System.out.println(word);
        String mean = dictionary.searchWordTrie(word);
        explainTextArea.setText(mean);
    }

    //text area Keypress
    public void textAreaKeyPress(KeyEvent t){
        String word = inputWordTextField.getText();
        if(word.length()==0) return;
        if(t.getCode() != KeyCode.ENTER){
            word = inputWordTextField.getText();
            //System.out.println(word);
            ArrayList<Integer> rcm = trie.recommendWord(word);
            String recommend = "";
            for(Integer x:rcm){
                recommend += Dict.get(x).getWordTarget() +'\n';
            }
            recommendTextArea.setText(recommend);

        } else {
            word = inputWordTextField.getText();
            String mean = dictionary.searchWordTrie(word);
            explainTextArea.setText(mean);
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        recommendTextArea.setStyle("-fx-control-inner-background:#000001; -fx-font-family: Monospaced; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00;-fx-font-size : 22;");
        explainTextArea.setStyle("-fx-control-inner-background:#000001; -fx-font-family: Monospaced; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00;-fx-font-size : 16;");

        //  icon sound
        Image iconImage = new Image("img/icons8_Audio_20px.png");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(25);
        iconImageView.setFitWidth(25);
        soundButton.setGraphic(iconImageView);
        soundButton.setShape(new Circle(2));

        // icon Home
        iconImage = new Image("img/home1.jpg");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        homeButton.setGraphic(iconImageView);
        homeButton.setStyle("-fx-background-color: rgb(255,255,255)");

        // add icon
        iconImage = new Image("img/add.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        addButton.setGraphic(iconImageView);
        addButton.setStyle("-fx-background-color: rgb(255,255,255)");
        addButton.setShape(new Circle(4));
        // subtract icon
        iconImage = new Image("img/subtract.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(39);
        iconImageView.setFitWidth(36);
        subtractButton.setGraphic(iconImageView);
        subtractButton.setStyle("-fx-background-color: rgb(255,255,255)");
        subtractButton.setShape(new Circle(4));

        // find icon from database and gg translate
        iconImage = new Image("img/search.jpg");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(60);
        iconImageView.setFitWidth(62);
        myPcButton.setGraphic(iconImageView);
        myPcButton.setStyle("-fx-background-color: rgb(255,255,255)");

        iconImage = new Image("img/searchgg.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(60);
        iconImageView.setFitWidth(62);
        googleButton.setGraphic(iconImageView);
        googleButton.setStyle("-fx-background-color: rgb(255,255,255)");
    }
}
