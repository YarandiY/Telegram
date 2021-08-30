package client.controller;

import client.connections.ChangeP;
import client.connections.GiveContacts;
import client.factory.CreateProfilePicture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import server.action.Login;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfile implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField id;
    @FXML
    PasswordField pass;
    @FXML
    public StackPane profilePic;
    private static String url;
    private ImageView imageView;

    public void back(ActionEvent actionEvent) {
        GiveContacts.run();
        System.out.println("back method");
        Page.goTo(getClass().getResource("../UI/fxmlFiles/contactsPage.fxml"));
        url=null;
    }

    public void chooseImage(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        File select = fileChooser.showOpenDialog(null);
        if (select != null) {
            url = select.getAbsoluteFile().toURI().toString();
        }
        LoginPage.user.setProfilePicturePath(url);
        Page.goTo(getClass().getResource("../UI/fxmlFiles/editProfile.fxml"));
    }

    public void removeP(ActionEvent actionEvent){
        url="shared/defaultProfileImage.jpeg";
        LoginPage.user.setProfilePicturePath(url);
        Page.goTo(getClass().getResource("../UI/fxmlFiles/editProfile.fxml"));
    }

    public void change(ActionEvent actionEvent){
        System.out.println("omad");
        if(name.getText()!=null && name.getText().length()>1)
        LoginPage.user.changeName(name.getText());
        if(id.getText()!=null && id.getText().length()>1)
        LoginPage.user.changeId(id.getText());
        if(pass.getText()!=null && pass.getText().length()>1)
        LoginPage.user.changePassword(pass.getText());
        if(url!=null)
            LoginPage.user.setProfilePicturePath(url);
        System.out.println("in hale");
        ChangeP.change(LoginPage.user);
        System.out.println("hala baragard");
        url=null;
        back(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(LoginPage.user.getName());
        id.setText(LoginPage.user.getId());
        imageView = new ImageView(LoginPage.user.getProfilePicturePath());
        imageView.setStyle("-fx-border-color: #0088cc ; -fx-border-width: 5px");
        profilePic.getChildren().add(CreateProfilePicture.profilePicture(imageView, 50));
        Button button = new Button("+");
        button.setOnAction(this::chooseImage);
        button.getStyleClass().add("changePicButton");
        profilePic.getChildren().add(button);
        synchronized (this){
            notify();
        }
    }
}
