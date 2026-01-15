import java.util.ArrayList;
import java.util.List;

public class PropertyManager {
    private static List<Property> totalPropertyList = new ArrayList<>();

    public static void addProperty(Property property) {
        totalPropertyList.add(property);
    }
    public static void removeProperty(int num) {
        totalPropertyList.remove(num);
    }

    public static List<Property> getAllProperties() {
        return totalPropertyList;
    }
}
