package client.connections;

import client.main.Main;
import javafx.scene.control.Alert;
import shared.User;

import java.io.IOException;

public class SetRead {
    public static void read(User contact){
        try {
            Main.outputStream.writeUTF("setread");
            Main.outputStream.flush();
            Main.outputStream.writeUTF(contact.getId());
            Main.outputStream.flush();
            Main.outputStream.reset();
            System.out.println("hale");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server not find!Try again");
            alert.show();
            Connect.connect();
            read(contact);
        }
    }
}
