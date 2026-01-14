public class Messaging {
    private Account sender;
    private Account receiver;
    private String content;

    public Messaging(Account sender, Account receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Account getSender() {
        return sender;
    }
    
    public Account getReceiver(){
        return receiver;
    }

    public String getContent() {
        return content;
    }
}
