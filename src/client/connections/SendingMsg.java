package client.connections;

import client.controller.ContactsPage;
import client.controller.LoginPage;
import client.controller.Page;
import client.main.Main;
import shared.Chat;

import java.io.IOException;

public class SendingMsg {

    public synchronized void send(String message){
        try {
            if (message != null) {
                Main.outputStream.writeUTF("sendingm");
                Main.outputStream.writeUTF(message);
                Main.outputStream.reset();
                Main.outputStream.writeObject(ContactsPage.contact);
                Main.outputStream.flush();
                Chat temp=new Chat(LoginPage.user,ContactsPage.contact,message);
                LoginPage.user.addMessage(temp);
                ContactsPage.contact.addMessage(temp);
                Page.goTo(getClass().getResource("../UI/fxmlFiles/chatPage.fxml"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            notify();
        }
    }
}
