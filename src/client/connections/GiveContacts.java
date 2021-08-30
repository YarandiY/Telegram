package client.connections;


import client.controller.LoginPage;
import client.main.Main;
import javafx.application.Platform;
import shared.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GiveContacts{
    public static void run() {
        try {
                Main.outputStream.writeUTF("giveContacts");
                Main.outputStream.flush();
                Object o = Main.inputStream.readObject();
                ArrayList<User> temp = (ArrayList<User>) o;
                LoginPage.users = new CopyOnWriteArrayList<>();
                for (User u :
                        temp) {
                    LoginPage.users.add(u);
                }
                System.out.println("hale");
                Platform.runLater(() -> {
                    Main.getPrimaryStage().setScene(Main.getPrimaryStage().getScene());
                    Main.getPrimaryStage().show();
                });
                System.out.println("inja");

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
