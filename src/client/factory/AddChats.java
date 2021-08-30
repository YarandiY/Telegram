package client.factory;

import client.controller.ContactsPage;
import client.controller.LoginPage;
import client.main.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shared.Chat;
import shared.FileMsg;
import java.awt.*;
import java.io.*;

public class AddChats {
    public VBox addChats() {
        VBox vBox = new VBox(10);
        vBox.setMinWidth(450);
        vBox.setMinHeight(490);
        vBox.setStyle("-fx-background-image: url(../UI/materialFiles/pictures/wallpaper.jpg)");
        if (LoginPage.user.getMessages() == null || LoginPage.user.getMessages().isEmpty()) {
            return vBox;
        }
        for (int i = 0; i < LoginPage.user.getMessages().size(); i++) {
            if (LoginPage.user.getMessages().get(i).getTo().equals(ContactsPage.contact) || LoginPage.user.getMessages().get(i).getFrom().equals(ContactsPage.contact)) {
                if (LoginPage.user.getMessages().get(i) instanceof Chat)
                    vBox.getChildren().add(addChat((Chat) LoginPage.user.getMessages().get(i)));
                else
                    vBox.getChildren().add(addFile((FileMsg) LoginPage.user.getMessages().get(i)));
            }
        }
        return vBox;
    }


    public HBox addFile(FileMsg fileMsg){
        HBox hBox = new HBox();
        fileMsg.setRead(true);
        VBox vBox = new VBox();
        hBox.setMinWidth(400);
        vBox.setMinWidth(100);
        vBox.setMaxWidth(300);
        hBox.setStyle("-fx-padding: 20 20 20 8");
        ImageView i=new ImageView("client/UI/materialFiles/pictures/29074-200.png");
        i.setFitHeight(50);
        i.setFitWidth(50);
        if (fileMsg.getFrom().equals(LoginPage.user)) {
            vBox.setStyle("-fx-background-color:#6DD292;-fx-font-size:13px ;-fx-text-fill: black;-fx-background-radius:12px;-fx-padding: 1 10 1 15");
            hBox.setAlignment(Pos.CENTER_RIGHT);
        } else {
            vBox.setStyle("-fx-background-color: white;-fx-font-size:13px ;-fx-text-fill: black;-fx-background-radius:12px;-fx-padding: 1 10 1 15");
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().add(i);
        Label temp = new Label(fileMsg.getTime());
        temp.setStyle("-fx-font-size: 10px;-fx-alignment: center-right");
        vBox.getChildren().add(temp);
        hBox.getChildren().add(vBox);
        i.setOnMouseClicked((event)->{
            try {
                Main.outputStream.writeUTF("recive");
                Main.outputStream.writeObject(fileMsg);
                byte[] buffer=new byte[(int) fileMsg.getFile().length()];
                InputStream inputStream=Main.socket.getInputStream();
                OutputStream o= new FileOutputStream(new File("client/files"));
                int read;
                int total=0;
                while ((read=inputStream.read(buffer))>0){
                    o.write(buffer,0,read);
                    total+=read;
                }
                o.close();
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(fileMsg.getFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return hBox;
    }


    public HBox addChat(Chat message) {
        Chat chat = message;
        message.setRead(true);
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.setMinWidth(400);
        vBox.setMinWidth(100);
        vBox.setMaxWidth(300);
        hBox.setStyle("-fx-padding: 20 20 20 8");
        Label label = new Label(chat.getChat());
        label.setMinWidth(100);
        if (chat.getFrom().equals(LoginPage.user)) {
            vBox.setStyle("-fx-background-color:#6DD292;-fx-font-size:13px ;-fx-text-fill: black;-fx-background-radius:12px;-fx-padding: 1 10 1 15");
            hBox.setAlignment(Pos.CENTER_RIGHT);
        } else {
            vBox.setStyle("-fx-background-color: white;-fx-font-size:13px ;-fx-text-fill: black;-fx-background-radius:12px;-fx-padding: 1 10 1 15");
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().add(label);
        Label temp = new Label(chat.getTime());
        temp.setStyle("-fx-font-size: 10px;-fx-alignment: center-right");
        vBox.getChildren().add(temp);
        hBox.getChildren().add(vBox);
        return hBox;
    }
}
