package CaurseWork;

public class FuelRepository {
    private String fuelType;
    private float fuelLeft;
    private float capacity;

    //Constructor
    public FuelRepository(String fuelType, float capacity){
        this.fuelType = fuelType;
        this.capacity = capacity;
        fuelLeft = 0;
    }

    public void addFuel(float fuel){
        fuelLeft += fuel;
    }

    public float checkFuelLeft(){
        return fuelLeft;
    }

    public String getFuelType() {
        return fuelType;
    }

    public float getCapacity() {
        return capacity;
    }
}
