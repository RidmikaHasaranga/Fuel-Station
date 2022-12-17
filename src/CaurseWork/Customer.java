package CaurseWork;

public class Customer {
    private int vehicleNumber;
    private String vehicleType;
    private String fuelType;
    private double amountOfFuelRequired;
    private boolean gotTicket;

    //Constructor
    public Customer(int vehicleNumber, String vehicleType, String fuelType, float amountOfFuelRequired, boolean gotTicket) {
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

    public String getVehicleType() {
        return vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getAmountOfFuelRequired() {
        return amountOfFuelRequired;
    }

    public boolean gotTicket() {
        return gotTicket;
    }
}
