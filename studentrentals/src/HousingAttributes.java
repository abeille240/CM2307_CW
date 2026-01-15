import java.util.List;
import java.util.Scanner;

public class HousingAttributes {
    private static final List<String> locations =
            List.of("Cathays", "Cardiff Bay", "Roath", "Rumney", "Adamsdown");

    public static Preferences collectPreferences(Scanner scanner) {
        int price = askForPrice(scanner);
        String location = askForLocation(scanner);
        boolean utilities = askForUtilities(scanner);
        int bedrooms = askForBedrooms(scanner);
        int bathrooms = askForBathrooms(scanner);

        return new Preferences(price, location, utilities, bedrooms, bathrooms);
    }

    private static int askForPrice(Scanner scanner) {
        int price;
        do {
            System.out.print("Whats the weekly price?\n");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number between 100-500");
                scanner.next();
            }
            price = scanner.nextInt();
        } while (price <= 100 && price >= 500);

        scanner.nextLine();
        return price;
    }

    private static String askForLocation(Scanner scanner) {
        String location = "";

        while (location.isEmpty()) {
            System.out.println("Available locations: " + locations);
            System.out.print("\nChoose Property location: ");
            location = scanner.nextLine();

            if (!locations.contains(location)) {
                System.out.println("\nInvalid location! Please Input an available location (Case Sensitive)\n");
                location = "";
            }
        }
        return location;
        }

    private static boolean askForUtilities(Scanner scanner) {
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.println("\nUtilities included?\n");
            System.out.println("1. Yes");
            System.out.println("2. No");
            while (!scanner.hasNextInt()) {
                System.out.println("\nPlease enter 1 or 2\n");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        // boolean
        return choice == 1;
    }

    private static int askForBedrooms(Scanner scanner) {
    int bedrooms;

    do{
        System.out.print("\nBedrooms (1-12): ");
        while (!scanner.hasNextInt()){
        System.out.println("\nPlease enter a number between 1 and 11!\n");
        scanner.next();
        }
        bedrooms = scanner.nextInt();
        if( bedrooms < 1 || bedrooms > 12 ){
            System.out.println("\nPlease enter a number between 1 and 11!\n");
        }
    }while( bedrooms < 1 || bedrooms > 12 );

    scanner.nextLine();
    return bedrooms;
    }



    private static int askForBathrooms(Scanner scanner) {
    int bathrooms;

    do{
        System.out.print("\nBathrooms (1-12): ");
        while (!scanner.hasNextInt()){
        System.out.println("\nPlease enter a number between 1 and 12!\n");
        scanner.next();
        }
        bathrooms = scanner.nextInt();
            if( bathrooms < 1 || bathrooms > 12 ){
        System.out.println("\nPlease enter a number between 1 and 12!\n");}
    }while( bathrooms < 1 || bathrooms > 12 );

    scanner.nextLine();
    return bathrooms;
    }

}



