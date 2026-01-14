import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to StudentRentals!");
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1. Login");
            System.out.println("\n2. Exit Program");

            System.out.println("\nChoose an option by number input");
            int numChoice;
            try {
                numChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // consume invalid input
                continue;
            }

            if (numChoice == 1){
                loginMain(scanner);
            }
            else if (numChoice == 2){
                System.out.println("Thank You!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }

        }
    }

    private static void loginMain(Scanner scanner) {
        System.out.println("\nWelcome to Login");
        System.out.println("\nPress 0 to exit, or anything else to continue");

        if(scanner.nextLine().equals("0")){
            return;
        }

        System.out.println("Username: ");
        String username = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        Account account = Authorisation.authLogin(username,password,"userdata.txt", ",");
        if(account != null){
            // get account type and make account
            System.out.println("\nLogin Success!");
            setMenu(scanner, account);

        }
        else{
            System.out.println("\nLogin failed");
        }
        
    }

    public static void setMenu(Scanner scanner, Account account){
        account.displayProfile();

        if (account instanceof Student student){
            studentLayout(scanner, student);
        }
        else if (account instanceof HomeOwner ho){
            homeOwnerLayout(scanner, ho);
        }
    }

    
    public static void studentLayout(Scanner scanner, Student student){
        while(true){
            student.displayProfile();
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
                    viewPendMain(scanner, student);
                    break;
                case 4:
                    loginMain(scanner);
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }     
    }

    public static void homeOwnerLayout(Scanner scanner, HomeOwner ho){
        while(true){
            ho.displayProfile();
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
                    loginMain(scanner);
                    return;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public static void preferencesMain(Scanner scanner, Student student){
        System.out.println("Select your Housing Preferences below:");

        Preferences preferences = HousingAttributes.collectPreferences(scanner);
        student.setPreferences(preferences);

        System.out.println("Preferences saved!");

    }
    
    public static void listPropMain(Scanner scanner, HomeOwner ho){
        System.out.println("Lets add a listing!");

        //name
        System.out.println("First, What's the name of the Property");
        String propName = scanner.nextLine();
        //Data
        Preferences propData = HousingAttributes.collectPreferences(scanner);
        //Description
        System.out.println("Property Description");
        String propDescription = scanner.nextLine();

        Property property = new Property(propName, propData, propDescription, ho,1);
        ho.listProperty(property);

        System.out.println("Property Listed!");

    }
    
    public static void removePropMain(Scanner scanner, HomeOwner ho){
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
            System.out.println("Property De-Listed!");
    
        }

    }

    public static void viewPropMain(Scanner scanner, HomeOwner ho){
        System.out.println("Current Properties");
        if(ho.getPropertyList().isEmpty()){
            System.out.println("No listings!");
        }
        else{
            for(Property p : ho.getPropertyList()){
                p.displayProperty();
                System.out.println("\n\n\n");
            }
        }

    }
    
    public static void amendPropStatusMain(Scanner scanner, HomeOwner ho){
        
    }

    public static void manageBookingMain(Scanner scanner, HomeOwner ho){

    }

    public static void messageStudent(Scanner scanner, HomeOwner ho){
        //view current messages
        System.out.println("Messages from students:");
        if (ho.getMessageList().isEmpty()) {
            System.out.println("No messages.");
        } else {
            ho.displayMessages();
        }
    }
    public static void searchPropMain(Scanner scanner, Student student){
        SearchService searchService = new SearchService();
        searchService.search(student, PropertyManager.getAllProperties(), scanner);
    }
    public static void viewPendMain(Scanner scanner, Student student){
        System.out.println("checking!");
    }

}
