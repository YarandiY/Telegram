package client.factory;


import client.controller.ChatPage;
import client.controller.ContactsPage;
import client.controller.LoginPage;
import client.controller.Page;
import client.main.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import shared.Message;
import shared.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class CreatContactsPage {

    public VBox creatEmptyPage() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        Label txt = new Label("No Contact!");
        txt.setStyle("-fx-text-fill: \t#778899");
        vbox.getChildren().addAll(new Text(""), new Text(""), new Text(""), new Text(""), new Text(""));
        vbox.getChildren().add(txt);
        return vbox;
    }

    public ScrollPane creatContacts(CopyOnWriteArrayList<User> users) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinHeight(570);
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: white");
        scrollPane.setContent(vbox);
        ArrayList<User> temp = new ArrayList<>();
        for (User u :
                users) {
            if(!u.equals(LoginPage.user))
                temp.add(u);
        }
        temp.sort((a, b) -> {
            if (a.getMessages() == null || a.getMessages().stream().filter(m -> m.getTo() == LoginPage.user || m.getFrom() == LoginPage.user).toArray().length == 0)
                return -1;
            if (b.getMessages() == null ||b.getMessages().stream().filter(m -> m.getTo() == LoginPage.user || m.getFrom() == LoginPage.user).toArray().length == 0)
                return 1;
            Stream<Message> m1 = a.getMessages().stream().filter(m -> m.getTo() == LoginPage.user || m.getFrom() == LoginPage.user);
            Stream<Message> m2 = a.getMessages().stream().filter(m -> m.getTo() == LoginPage.user || m.getFrom() == LoginPage.user);
            if(m1.filter(m -> !m.isRead()).toArray().length == 0)
                return -1;
            if(m2.filter(m -> !m.isRead()).toArray().length == 0)
                return 1;
            return 0;
        });
        for (User u :
                temp) {
            vbox.getChildren().add(creatContact(u));
        }
        return scrollPane;
    }

    private HBox creatContact(User user) {
        ImageView imageView;
        imageView = new ImageView(user.getProfilePicturePath());
        HBox hBox = new HBox(20);
        hBox.setMinWidth(450);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(CreateProfilePicture.profilePicture(imageView, 30));
        Text text = new Text(user.getName());
        text.getStyleClass().add("txt");
        hBox.getChildren().add(text);
        hBox.getStyleClass().add("hbox");
        hBox.setOnMouseClicked(event ->
                {
                    ContactsPage.contact = user;
                    Main.controler= ChatPage.class.getClass();
                    Page.goTo(getClass().getResource("../UI/fxmlFiles/chatPage.fxml"));
                }

        );
        Integer messageCounter = 0;
        if(LoginPage.user.getMessages()!=null)
        for (Message m :
                LoginPage.user.getMessages()) {
            if (m.getFrom().equals(user)) {
                if (!m.isRead()) {
                    messageCounter++;
                }
            }
        }
        if (messageCounter > 0) {
            Label l = new Label(messageCounter.toString());
            Circle circle = new Circle(10);
            circle.setFill(javafx.scene.paint.Color.RED);
            l.setStyle("-fx-font-size:12px ; -fx-text-fill: white ");
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(circle);
            stackPane.getChildren().add(l);
            hBox.getChildren().add(stackPane);
        }
        return hBox;
    }
}
