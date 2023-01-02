package CaurseWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DbConnector {
    Connection con = null;
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOP_CW?useSSL=false&serverTimezone=Asia/Colombo", "root", "iit#20210763");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public GasStationManager getGasStationManager() {
        GasStationManager owner = null;
        FuelRepository petrolRepository = null;
        FuelRepository dieselRepository = null;
        try {
            Statement st = con.createStatement();

            ResultSet rsFuelRepository = st.executeQuery("select * from fuel_repository");
            while (rsFuelRepository.next()){
                if (rsFuelRepository.getInt(2) == 1){
                    petrolRepository = new FuelRepository(rsFuelRepository.getInt(2), rsFuelRepository.getInt(3));
                } else {
                    dieselRepository = new FuelRepository(rsFuelRepository.getInt(2), rsFuelRepository.getInt(3));
                }
            }

            owner = new GasStationManager(petrolRepository, dieselRepository);
            WaitingQueue waitingQueue = new WaitingQueue();

            ResultSet rsPetrolDispenser = st.executeQuery("select * from petrol_dispenser");
            while (rsPetrolDispenser.next()){
                String[] vehiclesAllowedString = rsPetrolDispenser.getString(4).split(" ");
                int[] vehiclesAllowed = convertToIntArray(vehiclesAllowedString);
                FuelQueue petrolQueue = new FuelQueue(vehiclesAllowed, waitingQueue);

                OctaneFuelDispenserManager petrolDispenser = new OctaneFuelDispenserManager(petrolQueue, petrolRepository, rsPetrolDispenser.getFloat(2), 1, rsPetrolDispenser.getString(1));

                owner.addOctaneDispenser(petrolDispenser);
            }

            ResultSet rsDieselDispenser = st.executeQuery("select * from diesel_dispenser");
            while (rsDieselDispenser.next()){
                String[] vehiclesAllowedString = rsDieselDispenser.getString(4).split(" ");
                int[] vehiclesAllowed = convertToIntArray(vehiclesAllowedString);
                FuelQueue dieselQueue = new FuelQueue(vehiclesAllowed, waitingQueue);

                DieselFuelDispenseManager dieselDispener = new DieselFuelDispenseManager(dieselQueue, dieselRepository, rsDieselDispenser.getFloat(2), 2, rsDieselDispenser.getString(1));

                owner.addDieselDispenser(dieselDispener);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return owner;
    }

    public static int[] convertToIntArray(String[] vehiclesAllowedString){
        int[] vehiclesAllowed = new int[vehiclesAllowedString.length];
        for (int i=0; i<vehiclesAllowedString.length; i++){
            vehiclesAllowed[i] = Integer.parseInt(vehiclesAllowedString[i]);
        }
        return vehiclesAllowed;
    }

    public void addPetrolDispenser(int dispenserNo, float pricePerLiter, String vehiclesAllowed){
        String query = "insert into petrol_dispenser values (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1 , dispenserNo);
            pst.setFloat(2, pricePerLiter);
            pst.setInt(3, 1);
            pst.setString(4, vehiclesAllowed);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addDieselDispenser(int dispenserNo, float pricePerLiter, String vehiclesAllowed){
        String query = "insert into diesel_dispenser values (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1 , dispenserNo);
            pst.setFloat(2, pricePerLiter);
            pst.setInt(3, 2);
            pst.setString(4, vehiclesAllowed);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addDetailsArray() {
        ArrayList<Detail> petrolDataArray = new ArrayList<>();
        ArrayList<Detail> dieselDataArray = new ArrayList<>();
        ArrayList<Integer> customerIDs = new ArrayList<>();
        ArrayList<DateTime> dateTimes = new ArrayList<>();
        ArrayList<String> dispenserIds = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rsServedInfo = st.executeQuery("select * from served_details");
            while (rsServedInfo.next()) {
                DateTime dateTime = new DateTime(rsServedInfo.getInt(3), rsServedInfo.getInt(4), rsServedInfo.getInt(5));
                dateTimes.add(dateTime);
                customerIDs.add(rsServedInfo.getInt(2));
                dispenserIds.add(rsServedInfo.getString(6));
            }
            for (int i = 0; i < customerIDs.size(); i++) {
                ResultSet rsCustomer = st.executeQuery("SELECT * FROM programming_coursework.customer WHERE customer_id=" + customerIDs.get(i));
                rsCustomer.next();
                Customer customer = new Customer(rsCustomer.getInt(2), rsCustomer.getInt(3), rsCustomer.getInt(4), rsCustomer.getFloat(5), true);
                Detail detail = new Detail(customer, dateTimes.get(i), dispenserIds.get(i));
                if (rsCustomer.getInt(4) == 1) {
                    petrolDataArray.add(detail);
                } else if (rsCustomer.getInt(4) == 2) {
                    dieselDataArray.add(detail);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        OctaneFuelDispenserManager.addDetail(petrolDataArray);
        DieselFuelDispenseManager.addDetail(dieselDataArray);
    }

    public void addCustomerToQueue(GasStationManager manager, Customer customer){
        if (customer.getFuelType() == 1) {
            int count = 1;
            ArrayList<OctaneFuelDispenserManager> allFuelDispensers = manager.getPetrolDispensers();
            ArrayList<OctaneFuelDispenserManager> availableFuelDispensers = new ArrayList<>();
            Scanner scanner5 = new Scanner(System.in);
            for (OctaneFuelDispenserManager dispenser : allFuelDispensers) {
                int[] vehiclesAllowed = dispenser.getFuelQueue().getVehiclesAllowed();
                for (int j = 0; j < vehiclesAllowed.length; j++) {
                    if (vehiclesAllowed[j] == customer.getVehicleType()) {
                        if (dispenser.getFuelQueue().getFreeSpace() > 0) {
                            System.out.println(count + ". 92 Octane Dispenser " + j + "(" + dispenser.getFuelQueue().getFreeSpace() + " free spaces)");
                            assert false;
                            availableFuelDispensers.add(dispenser);
                            count++;
                        }
                    }
                }
            }
            if (count > 1) {
                int selectedDispenserString;
                while (true) {
                    try {
                        scanner5 = new Scanner(System.in);
                        selectedDispenserString = scanner5.nextInt();
                        if (selectedDispenserString > 0 && selectedDispenserString <= count-1) {
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                    }
                }
                assert false;
                OctaneFuelDispenserManager selectedDispener = availableFuelDispensers.get(selectedDispenserString-1);
                selectedDispener.getFuelQueue().addCustomer(customer);
                customer.getTicket();
            } else {
                allFuelDispensers.get(0).getFuelQueue().getWaitingQueue().addCustomer(customer);
            }
        } else if (customer.getFuelType() == 2) {
            int count = 1;
            ArrayList<DieselFuelDispenseManager> allFuelDispensers = manager.getDieselDispenser();
            ArrayList<DieselFuelDispenseManager> availableFuelDispensers = null;
            Scanner scanner5 = new Scanner(System.in);
            for (DieselFuelDispenseManager dispenser : allFuelDispensers) {
                int[] vehiclesAllowed = dispenser.getFuelQueue().getVehiclesAllowed();
                for (int j = 0; j < vehiclesAllowed.length; j++) {
                    if (vehiclesAllowed[j] == customer.getVehicleType()) {
                        if (dispenser.getFuelQueue().getFreeSpace() > 0) {
                            System.out.println(count + ". Diesel Dispenser " + j + "(" + dispenser.getFuelQueue().getFreeSpace() + " free spaces)");
                            assert false;
                            availableFuelDispensers.add(dispenser);
                            count++;
                        }
                    }
                }
            }
            if (count > 1) {
                int selectedDispenserString;
                while (true) {
                    try {
                        scanner5 = new Scanner(System.in);
                        selectedDispenserString = scanner5.nextInt();
                        if (selectedDispenserString > 0 && selectedDispenserString <= count-1) {
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                    }
                }
                assert false;
                DieselFuelDispenseManager selectedDispener = availableFuelDispensers.get(selectedDispenserString-1);
                selectedDispener.getFuelQueue().addCustomer(customer);
                customer.getTicket();
            } else {
                allFuelDispensers.get(0).getFuelQueue().getWaitingQueue().addCustomer(customer);
            }
        }
    }
}
