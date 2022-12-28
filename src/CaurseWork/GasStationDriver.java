package CaurseWork;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.time.ZoneId;
import java.util.Date;


public class GasStationDriver {
    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        dbConnector.connect();
        GasStationManager manager = dbConnector.getGasStationManager();

        //add exception handling
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------");
        System.out.println("1. Add a vehicle to the fuel queue");
        System.out.println("2. Serve Fuel");
        System.out.println("3. Get Details");
        System.out.println("4. Add a new dispenser");
        String userInput = scanner.next();

        switch (userInput) {
            case "1":
                //Add a vehicle to the fuel queue

                break;
            case "2":
                //Serve Fuel

                break;
            case "3":
                Scanner scannerCase3 = new Scanner(System.in);
                System.out.println("------------------------------------");
                System.out.println("1. Total fuel dispensed (per vehicle type according to the fuel type)");
                System.out.println("2. The vehicle that received the largest amount of fuel for the day and the type of fuel received");
                System.out.println("3. Total number of vehicles served by each dispenser along with the amount of fuel and the total income per dispenser");
                System.out.println("4. Total income of the gas station  per day per fuel type");
                System.out.println("5. Remaining stock");

                String selectedStats = scannerCase3.next();

                if (selectedStats.equals("1")) {
                    ArrayList<Detail> octaneDataArray = OctaneFuelDispenserManager.getDetailArray();
                    ArrayList<Detail> dieselDataArray = DieselFuelDispenseManager.getDetailArray();
                    float[] totalFuelDispensed = new float[12];
                    for (int i = 0; i < 12; i++) {
                        totalFuelDispensed[i] = 0;
                    }
                    for (Detail data : octaneDataArray) {
                        if (data.getCustomer().getVehicleType() == 1) {
                            totalFuelDispensed[0] += data.getCustomer().getAmountOfFuelRequired();
                        } else if (data.getCustomer().getVehicleType() == 2) {
                            totalFuelDispensed[1] += data.getCustomer().getAmountOfFuelRequired();
                        } else if (data.getCustomer().getVehicleType() == 3) {
                            totalFuelDispensed[2] += data.getCustomer().getAmountOfFuelRequired();
                        } else if (data.getCustomer().getVehicleType() == 4) {
                            totalFuelDispensed[3] += data.getCustomer().getAmountOfFuelRequired();
                        } else if (data.getCustomer().getVehicleType() == 5) {
                            totalFuelDispensed[4] += data.getCustomer().getAmountOfFuelRequired();
                        } else if (data.getCustomer().getVehicleType() == 6) {
                            totalFuelDispensed[5] += data.getCustomer().getAmountOfFuelRequired();
                        }
                    }
                    for (Detail detail : dieselDataArray) {
                        if (detail.getCustomer().getVehicleType() == 1) {
                            totalFuelDispensed[6] += detail.getCustomer().getAmountOfFuelRequired();
                        } else if (detail.getCustomer().getVehicleType() == 2) {
                            totalFuelDispensed[7] += detail.getCustomer().getAmountOfFuelRequired();
                        } else if (detail.getCustomer().getVehicleType() == 3) {
                            totalFuelDispensed[8] += detail.getCustomer().getAmountOfFuelRequired();
                        } else if (detail.getCustomer().getVehicleType() == 4) {
                            totalFuelDispensed[9] += detail.getCustomer().getAmountOfFuelRequired();
                        } else if (detail.getCustomer().getVehicleType() == 5) {
                            totalFuelDispensed[10] += detail.getCustomer().getAmountOfFuelRequired();
                        } else if (detail.getCustomer().getVehicleType() == 6) {
                            totalFuelDispensed[11] += detail.getCustomer().getAmountOfFuelRequired();
                        }
                    }
                    System.out.println("\nPetrol Vehicles;");
                    System.out.println("\nCar: " + totalFuelDispensed[0]);
                    System.out.println("\nVan: " + totalFuelDispensed[1]);
                    System.out.println("\nThree Wheel: " + totalFuelDispensed[2]);
                    System.out.println("\nMotor Bike: " + totalFuelDispensed[3]);
                    System.out.println("\nPublic Transport: " + totalFuelDispensed[4]);
                    System.out.println("\nOther Vehicles: " + totalFuelDispensed[5]);
                    System.out.println("----------");
                    System.out.println("\nDiesel Vehicles;");
                    System.out.println("Car: " + totalFuelDispensed[6]);
                    System.out.println("\nVan: " + totalFuelDispensed[7]);
                    System.out.println("\nThree Wheel: " + totalFuelDispensed[8]);
                    System.out.println("\nMotor Bike: " + totalFuelDispensed[9]);
                    System.out.println("\nPublic Transport: " + totalFuelDispensed[10]);
                    System.out.println("\nOther Vehicles: " + totalFuelDispensed[11]);
                }

                else if (selectedStats.equals("2")){
                    Date date = new Date();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    ArrayList<Detail> petrolDataArray = OctaneFuelDispenserManager.getDetailArray();
                    ArrayList<Detail> dieselDataArray = DieselFuelDispenseManager.getDetailArray();
                    String highestServedFuel = "Petrol";
                    float highestServed = 0;
                    for (Detail detail : petrolDataArray) {
                        DateTime customerDate = detail.getDateTime();
                        if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                            if (detail.getCustomer().getAmountOfFuelRequired() > highestServed) {
                                highestServed = (float) detail.getCustomer().getAmountOfFuelRequired();
                            }
                        }
                    }
                    for (Detail detail : dieselDataArray) {
                        DateTime customerDate = detail.getDateTime();
                        if (customerDate.getYear() == localDate.getYear() && customerDate.getMonth() == localDate.getMonthValue() && customerDate.getDate() == localDate.getDayOfMonth()) {
                            if (detail.getCustomer().getAmountOfFuelRequired() > highestServed) {
                                highestServed = (float) detail.getCustomer().getAmountOfFuelRequired();
                                highestServedFuel = "Diesel";
                            }
                        }
                    }
                    if (highestServed > 0) {
                        System.out.println(highestServed + " litres of " + highestServedFuel);
                    } else {
                        System.out.println("No Customers today");
                    }
                }

                else if (selectedStats.equals("3")){
                    ArrayList<Detail> petrolDataArray = OctaneFuelDispenserManager.getDetailArray();
                    ArrayList<Detail> dieselDataArray = DieselFuelDispenseManager.getDetailArray();
                    ArrayList<float[]> petrolDispenserIncome = new ArrayList<>();
                    ArrayList<float[]> dieselDispenserIncome = new ArrayList<>();
                    for (int i = 0; i < manager.getPetrolDispensers().size(); i++) {
                        petrolDispenserIncome.add(new float[]{0, 0});
                    }
                    for (int i = 0; i < manager.getDieselDispenser().size(); i++) {
                        dieselDispenserIncome.add(new float[]{0, 0});
                    }
                    for (Detail detail : petrolDataArray) {
                        int dispenserID = Integer.parseInt(detail.getDispenserID());
                        petrolDispenserIncome.get(dispenserID)[1]++;
                        petrolDispenserIncome.get(dispenserID)[0] += detail.getCustomer().getAmountOfFuelRequired();
                    }
                    for (Detail detail : dieselDataArray) {
                        int dispenserID = Integer.parseInt(detail.getDispenserID());
                        dieselDispenserIncome.get(dispenserID)[1]++;
                        dieselDispenserIncome.get(dispenserID)[0] += detail.getCustomer().getAmountOfFuelRequired();
                    }
                    for (int i = 0; i < manager.getPetrolDispensers().size(); i++) {
                        int number = i + 1;
                        System.out.println("\nPetrol Dispenser No: " + number);
                        System.out.println("\nTotal Profit: " + petrolDispenserIncome.get(i)[0] * OctaneFuelDispenserManager.getPricePerLiter());
                        System.out.println("\nTotal Number of Vehicles Served: " + petrolDispenserIncome.get(i)[1]);
                        System.out.println("\nTotal Fuel Served: " + petrolDispenserIncome.get(i)[0]);
                    }
                    System.out.println("----------");
                    for (int i = 0; i < manager.getDieselDispenser().size(); i++) {
                        int number = i + 1;
                        System.out.println("\nDiesel Dispenser " + number);
                        System.out.println("\nTotal Number of Vehicles Served : " + dieselDispenserIncome.get(i)[1]);
                        System.out.println("\nTotal Fuel Served : " + dieselDispenserIncome.get(i)[0]);
                        System.out.println("\nTotal Profit : " + dieselDispenserIncome.get(i)[0] * DieselFuelDispenseManager.getPricePerLiter());
                    }
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

        }
    }

}
