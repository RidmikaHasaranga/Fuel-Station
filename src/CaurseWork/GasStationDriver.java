package CaurseWork;

public class GasStationDriver {
    public static void main(String[] args) {
        initialize();
    }

    private static void initialize(){
        //Creating Repository Objects
        FuelRepository dieselRepository = new FuelRepository("Diesel", 25000);
        FuelRepository petrolRepository = new FuelRepository("Petrol", 25000);

        //Creating Gas Station Manager (Owner) Object
        GasStationManager owner = new GasStationManager(petrolRepository, dieselRepository);
        WaitingQueue waitingQueue = new WaitingQueue();

        //Creating Fuel Queue Objects (Petrol & Diesel)
        FuelQueue petrolQueue1 = new FuelQueue(new String[]{"Car", "Van"}, waitingQueue);
        FuelQueue petrolQueue2 = new FuelQueue(new String[]{"Car", "Van", "Three_Wheeler", "Motor_Bike","Other_Vehicle"}, waitingQueue);
        FuelQueue petrolQueue3 = new FuelQueue(new String[]{"Three_Wheeler"}, waitingQueue);
        FuelQueue petrolQueue4 = new FuelQueue(new String[]{"Motor_Bike"}, waitingQueue);

        FuelQueue dieselQueue1 = new FuelQueue(new String[]{"Public_Transport"}, waitingQueue);
        FuelQueue dieselQueue2 = new FuelQueue(new String[]{"Other"}, waitingQueue);
        FuelQueue dieselQueue3 = new FuelQueue(new String[]{"Other"}, waitingQueue);

        //Creating Octane Fuel Dispenser Manager Objects
        OctaneFuelDispenserManager petrolDispenser1 = new OctaneFuelDispenserManager(petrolQueue1, petrolRepository, 450, "Petrol");
        OctaneFuelDispenserManager petrolDispenser2 = new OctaneFuelDispenserManager(petrolQueue2, petrolRepository, 450, "Petrol");
        OctaneFuelDispenserManager petrolDispenser3 = new OctaneFuelDispenserManager(petrolQueue3, petrolRepository, 450, "Petrol");
        OctaneFuelDispenserManager petrolDispenser4 = new OctaneFuelDispenserManager(petrolQueue4, petrolRepository, 450, "Petrol");

        //Creating Diesel Fuel Dispense Manager Objects
        DieselFuelDispenseManager dieselDispenser1 = new DieselFuelDispenseManager(dieselQueue1, dieselRepository, 430, "Diesel");
        DieselFuelDispenseManager dieselDispenser2 = new DieselFuelDispenseManager(dieselQueue2, dieselRepository, 430, "Diesel");
        DieselFuelDispenseManager dieselDispenser3 = new DieselFuelDispenseManager(dieselQueue3, dieselRepository, 430, "Diesel");

        //Creating New Dispensers for Petrol & Diesel
        owner.addOctaneDispenser(petrolDispenser1);
        owner.addOctaneDispenser(petrolDispenser2);
        owner.addOctaneDispenser(petrolDispenser3);
        owner.addOctaneDispenser(petrolDispenser4);

        owner.addDieselDispenser(dieselDispenser1);
        owner.addDieselDispenser(dieselDispenser2);
        owner.addDieselDispenser(dieselDispenser3);
    }
}
