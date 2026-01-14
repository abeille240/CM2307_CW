public class Booking {

    private Property property;
    private String status;
    private DateRange bookingDate;

    public Booking(Property property, DateRange bookingDate) {
        this.property = property;
        this.bookingDate = bookingDate;
        this.status = "PENDING";
    }

    public void cancelBooking(String userName) {
        status = "CANCELLED";
    }

    public void notifyUser(String message, String userName) {
        // notification logic
    }

    public void amendAvailability() {
        // availability update
    }

    public boolean checkAvailability() {
        return true;
    }
}
