package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOBesoin {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOBesoin() {
        try {
            conn =getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    protected Connection getConnection() throws SQLException {
        // charger la calsse du pilote
        try{
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        // obtenir la connection
        return DriverManager.getConnection(url, username, password);
    }
    
    public int create(Besoin besoin){
        int dps = besoin.getDps();
        String comp = besoin.getComp();
        int nombre = besoin.getNombre();


        String query = "INSERT INTO besoin VALUES (" +dps+ ", '" + comp+ "', " +nombre+ ")";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

}

