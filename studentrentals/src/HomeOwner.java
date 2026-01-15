import java.util.ArrayList;
import java.util.List;

public class HomeOwner extends Account {

    private List<Property> hoPropertyList = new ArrayList<>();

    public HomeOwner(String userName, String passwordHash) {
        super(userName, passwordHash);
    }


    //MENU
    @Override
    public void displayProfile(){
        System.out.println("Welcome HomeOwner " + userName + "!");
        System.out.println("\n1. List Property");
        System.out.println("\n2. Remove Property");
        System.out.println("\n3. View Properties");
        System.out.println("\n4. View Messages");
        System.out.println("\n5. Manage Bookings");
        System.out.println("\n6. Amend Property Status");
        System.out.println("\n7. Logout");
        System.out.println("Choose an option by number input");

    }

    //LISTING ON MAIN
    public void listProperty(Property property) {
        hoPropertyList.add(property);
        PropertyManager.addProperty(property);
    }

    public List<Property> getPropertyList() {
        return hoPropertyList;
    }

    public void removeProperty(int propertynum) {
        hoPropertyList.remove(propertynum);
        PropertyManager.removeProperty(propertynum);
    }


    //MESSAGING 
    public void messageStudent(String message, Student student) {
        sendMessage(message, student);
    }


    //BOOKINGS
    public List<Booking> manageBookings() {
        List<Booking> result = new ArrayList<>();
        for (Property p : getPropertyList()) {
            result.addAll(p.getBookings());
        }
        return result;
    }
    

    //STATUS SHIT
    public boolean markPropertyPending(Property property, Booking booking) {
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            System.out.println("Booking must be confirmed first.");
            return false;
        }
        return property.markPending();
    }



}
