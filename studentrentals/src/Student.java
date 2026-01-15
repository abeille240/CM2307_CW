import java.util.ArrayList;
import java.util.List;

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
        System.out.println("\n3. View Messages");
        System.out.println("\n4. Manage Bookings");
        System.out.println("\n5. Logout");

        System.out.println("\nChoose an option by number input");
      
    }
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Preferences getPreferences(){
        return preferences;
    }

    public boolean bookViewing(Property property) {
        if(property.isBooked(this)){
            System.out.println("You have already booked this property!");
            return false;
        }
        else if(property.getSaleStatus() != SaleStatus.AVAILABLE){
            System.out.println("Property is no longer Available");
            return false;
        }

        Booking booking = new Booking(property, this);
        property.addBooking(booking);
        return true;

    }

    public List<Booking> getBookings(List<Property> allProperties) {
    List<Booking> result = new ArrayList<>();
    for (Property p : allProperties) {
        for (Booking b : p.getBookings()) {
            if (b.getStudent().equals(this)) {
                result.add(b);
            }
        }
    }
    return result;
    }




    //BUY IT
public boolean purchaseProperty(Property property) {

    boolean hasConfirmedBooking = property.getBookings().stream()
        .anyMatch(b -> b.getStudent().equals(this)
                && b.getStatus() == BookingStatus.CONFIRMED);

    if (!hasConfirmedBooking) {
        System.out.println("You do not have a confirmed booking.");
        return false;
    }

    if (!property.markSold()) {
        System.out.println("Property is not pending.");
        return false;
    }

    return true;
}

}
