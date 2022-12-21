package CaurseWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
