package shared;


public class Chat extends Message {
    private String chat;

    public Chat(User from, MessageReceiver to,String chat) {
        super(from, to);
        this.chat=chat;
    }

    public synchronized void editChat(String chat){
        this.chat=chat;
    }

    public String getChat() {
        return chat;
    }
}
