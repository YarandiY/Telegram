package shared;

import java.io.Serializable;
import java.time.LocalTime;

public abstract class Message implements Serializable {
    private User from;
    private MessageReceiver to;
    private String time;
    private boolean isRead=false;

    public Message(User from, MessageReceiver to) {
        this.from = from;
        this.to = to;
        time= Integer.toString(LocalTime.now().getHour())+":"+Integer.toString(LocalTime.now().getMinute());
    }


    public void setRead(boolean read) {
        isRead = read;
    }

    public User getFrom() {
        return from;
    }

    public MessageReceiver getTo() {
        return to;
    }

    public String getTime() {
        return time;
    }

    public boolean isRead() {
        return isRead;
    }
}
