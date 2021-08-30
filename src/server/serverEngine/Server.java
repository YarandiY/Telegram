package server.serverEngine;

import server.data.DataBase;
import shared.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ExecutorService executersServer=Executors.newFixedThreadPool(20);
    protected static ConcurrentHashMap<ClientHandler,User> onlineUsers;
    public static void main(String[] args) {
        DataBase.update();
        startServer();
    }

    public static  void startServer(){
        try(ServerSocket serverSocket=new ServerSocket(8080)) {
            onlineUsers=new ConcurrentHashMap<>();
            Socket socket;
            while (true){
                socket=serverSocket.accept();
                ClientHandler clientHandler =new ClientHandler(socket);
                executersServer.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
