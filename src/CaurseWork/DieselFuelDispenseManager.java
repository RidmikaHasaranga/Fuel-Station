package CaurseWork;
import java.util.ArrayList;

public class DieselFuelDispenseManager implements FuelDispenseManager{
    private static ArrayList<Detail> detailArray = new ArrayList<>();
    private static int totalNumberOfVehiclesServed = 0;
    private static float totalProfit = 0;
    private FuelQueue fuelQueue;
    private FuelRepository repository;
    private static float pricePerLiter;
    private int fuelType;

    //Constructor
    public DieselFuelDispenseManager(FuelQueue fuelQueue, FuelRepository repository, float pricePerLiter, int fuelType) {
        this.fuelQueue = fuelQueue;
        this.repository = repository;
        DieselFuelDispenseManager.pricePerLiter = pricePerLiter;
        this.fuelType = fuelType;
    }

    @Override
    public void serveCustomer(DateTime date) {
        Detail detail = new Detail(fuelQueue.getFirstCustomer(), date);
        detailArray.add(detail);
        incrementVehiclesServed();
        incrementProfit(fuelQueue.getFirstCustomer());
        fuelQueue.removeCustomer(0);
    }

    //add object for Detail Class
    public static void addDetail(Detail detail){
        detailArray.add(detail);
    }

    public static ArrayList<Detail> getDetailArray() {
        return detailArray;
    }

    public static Customer getHighestAmountOfFuel(DateTime date){
        Customer highestAmountOfFuel = new Customer(0, 0, 1, 0, false);//temp values as first object for check highest amount of fuel
        for (Detail detail : detailArray) {
            if (detail.getDateTime() == date) {
                if (detail.getCustomer().getAmountOfFuelRequired() > highestAmountOfFuel.getAmountOfFuelRequired()) {
                    highestAmountOfFuel = detail.getCustomer();
                }
            }
        }
        return highestAmountOfFuel;
    }

    public static float totalFuelDispensed(int vehicleType){
        float totalFuelDispensed = 0;
        for (Detail detail : detailArray) {
            if (detail.getCustomer().getVehicleType()==vehicleType) {
                totalFuelDispensed += detail.getCustomer().getAmountOfFuelRequired();
            }
        }
        return totalFuelDispensed;
    }

    public static float totalProfit(DateTime date){
        float profit = 0;
        for (Detail detail : detailArray) {
            if (detail.getDateTime() == date) {
                profit += detail.getCustomer().getAmountOfFuelRequired() * pricePerLiter;
            }
        }
        return profit;
    }

    //served vehicle++
    public static void incrementVehiclesServed(){
        totalNumberOfVehiclesServed++;
    }

    public static int getVehiclesServed(){
        return totalNumberOfVehiclesServed;
    }

    public static void incrementProfit(Customer customer){
        totalProfit += customer.getAmountOfFuelRequired()*pricePerLiter;
    }

    public static float getProfitPerDispenser(){
        return totalProfit;
    }

    public float checkFuelLeft(){
        return repository.checkFuelLeft();
    }

    public static float getPricePerLiter() {
        return pricePerLiter;
    }

    public int getFuelType() {
        return fuelType;
    }

}
