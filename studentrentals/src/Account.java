import java.util.ArrayList;
import java.util.List;


public abstract class Account {

    protected String userName;
    protected String password;
    protected List<Messaging> messageList;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.messageList = new ArrayList<>();
    }
    
    public void displayProfile() {
        // display account profile
    }

    public void displayMessages() {
        for (Messaging message : messageList) {
            System.out.println(message.getContent());
        }
    }

    public void sendMessage(String message, Account receiver) {
        Messaging msg = new Messaging(this, receiver, message);
        receiver.getMessageList().add(msg);
    }

    public String getUserName() {
        return userName;
    }

    public List<Messaging> getMessageList() {
        return messageList;
    }
}
