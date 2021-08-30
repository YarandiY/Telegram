package server.data;

import shared.User;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataBase {
    private static ArrayList<User> users;
    private static String fileUsersPath = "D:\\Projects\\Telegram\\src\\server\\data\\Users";

    static {
        users = new ArrayList<>();
    }

    public static void update() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileUsersPath))) {
            users= (ArrayList<User>) objectInputStream.readObject();
            users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
            users = users.stream().filter(u -> !u.getName().startsWith("zzz")).collect(Collectors.toCollection(ArrayList::new));
            objectInputStream.close();
        } catch (EOFException e) {
            System.out.println("file tamom shod:)");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            System.out.println("StreamCorruptedException");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
            users.add(user);
            writeOnFile(users);
            update();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getUser(String id) {
        for (User user :
                users) {
            if (user.getId().equalsIgnoreCase(id))
                return user;
        }
        return null;
    }

    public static void writeOnFile(ArrayList arrayList){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileUsersPath, false));
            objectOutputStream.writeObject(users);
            objectOutputStream.reset();
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean removeUser(String id){
        User temp=getUser(id);
        if(temp==null)
            return false;
        users.remove(temp);
        return true;
    }
}
