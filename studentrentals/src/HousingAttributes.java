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
            System.out.print("Maximum Weekly price: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number between 50-1000");
                scanner.next();
            }
            price = scanner.nextInt();
        } while (price <= 49 || price >= 10000);

        scanner.nextLine();
        return price;
    }

    private static String askForLocation(Scanner scanner) {
        String location = "";

        while (location.isEmpty()) {
            System.out.println("Available locations: " + locations);
            System.out.print("\nChoose location: ");
            location = scanner.nextLine();

            if (!locations.contains(location)) {
                System.out.println("Invalid location");
                location = "";
            }
        }
        return location;
        }

    private static boolean askForUtilities(Scanner scanner) {
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.println("Utilities included?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter 1 or 2");
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
        System.out.print("Bedrooms (1-12): ");
        while (!scanner.hasNextInt()){
        System.out.println("Please enter a number between 1 and 12!");
        scanner.next();
        }
        bedrooms = scanner.nextInt();
    }while( bedrooms < 1 || bedrooms > 12 );

    scanner.nextLine();
    return bedrooms;
    }



    private static int askForBathrooms(Scanner scanner) {
    int bathrooms;

    do{
        System.out.print("Bathrooms (1-12): ");
        while (!scanner.hasNextInt()){
        System.out.println("Please enter a number between 1 and 12!");
        scanner.next();
        }
        bathrooms = scanner.nextInt();
    }while( bathrooms < 1 || bathrooms > 12 );

    scanner.nextLine();
    return bathrooms;
    }




}



