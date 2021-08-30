package client.controller;

import client.Exception.DuplicateUsername;
import client.Exception.EmptyField;
import client.connections.Connect;
import client.connections.CreateAccount;
import client.connections.GiveContacts;
import client.factory.CreateProfilePicture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountPage implements Initializable {

    @FXML
    public StackPane profilePic;
    @FXML
    public TextField name;
    @FXML
    public TextField id;
    @FXML
    public PasswordField pass;
    private String url="shared/defaultProfileImage.jpeg";


    public void back(ActionEvent actionEvent) {
        Page.goTo(getClass().getResource("../UI/fxmlFiles/loginPage.fxml"));
    }

    public void create(ActionEvent actionEvent) {
        try {
            System.out.println("khob");
            CreateAccount.create(name.getText(), id.getText(), pass.getText(), url);
            System.out.println("hale!");
            Page.goTo(getClass().getResource("../UI/fxmlFiles/loginPage.fxml"));
        } catch (EmptyField emptyField) {
            System.out.println(emptyField.getMessage());
        } catch (IOException e) {
            System.out.println("server not found");
            Connect.connect();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server not find!Try again");
            alert.show();
        } catch (DuplicateUsername duplicateUsername) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "duplicate username!");
            alert.show();
            System.out.println(duplicateUsername.getMessage());

        }
    }

    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File select = fileChooser.showOpenDialog(null);
        if (select != null) {
            url = select.getAbsoluteFile().toURI().toString();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GiveContacts.run();
        ImageView imageView = new ImageView("client/UI/materialFiles/pictures/defaultProfileImage.jpeg");
        imageView.setStyle("-fx-border-color: #0088cc ; -fx-border-width: 5px");
        profilePic.getChildren().add(CreateProfilePicture.profilePicture(imageView, 50));
        Button button = new Button("+");
        button.setOnAction(this::chooseImage);
        button.getStyleClass().add("changePicButton");
        profilePic.getChildren().add(button);

    }
}
