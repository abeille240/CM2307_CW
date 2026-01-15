import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

    public void amendProperty(){
        Scanner scanner = new Scanner(System.in);
        List<Property> properties =  PropertyManager.getAllProperties();
        List<Property> listOfProperties = new ArrayList<>();
        for(int i=0; i<properties.size(); i++){
            Property b = properties.get(i);
            if(getUserName() == b.getHomeOwner().getUserName()){
                listOfProperties.add(b);
                System.out.println(i+  ". "+ b.getPropertyName());
            }
        }
        System.out.println("Please enter a number to amend.");
        String ans2 = scanner.nextLine();
        if(Integer.parseInt(ans2)>listOfProperties.size()-1){
            System.out.println("Invalid input. Please enter a valid number.");
        }
        else{
            Property p = listOfProperties.get(Integer.parseInt(ans2));
            System.out.println(p.getPropertyName());
            System.out.println("What would you like to do?:");
            System.out.println("\n1: Mark Pending");
            System.out.println("\n2: Mark Sold");

            ans2 = scanner.nextLine();
            if(Integer.parseInt(ans2) == 1){
                p.markPending();
            }
            if(Integer.parseInt(ans2) == 2){
                p.markSold();
            }
        }
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
    public List<Booking> returnBookings() {
        List<Booking> result = new ArrayList<>();
        for (Property p : getPropertyList()) {
            result.addAll(p.getBookings());
        }
        return result;
    }


    public void manageBookings(){
        Scanner scanner = new Scanner(System.in);
        List<Booking> bookings = returnBookings();
        if(bookings.isEmpty()){
            System.out.println("No bookings for your properties.");
            return;
        }
        System.out.println("Bookings for your properties:");
        for(int i=0; i<bookings.size(); i++){
            Booking b = bookings.get(i);
            System.out.println((i+1) + ". Student: " + b.getStudent().getUserName() + ", Property: " + b.getProperty().getPropertyName() + ", Status: " + b.getStatus());
        }
        System.out.print("Enter the number of the booking to manage (or 0 to exit): ");
        int num;
        try {
            num = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
            return;
        }
        if(num == 0) return;
        if(num < 1 || num > bookings.size()){
            System.out.println("Invalid number.");
            return;
        }
        Booking selected = bookings.get(num-1);
        System.out.println("1. Confirm booking\n2. Cancel booking");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
            return;
        }
        if(choice == 1){
            selected.confirm();
            System.out.println("Booking confirmed.");
        }else if(choice == 2){
            selected.cancel();
            System.out.println("Booking cancelled.");
        }else{
            System.out.println("Invalid choice.");
        }
    }



}
