import java.util.List;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        System.out.println("Welcome to StudentRentals!\n");
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\n1. Login");
            System.out.println("2. Exit Program");

            System.out.println("\n- Select 1 or 2 to continue -");
            int numChoice;
            try {
                numChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n- Invalid input! Please enter 1 for Login or 2 to Exit. -\n");
                scanner.nextLine(); // consume invalid input
                continue;
            }
            

            switch(numChoice){
                case 1:
                    loginMain(scanner);
                    break;
                case 2:
                    System.out.println("See you Soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }

        }
    }


    // LOGIN SEQUENCE
    private static void loginMain(Scanner scanner) {
        System.out.println("\nWelcome to Login");
        System.out.println("\nPress 0 to exit, or Anything Else to continue with login!");

        if(scanner.nextLine().equals("0")){
            return;
        }
        
        while(true){
            System.out.println("Username: ");
            String username = scanner.nextLine();

            System.out.println("\nPassword: ");
            String password = scanner.nextLine();

            Account account = Authorisation.authLogin(username,password,"userdata.txt", ",");
            
            if(account != null){
                // get account type and make account
                System.out.println("\nLogin Success!\n");
                setMenu(scanner, account);
                return;
            }

            else{
                System.out.println("\nLogin failed! Press Anything to try again, or Press 0 to exit.\n");
                if(scanner.nextLine().equals("0")){
                    return;
                }
        }
    }
        
    }


    // SPLIT INTO LAYOUTS 
    public static void setMenu(Scanner scanner, Account account){
        account.displayProfile();

        if (account instanceof Student student){
            studentLayout(scanner, student);
        }
        else if (account instanceof HomeOwner ho){
            homeOwnerLayout(scanner, ho);
        }
    }

    
    // STUDENT LAYOUT + FUNCTIONS
    public static void studentLayout(Scanner scanner, Student student){
        while(true){
            int numChoice;
            try {
                numChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // consume invalid input
                continue;
            }
            
            switch(numChoice){
                case 1:
                    preferencesMain(scanner, student);
                    break;
                case 2:
                    searchPropMain(scanner, student);
                    break;
                case 3:
                    messageHomeOwner(scanner, student);
                    break;
                case 4:
                    manageBookings(scanner, student);
                    break;
                case 5:
                    System.out.println("Successfully logged out. Goodbye!");
                    loginMain(scanner);
                    return;
                default:
                    System.out.println("Invalid Input!");
            }
            student.displayProfile();
        }     
    }

        //STUDENT PREF
    public static void preferencesMain(Scanner scanner, Student student){
        System.out.println("\n\n-- SETTING UP YOUR PREFERENCES --");
        System.out.println("\nSelect your Housing Preferences below.\n\n");

        Preferences preferences = HousingAttributes.collectPreferences(scanner);
        student.setPreferences(preferences);

        System.out.println("Preferences saved!");

    }

        //STUDENT VIEW EVERY LISTING
    public static void searchPropMain(Scanner scanner, Student student){
        SearchService searchService = new SearchService();
        searchService.search(student, PropertyManager.getAllProperties(), scanner);
    }

        // MESSAGE HOMEOWNER
    public static void messageHomeOwner(Scanner scanner, Student student){
        System.out.println("\n\n-- " + student.getUserName() + "'s MESSAGES --\n\n");
        student.viewAndReplyMessages(scanner);

    }

        //VIEW BOOKINGS
    public static void manageBookings(Scanner scanner, Student student){
        List<Booking> bookings = student.getBookings(PropertyManager.getAllProperties());
        if(bookings.isEmpty()){
            System.out.println("No bookings yet.");
        }else{
            System.out.println("Your bookings:");
            for(Booking b : bookings){
                System.out.println("Property: " + b.getProperty().getPropertyName() + ", Status: " + b.getStatus());
            }
        }
        System.out.println("Would you like to make a new booking?");
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
        if(choice == 1){
            makeBooking(scanner, student);
        }
    }

        //MAKE A BOOKING
    public static void makeBooking(Scanner scanner, Student student){
        List<Property> allProps = PropertyManager.getAllProperties();
        if(allProps.isEmpty()){
            System.out.println("No properties available.");
            return;
        }
        System.out.println("Available properties:");
        for(int i=0; i<allProps.size(); i++){
            System.out.println((i+1) + ". " + allProps.get(i).getPropertyName());
        }
        System.out.print("Enter the number of the property to book: ");
        int num;
        try {
            num = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
            return;
        }
        if(num < 1 || num > allProps.size()){
            System.out.println("Invalid number.");
            return;
        }
        Property selected = allProps.get(num-1);
        boolean success = student.bookViewing(selected);
        if(success){
            System.out.println("Booking request sent!");
        }else{
            System.out.println("You have already requested this property.");
        }
    }











    // HOMEOWNER LAYOUT + FUNCTIONS 
    public static void homeOwnerLayout(Scanner scanner, HomeOwner ho){
        while(true){
            int numChoice;
            try {
                numChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // consume invalid input
                continue;
            }
                
            switch(numChoice){
                case 1:
                    listPropMain(scanner, ho);
                    break;
                case 2:
                    removePropMain(scanner, ho);
                    break;
                case 3:
                    viewPropMain(scanner, ho);
                    break;
                case 4:
                    messageStudent(scanner, ho);
                    break;
                case 5:
                    manageBookingMain(scanner, ho);
                    break;
                case 6:
                    amendPropStatusMain(scanner, ho);
                    break;
                case 7:
                    System.out.println("Successfully logged out. Goodbye!");
                    loginMain(scanner);
                    return;
                default:
                    System.out.println("Invalid Input");
            }
            ho.displayProfile();
        }
    }

        //HO LISTINGS
    public static void listPropMain(Scanner scanner, HomeOwner ho){
        System.out.println("\n\n-- SETTING UP YOUR LISTING --");
        System.out.println("\nLets add a listing!");

        //name
        System.out.println("\nFirst, What's the name of the Property?");
        String propName = scanner.nextLine();
        //Data
        Preferences propData = HousingAttributes.collectPreferences(scanner);
        //Description
        System.out.println("\nAdd some Property Description to draw students in!");
        String propDescription = scanner.nextLine();

        Property property = new Property(propName, propData, propDescription, ho);
        ho.listProperty(property);

        System.out.println("That's it! Property Listed!\n");

    }

        //HO REMOVE LISTINGS
    public static void removePropMain(Scanner scanner, HomeOwner ho){
        System.out.println("\n\n-- REMOVING A LISTING --");
        System.out.println("Which listing do you want to remove?\n\n");
        if(ho.getPropertyList().isEmpty()){
            System.out.println("No listings!");

        }
        else{
            int i = 0;
            int selection;
            for(Property p : ho.getPropertyList()){
                System.out.println("Property " + (i+1));
                p.displayProperty();
                System.out.println("\n\n\n");
                i++;
            }
            do{
                System.out.print("Choose a Property you wish to remove (1-" + i + "): ");
                while (!scanner.hasNextInt()){
                    System.out.println("Please enter a number between 1 and " + i);
                    scanner.next();
                }
                selection = scanner.nextInt();
                scanner.nextLine(); // consume newline
            }while( selection < 1 || selection > i );

            ho.removeProperty(selection-1);
            
            System.out.println("\n\n\nPoof! Property De-Listed!\n\n");
        }
    }


        //HO VIEW PERSONAL LISTINGS
    public static void viewPropMain(Scanner scanner, HomeOwner ho){
        System.out.println("\n\n-- ACTIVE LISTINGS --");
        System.out.println("\nHere are you currently active properties!");
        if(ho.getPropertyList().isEmpty()){
            System.out.println("No listings!");
        }
        else{
            for(Property p : ho.getPropertyList()){
                p.displayProperty();
                System.out.println("\n\n");
            }
        }
    }
    

    public static void amendPropStatusMain(Scanner scanner, HomeOwner ho){
        ho.amendProperty();

    }

    public static void manageBookingMain(Scanner scanner, HomeOwner ho){
        ho.manageBookings();
    }

    public static void messageStudent(Scanner scanner, HomeOwner ho){
        System.out.println("-- " + ho.getUserName() + "'s MESSAGES --\n\n");
        ho.viewAndReplyMessages(scanner);
    }

}
