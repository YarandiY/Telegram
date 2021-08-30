package shared;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CopyOnWriteArrayList;


public class User implements MessageReceiver {
    private String name;
    private String id;
    private String password;
    private String profilePicturePath;
    private CopyOnWriteArrayList<Message> messages;
    private String lastSeen;
    public CopyOnWriteArrayList<Message> getMessages() {
        return messages;
    }

    private static final long serialVersionUID = -9042563009866316552L;


    public void setLastSeen(boolean online) {
        if (online)
            lastSeen = "online";
        else
            this.lastSeen = "last seen " + Integer.toString(LocalTime.now().getHour()) + " : " + Integer.toString(LocalTime.now().getMinute()) + " at " + Integer.toString(LocalDateTime.now().getDayOfMonth()) + LocalDateTime.now().getMonth().name();
    }

    public void setLastSeen(String s) {
        lastSeen = s;
    }


    public void addMessage(Message message) {
        if (messages == null)
            messages = new CopyOnWriteArrayList<>();
        messages.add(message);
    }


    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.lastSeen = "last seen " + Integer.toString(LocalTime.now().getHour()) + " : " + Integer.toString(LocalTime.now().getMinute()) + " at " + Integer.toString(LocalDateTime.now().getDayOfMonth()) + LocalDateTime.now().getMonth().name();
        this.profilePicturePath = "shared/defaultProfileImage.jpeg";
    }


    public String getLastSeen() {
        return lastSeen;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public void setMessages(CopyOnWriteArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeId(String id) {
        this.id = id;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User)
            return this.id.equalsIgnoreCase(((User) obj).id);
        return false;
    }
}