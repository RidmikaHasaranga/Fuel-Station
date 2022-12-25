package CaurseWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class DbConnector {
    Connection con = null;
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
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

                OctaneFuelDispenserManager petrolDispenser = new OctaneFuelDispenserManager(petrolQueue, petrolRepository, rsPetrolDispenser.getFloat(2), 1);

                owner.addOctaneDispenser(petrolDispenser);
            }

            ResultSet rsDieselDispenser = st.executeQuery("select  * from diesel_dispenser");
            while (rsDieselDispenser.next()){
                String[] vehiclesAllowedString = rsDieselDispenser.getString(4).split(" ");
                int[] vehiclesAllowed = convertToIntArray(vehiclesAllowedString);
                FuelQueue dieselQueue = new FuelQueue(vehiclesAllowed, waitingQueue);

                DieselFuelDispenseManager dieselDispener = new DieselFuelDispenseManager(dieselQueue, dieselRepository, rsDieselDispenser.getFloat(2), 2);

                owner.addDieselDispenser(dieselDispener);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return owner;
    }

    private static int[] convertToIntArray(String[] vehiclesAllowedString){
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
}
