package CaurseWork;

public class Customer {
    private int vehicleNumber;
    private int vehicleType;
    private int fuelType;
    private double amountOfFuelRequired;
    private boolean gotTicket;

    //Constructor
    public Customer(int vehicleNumber, int vehicleType, int fuelType, float amountOfFuelRequired, boolean gotTicket) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.amountOfFuelRequired = amountOfFuelRequired;
        this.gotTicket = gotTicket;
    }

    //Set Ticket
    public void getTicket(){
        gotTicket=true;
    }

    //Getters
    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public int getFuelType() {
        return fuelType;
    }

    public double getAmountOfFuelRequired() {
        return amountOfFuelRequired;
    }

    public boolean gotTicket() {
        return gotTicket;
    }
}
