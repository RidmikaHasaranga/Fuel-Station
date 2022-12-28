package CaurseWork;

public class Detail {
    private Customer customer;
    private DateTime dateTime;
    private String dispenserID;

    //Constructor
    public Detail(Customer customer, DateTime dateTime, String dispenserID) {
        this.customer = customer;
        this.dateTime = dateTime;
        this.dispenserID = dispenserID;
    }

    public Detail() {

    }

    //Getters
    public Customer getCustomer() {
        return customer;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getDispenserID() {
        return dispenserID;
    }
}
