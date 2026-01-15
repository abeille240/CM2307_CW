import java.util.ArrayList;
import java.util.List;

public class Property {

    private String propertyName;
    private Preferences houseData;
    private String houseDescription;
    private HomeOwner homeOwner;
    private Student bookedStudent;
    private List<Booking> bookings = new ArrayList<>();
    private BookingStatus status;
    private SaleStatus saleStatus = SaleStatus.AVAILABLE;

    public Property(String name, Preferences data, String description, HomeOwner homeOwner) {
        this.propertyName = name;
        this.houseData = data;
        this.houseDescription = description;
        this.homeOwner = homeOwner;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public boolean markPending() {
    if (saleStatus != SaleStatus.AVAILABLE) {
        return false;
    }
    saleStatus = SaleStatus.PENDING;
    return true;
    }

    public boolean markSold() {
    if (saleStatus != SaleStatus.PENDING) {
        return false;
    }
    saleStatus = SaleStatus.SOLD;
    return true;
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
        System.out.println("Availability: " + getSaleStatus());
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

    //AI
    public boolean isBooked(Student student){
        return bookings.stream()
                .anyMatch(b -> b.getStudent().equals(student)
                        && b.getStatus() != BookingStatus.CANCELLED);

    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public List<Booking> getBookings(){
        return bookings;
    }

    public SaleStatus getSaleStatus(){
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus status){
        this.saleStatus = status;
    }

}
