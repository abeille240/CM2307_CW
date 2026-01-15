public class Booking {

    private Property property;
    private BookingStatus status;
    private Student student;

    public Booking(Property property, Student student) {
        this.property = property;
        this.student = student;
        this.status = BookingStatus.REQUESTED;
    }

    public Property getProperty(){
        return property;
    }

    public Student getStudent(){
        return student;
    }

    public BookingStatus getStatus(){
        return status;
    }

    public void confirm(){
        status = BookingStatus.CONFIRMED;
    }
    public void cancel(){
        status = BookingStatus.CANCELLED;
    }
}
