package CaurseWork;

import java.util.ArrayList;
import java.util.Scanner;

public class GasStationDriver {
    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        dbConnector.connect();
        GasStationManager manager = dbConnector.getGasStationManager();

        //add exception handling
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------");
        System.out.println("1. Add a vehicle to the fuel queue");
        System.out.println("2. Serve a vehicle");
        System.out.println("3. See the statistics");
        System.out.println("4. Add new dispenser");
        String userInput = scanner.next();

        switch (userInput) {
            case "1":
                //TO-DO
                //code to add a vehicle
                //add exception handling

                break;
            case "2":
                //TO-DO
                //code to serve a vehicle
                //add exception handling
                break;
            case "3":
                //TO-DO
                //code to see stats
                //add exception handling
                Scanner scannerCase3 = new Scanner(System.in);
                System.out.println("------------------------------------");
                System.out.println("1. Total fuel dispensed per vehicle type per fuel type");
                System.out.println("2. The vehicle that received the largest amount of fuel for the day and the type of fuel received");
                System.out.println("3. Total number of vehicles served by each dispenser along with the amount of fuel and the total income per dispenser");
                System.out.println("4. Total income of the gas station  per day per fuel type");
                System.out.println("5. Remaining stock");
                String selectedStats = scannerCase3.next();
                if (selectedStats.equals("1")) {

                }
                else if (selectedStats.equals("2")){

                }
                else if (selectedStats.equals("3")){

                }
                else if (selectedStats.equals("4")){
                    float petrolIncome = 0;
                    float dieselIncome = 0;
                    ArrayList<Detail> petrolDataArray = OctaneFuelDispenserManager.getDetailArray();
                    ArrayList<Detail> dieselDataArray = DieselFuelDispenseManager.getDetailArray();
                    for (int i=0; i<petrolDataArray.size(); i++){
                        petrolIncome += petrolDataArray.get(i).getCustomer().getAmountOfFuelRequired() * OctaneFuelDispenserManager.getPricePerLiter();
                    }
                    for (int i=0; i<dieselDataArray.size(); i++){
                        dieselIncome += petrolDataArray.get(i).getCustomer().getAmountOfFuelRequired() * OctaneFuelDispenserManager.getPricePerLiter();
                    }
                    System.out.println("Total Income from Petrol Dispensers : " + petrolIncome);
                    System.out.println("Total Income from Diesel Dispensers : " + dieselIncome);
                }
                else if (selectedStats.equals("5")){
                    float petrolStock = manager.getPetrolRepository().checkFuelLeft();
                    float dieselStock = manager.getDieselRepository().checkFuelLeft();
                    System.out.println("Petrol Stock Left : " + petrolStock);
                    System.out.println("Diesel Stock Left : " + dieselStock);
                }
                break;
            default:
                //TO-DO
                //exception
        }
    }

}
