import java.util.ArrayList;
import java.util.List;

public class PropertyManager {
    private static List<Property> totalPropertyList = new ArrayList<>();

    public static void addProperty(Property property) {
        totalPropertyList.add(property);
    }

    public static List<Property> getAllProperties() {
        return totalPropertyList;
    }
}
