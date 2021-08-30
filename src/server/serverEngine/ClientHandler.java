package server.serverEngine;

import client.main.Main;
import server.action.Login;
import server.action.SendContacts;
import server.action.Signup;
import server.data.DataBase;
import shared.Chat;
import shared.FileMsg;
import shared.Message;
import shared.User;
import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
    public Socket socket;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public User user=null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            inputStream=new ObjectInputStream(socket.getInputStream());
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("ee");

                String request = inputStream.readUTF();
                System.out.println(request.toLowerCase());
                switch (request.toLowerCase()){
                    case "login":
                        user=Login.check(this);
                        DataBase.getUser(user.getId()).setLastSeen(true);
                        Server.onlineUsers.put(this,user);
                        break;
                    case "signup":
                        System.out.println("gereftam");
                        Signup.create(this);
                        break;
                    case "givecontacts":
                        try {
                            System.out.println("omad inja");
                            outputStream.reset();
                            outputStream.writeObject(DataBase.getUsers());
                            outputStream.flush();
                            System.out.println("tamam");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "logout":
                        DataBase.getUser(user.getId()).setLastSeen(false);
                        break;
                    case "change":
                        System.out.println("omad inja");
                        User u= (User)inputStream.readObject();
                        DataBase.getUser(user.getId()).changeId(u.getId());
                        DataBase.getUser(user.getId()).changePassword(u.getPassword());
                        DataBase.getUser(user.getId()).changeName(u.getName());
                        DataBase.getUser(user.getId()).setProfilePicturePath(u.getProfilePicturePath());
                        System.out.println("hame ro gereftam");
                        break;
                    case "sendingm":
                        try {
                            String pm=inputStream.readUTF();
                            User contact=(User)inputStream.readObject();
                            contact=DataBase.getUser(contact.getId());
                            Message temp=new Chat(user,contact,pm);
                            DataBase.getUser(user.getId()).addMessage(temp);
                            DataBase.getUser(contact.getId()).addMessage(temp);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                        case "sendingf":
                        try {
                            long size=inputStream.readLong();
                            String name=inputStream.readUTF();
                            User c= (User) inputStream.readObject();
                            File file= Paths.get("server/files/"+name).toAbsolutePath().toFile();
                            OutputStream o=new FileOutputStream(file);
                            InputStream i=socket.getInputStream();
                            byte[] buffer=new byte[(int) size];
                            int read=0;
                            while ((read=i.read(buffer))>0){
                                o.write(buffer,0,read);
                            }
                            o.close();
                            Message msg=new FileMsg(user,c,file);
                            DataBase.getUser(user.getId()).addMessage(msg);
                            DataBase.getUser(c.getId()).addMessage(msg);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "recive":
                        FileMsg fileMsg= (FileMsg) Main.inputStream.readObject();
                        File file= Paths.get("server/files/"+fileMsg.getFile().getName()).toAbsolutePath().toFile();
                        OutputStream o=socket.getOutputStream();
                        InputStream i=new FileInputStream(file);
                        byte[] buffer=new byte[(int) fileMsg.getFile().length()];
                        int read=0;
                        while ((read=i.read(buffer))>0){
                            o.write(buffer,0,read);
                        }
                        o.close();
                        break;
                    case "setread":
                        String contactId=inputStream.readUTF();
                        System.out.println("khondam");
                        if(DataBase.getUser(contactId).getMessages()!=null)
                        for(Message message:DataBase.getUser(contactId).getMessages()){
                            if(message.getTo().equals(user))
                                message.setRead(true);
                            System.out.println("ch");
                        }
                        System.out.println("hale");
                        break;
                        default:
                            System.out.println("I'm sorry");
                }
                DataBase.writeOnFile(DataBase.getUsers());
                outputStream.reset();

            }
        } catch (Exception e) {
            DataBase.getUser(user.getId()).setLastSeen(false);
            Server.onlineUsers.remove(this);
            System.out.println("the connection ghat shod :)");
        }finally {
            DataBase.writeOnFile(DataBase.getUsers());
        }
    }
}

