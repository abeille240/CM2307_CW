import java.util.List;
import java.util.Scanner;

public class SearchService {

    private Reccomendations reccomendations = new Reccomendations();

    public void search(Student student, List<Property> allProperties, Scanner scanner) {
        // Get recommendations
        List<Property> recommended = reccomendations.getRecommendations(student, allProperties, 5); // Top 5
        displayAllProperties(recommended);

        //DETAIL OF PROPERTY + MESSAGING
        int choice;
        if (!recommended.isEmpty()) {
            System.out.println("\n\nEnter the name of the property to view details or press Enter to skip.");
            String propertyName = scanner.nextLine().trim();
            if (!propertyName.isEmpty()) {
                Property selected = findPropertyByName(recommended, propertyName);
                if (selected != null) {
                    selected.displayProperty();
                    System.out.println("\nWould you like to send a query message to the homeowner?");
                    System.out.println("\n1. Yes Please!\n2. No Thanks");
                    choice = scanner.nextInt();                    
                    scanner.nextLine();
                    if (choice == 1){
                        System.out.println("\nEnter your message: ");
                        String message = scanner.nextLine();
                        student.sendMessage(message, selected.getHomeOwner());
                        System.out.println("\n\nMessage sent!");
                    }
                } else {
                    System.out.println("Property not found in recommendations.");
                }
            }
        }
    }

    private Property findPropertyByName(List<Property> properties, String name) {
        for (Property p : properties) {
            if (p.getPropertyName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void displayAllProperties(List<Property> properties) {
        if (properties.isEmpty()) {
            System.out.println("No properties to display.");
            return;
        }
        for (Property p : properties) {
            System.out.println(p.getPropertyName());
            System.out.println("---");
        }
    }

}
