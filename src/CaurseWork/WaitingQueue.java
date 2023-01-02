package CaurseWork;

public class WaitingQueue extends Queue{

    //Constructor
    public WaitingQueue() {
        super();
    }

    public Customer sendCustomerToFuelQueue(){
        Customer customer = customers.get(0);
        customers.remove(0);
        return customer;
    }

    public Customer getFirstCustomer(){
        return customers.get(0);
    }
}
