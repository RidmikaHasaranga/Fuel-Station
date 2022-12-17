package CaurseWork;

public class Detail {
    private Customer customer;
    private DateTime dateTime;

    //Constructor
    public Detail(Customer customer, DateTime dateTime) {
        this.customer = customer;
        this.dateTime = dateTime;
    }

    //Getters
    public Customer getCustomer() {
        return customer;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
