package client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import shared.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class NewGroup implements Initializable {

    @FXML
    ChoiceBox<String> members;

    public void back(ActionEvent actionEvent) {
        System.out.println("back method");
        Page.goTo(getClass().getResource("../UI/fxmlFiles/contactsPage.fxml"));
    }

    public void create(ActionEvent actionEvent){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CopyOnWriteArrayList<String> contacts=new CopyOnWriteArrayList<>();
        for (User u :
                LoginPage.users) {
            if(!u.equals(LoginPage.user))
            contacts.add(u.getName());
        }
        members.getItems().setAll(contacts);

    }
}
