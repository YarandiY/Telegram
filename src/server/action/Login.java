package server.action;

import server.data.DataBase;
import server.serverEngine.ClientHandler;
import shared.User;
import java.io.IOException;

public class Login {
    public  static User check(ClientHandler c) throws IOException {
        String id = c.inputStream.readUTF();
        String pass = c.inputStream.readUTF();
        User u = DataBase.getUser(id);
        if (u == null || !u.getPassword().equals(pass)) {
            c.outputStream.writeUTF("false");
            c.outputStream.flush();
            return null;
        }
        c.outputStream.writeUTF("true");
        c.outputStream.writeObject(u);
        c.outputStream.reset();
        c.outputStream.flush();
        return u;

    }
}
