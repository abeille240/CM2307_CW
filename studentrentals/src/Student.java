
public class Student extends Account {

    private Preferences preferences;

    public Student(String userName, String passwordHash, Preferences preferences) {
        super(userName, passwordHash);
        this.preferences = preferences;
    }
    @Override
    public void displayProfile(){
        System.out.println("Welcome Student " + userName + "!");
        System.out.println("\n1. Set Preferences");
        System.out.println("\n2. Search Properties");
        System.out.println("\n3. View Pending Properties");
        System.out.println("\n4. Logout");

        System.out.println("\nChoose an option by number input");
      
    }
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void bookViewing(DateRange dateRange) {
        // booking logic
    }

    public void displayPendingProperties() {
        // display pending bookings
    }

    public void payDeposit() {
        // payment logic
    }
}
