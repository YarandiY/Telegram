package client.connections;

import client.Exception.DuplicateUsername;
import client.Exception.EmptyField;
import client.main.Main;
import java.io.IOException;

public class CreateAccount {

    public static synchronized void create(String name, String username, String password,String url) throws EmptyField, IOException, DuplicateUsername {
        if ( name== null || name.isEmpty())
            throw new EmptyField();
        if (username == null || username.isEmpty())
            throw new EmptyField();
        if (password == null || password.isEmpty())
            throw new EmptyField();
        Main.outputStream.writeUTF("signup");
        Main.outputStream.writeUTF(name);
        Main.outputStream.writeUTF(username);
        Main.outputStream.writeUTF(password);
        Main.outputStream.writeUTF(url);
        Main.outputStream.flush();
        System.out.println("ferestadm bara server");
        if(Main.inputStream.readUTF().equalsIgnoreCase("false"))
            throw new DuplicateUsername();
        System.out.println("creat");
    }
}
