package CaurseWork;

import java.util.ArrayList;

public abstract class Queue {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    //Constructor
    public Queue(){

    }

    //add object for Customer Class
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void giveTicketToCustomer(Customer customer){
        customer.getTicket();
    }
}
