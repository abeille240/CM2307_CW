import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract Class for shared Student and HomeOwner methods
// Used for Login
// Used for Messaging
// TODO: Reevaluate for hashing passwords
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


    public void viewAndReplyMessages(Scanner scanner) {

    System.out.println("\n\nMessages:");
    if (messageList.isEmpty()) {
        System.out.println("\nNo messages right now!\n");
        return;
    }

    int i = 1;
    for (Messaging m : messageList) {
        System.out.println(i + ". From " + m.getSender().getUserName() + ": " + m.getContent());
        i++;
        }

    System.out.println("\nWould you like to reply to a message?");
    System.out.println("1. Yes\n2. No");

    int choice;
    try {
        choice = scanner.nextInt();
            scanner.nextLine();
    } catch (Exception e) {
        scanner.nextLine();
        System.out.println("Invalid input.");
        return;
    }

    if (choice != 1) return;

    System.out.println("Enter the number of the message to reply to:");
    int msgNum = scanner.nextInt();
    scanner.nextLine();

    if (msgNum < 1 || msgNum > messageList.size()) {
        System.out.println("Invalid message number.");
        return;
    }

    Messaging selectedMsg = messageList.get(msgNum - 1);

    System.out.println("Enter your reply:");
    String reply = scanner.nextLine();

    sendMessage(reply, selectedMsg.getSender());
    System.out.println("Reply sent!");
    }

    //BOOKINGS

    public void cancelBooking(Booking booking) {
    booking.cancel();
}


}
