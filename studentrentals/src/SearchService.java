import java.util.List;
import java.util.Scanner;

public class SearchService {

    private Reccomendations reccomendations = new Reccomendations();

    public void search(Student student, List<Property> allProperties, Scanner scanner) {
        // Get recommendations
        List<Property> recommended = reccomendations.getRecommendations(student, allProperties, 5); // Top 5
        displayAllProperties(recommended);

        if (!recommended.isEmpty()) {
            System.out.println("Enter the name of the property to view details (or press Enter to skip): ");
            String propertyName = scanner.nextLine().trim();
            if (!propertyName.isEmpty()) {
                Property selected = findPropertyByName(recommended, propertyName);
                if (selected != null) {
                    selected.displayProperty();
                    System.out.println("Would you like to send a query message to the homeowner? (y/n): ");
                    String choice = scanner.nextLine().trim().toLowerCase();
                    if (choice.equals("y")) {
                        System.out.println("Enter your message: ");
                        String message = scanner.nextLine();
                        student.sendMessage(message, selected.getHomeOwner());
                        System.out.println("Message sent!");
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
            p.displayProperty();
            System.out.println("---");
        }
    }

    public void displayProperty(String propertyName) {
        // Implement if needed
    }

    public List<Property> filterProperties(List<Property> properties) {
        return properties; // Placeholder
    }
}
