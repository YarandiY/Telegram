package client.controller;

import client.Exception.EmptyField;
import client.Exception.UserNotFind;
import client.connections.CheckLoginInfo;
import client.connections.Connect;
import client.connections.GiveContacts;
import client.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.User;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoginPage implements Initializable {

    @FXML
    TextField id;
    @FXML
    PasswordField pass;

    public static User user = null;
    public volatile static CopyOnWriteArrayList<User> users;


    public void creatAccount(ActionEvent actionEvent) {
        Page.goTo(getClass().getResource("../UI/fxmlFiles/createAccountPage.fxml"));
    }

    public synchronized void login(ActionEvent actionEvent) {
        CheckLoginInfo checkLoginInfo = new CheckLoginInfo(id.getText(), pass.getText());
        try {
            user = checkLoginInfo.send();
            GiveContacts.run();
            System.out.println("tamom shod");
            Main.controler=ContactsPage.class.getClass();
            Page.goTo(getClass().getResource("../UI/fxmlFiles/contactsPage.fxml"));
        } catch (EmptyField emptyField) {
            System.out.println(emptyField.getMessage());
        } catch (IOException e) {
            System.out.println("ServerNotFound!Try it again...");
            Connect.connect();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server not find!Try again");
            alert.show();
            Page.goTo(getClass().getResource("../UI/fxmlFiles/LoginPage.fxml"));
        } catch (ClassNotFoundException e) {
            System.out.println("I don't know whats wrong ... try it later");
            Page.goTo(getClass().getResource("../UI/fxmlFiles/LoginPage.fxml"));
        } catch (UserNotFind userNotFind) {
            System.out.println(userNotFind.getMessage());
            Page.goTo(getClass().getResource("../UI/fxmlFiles/LoginPage.fxml"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
