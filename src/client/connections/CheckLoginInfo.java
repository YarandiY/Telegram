package client.connections;

import client.Exception.EmptyField;
import client.Exception.UserNotFind;
import client.main.Main;
import shared.User;

import java.io.*;

public class CheckLoginInfo {
    private String userName;
    private String pass;

    public CheckLoginInfo(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public synchronized User send() throws EmptyField, IOException, ClassNotFoundException, UserNotFind {
        if (userName == null || userName.isEmpty())
            throw new EmptyField();
        if (pass == null || pass.isEmpty())
            throw new EmptyField();
        Main.outputStream.writeUTF("login");
        Main.outputStream.writeUTF(userName);
        Main.outputStream.writeUTF(pass);
        Main.outputStream.flush();
        String s=Main.inputStream.readUTF();
        if (s.equalsIgnoreCase("false"))
            throw new UserNotFind();
        User temp= (User) Main.inputStream.readObject();
        return temp;
    }
}
