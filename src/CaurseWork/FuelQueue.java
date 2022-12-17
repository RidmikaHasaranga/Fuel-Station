package CaurseWork;

public class FuelQueue extends Queue{
    private final int maximumCustomers = 10;
    private int noOfCustomers;
    private String[] allowedVehicle;
    private WaitingQueue waitingQueue;

    //Constructor
    public FuelQueue(String[] vehiclesAllowed, WaitingQueue waitingQueue){
        super();
        this.noOfCustomers = 0;
        this.allowedVehicle = vehiclesAllowed;
        this.waitingQueue = waitingQueue;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (noOfCustomers>=maximumCustomers) {
            if (checkVehicleType(customer)) {
                super.addCustomer(customer);
                noOfCustomers++;
            }
            else {
                System.out.println("Vehicle not allowed in the Queue");
            }
        } else {
            System.out.println("Queue is full");
            waitingQueue.addCustomer(customer);
            System.out.println("Added the customer to the fuel Queue");
        }
    }

    //Remove the served customer and remove a selected customer
    public void removeCustomer(int index){
        customers.remove(index);
        noOfCustomers--;
    }

    //get first customer for serve
    public Customer getFirstCustomer(){
        return customers.get(0);
    }

    //Get free spaces in fuel queue
    public int getFreeSpace(){
        return maximumCustomers-noOfCustomers;
    }

    private boolean checkVehicleType(Customer customer){
        for (String s : allowedVehicle) {
            if (s.equals(customer.getVehicleType())) {
                return true;
            }
        }
        return false;
    }

    //allowed vehicle types
    public String[] getVehiclesAllowed() {
        return allowedVehicle;
    }
}
