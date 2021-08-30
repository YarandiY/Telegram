package client.connections;

import client.controller.ContactsPage;
import client.controller.LoginPage;
import client.controller.Page;
import client.main.Main;
import shared.FileMsg;

import java.io.*;


public class SendingFile {

    public void send(File file){
        try {
            System.out.println("check");
            if (file != null) {
                Main.outputStream.writeUTF("sendingf");
                Main.outputStream.writeLong(file.length());
                Main.outputStream.writeUTF(file.getName());
                Main.outputStream.writeObject(ContactsPage.contact);
                byte[] buffer=new byte[(int) file.length()];
                InputStream i=new FileInputStream(file);
                System.out.println(file.length());
                OutputStream o=Main.socket.getOutputStream();
                int read;
                int total=0;
                while ((read=i.read(buffer))>0){
                   o.write(buffer,0,read);
                    total+=read;
                }
                o.close();
                FileMsg fileMsg=new FileMsg(LoginPage.user,ContactsPage.contact,file);
                LoginPage.user.addMessage(fileMsg);
                ContactsPage.contact.addMessage(fileMsg);
                Page.goTo(getClass().getResource("../UI/fxmlFiles/chatPage.fxml"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
