package client.controller;

import client.connections.GiveContacts;
import client.factory.CreatContactsPage;
import client.factory.CreatMenuSidebar;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import shared.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContactsPage implements Initializable {


    @FXML
    VBox mainVBox;

    @FXML
    AnchorPane pane;


    VBox vBox;
    static TranslateTransition menuTranslation;
    public static User contact = null;


    public void menuPage(ActionEvent actionEvent) {
        CreatMenuSidebar creatMenuSidebar = new CreatMenuSidebar();
        vBox = creatMenuSidebar.creat(pane);
        menuTranslation = new TranslateTransition(Duration.millis(350), vBox);
        menuTranslation.setFromX(-700);
        menuTranslation.setToX(0);
        pane.getChildren().add(vBox);
        menuTranslation.setRate(1);
        menuTranslation.play();
    }

    public static void removeMenu() {
        menuTranslation.setRate(-1);
        menuTranslation.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GiveContacts.run();
        System.out.println("hata inja ham omad");
        CreatContactsPage creatContactsPage = new CreatContactsPage();
        synchronized (LoginPage.users) {
            System.out.println("alan injaeeim");
            if (LoginPage.users == null || LoginPage.users.isEmpty()) {
                this.mainVBox.getChildren().add(creatContactsPage.creatEmptyPage());
            } else {
                this.mainVBox.getChildren().add(creatContactsPage.creatContacts(LoginPage.users));
            }

        }
    }


}
