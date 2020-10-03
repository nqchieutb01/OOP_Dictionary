package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button button;

    @FXML
    private Image backgroundImage;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageView1;

    //Go to FindWord
    public void ChangeSceneFindWord(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent viewNextParent = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        Scene viewNext = new Scene(viewNextParent);

        //Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewNext);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // load background
        backgroundImage = new Image("img/bgr.jpg");
        imageView.setImage(backgroundImage);
        imageView.setTranslateX(120);
        imageView.setVisible(true);

        backgroundImage = new Image("img/bgr4.jpg");
        imageView1.setImage(backgroundImage);
        imageView1.setTranslateX(0);
        imageView1.setVisible(true);

    }
}
