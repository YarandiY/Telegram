package client.controller;


import client.connections.GiveContacts;
import client.connections.SendingFile;
import client.connections.SendingMsg;
import client.connections.SetRead;
import client.factory.AddChats;
import client.factory.CreatContactInfo;
import client.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import shared.Chat;
import shared.FileMsg;
import shared.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatPage implements Initializable {

    @FXML
    Button profileInfo;
    @FXML
    ScrollPane main;
    @FXML
    TextField chatField;
    private String url;


    AddChats addChats = new AddChats();
    VBox v;

    public void back(ActionEvent actionEvent) {
        GiveContacts.run();
        Main.controler=ContactsPage.class.getClass();
        Page.goTo(getClass().getResource("../UI/fxmlFiles/contactsPage.fxml"));
    }

    public void send(ActionEvent actionEvent) {
        new SendingMsg().send(chatField.getText());
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File select = fileChooser.showOpenDialog(null);
        if (select != null) {
            url = select.getAbsoluteFile().toURI().toString();
        }
        System.out.println(url);
        new SendingFile().send(new File(url));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GiveContacts.run();
        main.setVvalue(600);
        CreatContactInfo creatContactInfo = new CreatContactInfo();
        profileInfo.setGraphic(creatContactInfo.creat(ContactsPage.contact));
        v = addChats.addChats();
        main.setStyle("-fx-background-image: url(../UI/materialFiles/pictures/wallpaper.jpg)");
        main.setContent(v);
        SetRead.read(ContactsPage.contact);    }
}

