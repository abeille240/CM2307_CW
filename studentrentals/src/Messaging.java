public class Messaging {
    private Account sender;
    private Account receiver;
    private String content;

    public Messaging(Account sender, Account receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
