package server.action;

import server.data.DataBase;
import server.serverEngine.ClientHandler;

import java.io.IOException;

public class SendContacts implements Runnable{
    private ClientHandler clientHandler;

    public SendContacts(ClientHandler c){
        clientHandler=c;
    }

    @Override
    public void run() {

    }
}
