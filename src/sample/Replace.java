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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class Replace implements Initializable {

    // back ground
    @FXML private Image backgroundImage ;
    @FXML private ImageView imageView;

    // text file explain, word target and notification when add successfully
    @FXML private TextField textFieldWord ;
    @FXML private TextField textFieldExplain ;
    @FXML private Label labelNotification;

    //button home and add
    @FXML private Button buttonHome ;
    @FXML private Button buttonAdd ;


    // back to FindWord scene
    public void pressButtonFindWord(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        Scene viewNext = new Scene(viewNextParent);
        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    // add word in replace.txt
    void addNewWordInFile(String ws,String wt) throws IOException {
        File f = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/replace.txt");
        FileWriter bw = new FileWriter(f,true);
        bw.write(ws+"-"+wt+"\n");
        bw.append("\n");
        bw.flush();
    }

    // press ok when add new word
    public void pressAgreeAddNewWord() throws Exception{
        String ws = textFieldWord.getText();
        String wt = textFieldExplain.getText();
        if(trie.search(ws)==-1){
            System.out.println("Can't find "+ws+ "in dictionary");
            return;
        }
        addNewWordInFile(ws,wt);
        String wordExplain = dictionary.deleteWordTrie(ws);
        dictionary.addWordTrie(wt,wordExplain);
        labelNotification.setText("Word "+ ws +" has been replaced successfully");
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
        iconImage = new Image("img/replace.png");
        iconImageView = new ImageView(iconImage);
        iconImageView.setFitHeight(49);
        iconImageView.setFitWidth(51);
        buttonAdd.setGraphic(iconImageView);
        buttonAdd.setStyle("-fx-border-color : rgb(189,18,19);-fx-background-color: rgb(255,255,255) ");

    }
}
