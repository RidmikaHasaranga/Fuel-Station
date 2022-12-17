package CaurseWork;

import java.util.ArrayList;

public class GasStationManager {
    private ArrayList<OctaneFuelDispenserManager> octaneDispensers = new ArrayList<>();
    private ArrayList<DieselFuelDispenseManager> dieselDispenser = new ArrayList<>();
    private FuelRepository petrolRepository;
    private FuelRepository dieselRepository;

    //Constructor
    public GasStationManager(FuelRepository petrolRepository, FuelRepository dieselRepository){
        this.petrolRepository = petrolRepository;
        this.dieselRepository = dieselRepository;
    }

    //add object for OctaneFuelDispenserManager Class
    public void addOctaneDispenser(OctaneFuelDispenserManager dispenser){
        octaneDispensers.add(dispenser);
    }

    //add object for DieselFuelDispenserManager Class
    public void addDieselDispenser(DieselFuelDispenseManager dispense){
        dieselDispenser.add(dispense);
    }

    public ArrayList<OctaneFuelDispenserManager> getPetrolDispensers() {
        return octaneDispensers;
    }

    public ArrayList<DieselFuelDispenseManager> getDieselDispenser() {
        return dieselDispenser;
    }

    public FuelRepository getPetrolRepository() {
        return petrolRepository;
    }

    public FuelRepository getDieselRepository() {
        return dieselRepository;
    }
}
