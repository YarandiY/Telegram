package server.action;

import server.data.DataBase;
import server.serverEngine.ClientHandler;
import server.serverEngine.Server;
import shared.User;

import java.io.*;

public class Signup {

    public static void create(ClientHandler c) throws IOException {
        String name = c.inputStream.readUTF();
        String username = c.inputStream.readUTF();
        String password = c.inputStream.readUTF();
        String url = c.inputStream.readUTF();
        System.out.println("khondam hame ro");
        if (DataBase.getUser(username) != null) {
            c.outputStream.writeUTF("false");
            c.outputStream.flush();
            return;
        }
        c.outputStream.writeUTF("true");
        c.outputStream.flush();
        System.out.println("inam hale");
        User user=new User(name,username,password);
        user.setProfilePicturePath(url);
        System.out.println("databaseeee");
        DataBase.addUser(user);
        System.out.println(name+"sakhte shod");
    }
}
