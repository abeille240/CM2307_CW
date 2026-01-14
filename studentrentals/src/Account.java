import java.util.ArrayList;
import java.util.List;

// Abstract Class for shared Student and HomeOwner methods
//Used for Login
// Used for Messaging
//TO Reavaluate for hashmap passwords
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
            System.out.println("From " + message.getSender().getUserName() + ": " + message.getContent());
        }
    }

    // add messages to a list - receivers filter by their username
    public void sendMessage(String message, Account receiver) {
        Messaging msg = new Messaging(this, receiver, message);
        receiver.getMessageList().add(msg);
    }

    public List<Messaging> getMessageList() {
        return messageList;
    }

        public String getUserName() {
        return userName;
    }
}
