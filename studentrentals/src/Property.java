import java.util.ArrayList;
import java.util.List;

public class Property {

    private String propertyName;
    private Preferences houseData;
    private String houseDescription;
    private HomeOwner homeOwner;
    private List<DateRange> bookingDateRange;
    private Student bookedStudent;
    private int availability;

    public Property(String name, Preferences data, String description, HomeOwner homeOwner, int availability) {
        this.propertyName = name;
        this.houseData = data;
        this.houseDescription = description;
        this.homeOwner = homeOwner;
        this.bookingDateRange = new ArrayList<>();
        this.availability = availability;
    }



    public void setBookedStudent(Student student) {
        this.bookedStudent = student;
        availability = 2;
    }

    public void displayProperty(){
        System.out.println("Property Name: " + getPropertyName());
        System.out.println("Description: " + getHouseDescription());
        Preferences data = getHouseData();
        System.out.println("Price: " + data.getPricePreference());
        System.out.println("Location: " + data.getLocationPreference());
        System.out.println("Utilities: " + (data.getUtilityPreference() ? "Yes" : "No"));
        System.out.println("Bedrooms: " + data.getBedroomPreference());
        System.out.println("Bathrooms: " + data.getBathroomPreference());
        System.out.println("Availability: " + getAvailability());
        System.out.println("Home Owner: " + getHomeOwner().getUserName());
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Preferences getHouseData() {
        return houseData;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public List<DateRange> getBookingDateRange() {
        return bookingDateRange;
    }

    public Student getBookedStudent() {
        return bookedStudent;
    }

    public String getAvailability() {
        switch(availability){
            case 1:
                return("Available");
            case 2:
                return("Pending");
            case 3:
                return("Sold");
            default:
                return("Error");
        }
    }
}
