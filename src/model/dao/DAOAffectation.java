package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOAffectation {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOAffectation() {
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

    public int create(Secouriste sec, DPS dps){
        int s = sec.getId();
        int d = dps.getId();


        String query = "INSERT INTO affectation VALUES (" +s+ ", " + d+ ")";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                sec.addDps(dps);
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public int delete(Secouriste sec, DPS dps){
        int idsec = sec.getId();
        int idDps = dps.getId();


        String query = "DELETE FROM affectation WHERE secouriste_id =" +idsec+ "AND dps_id = " + idDps;
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                sec.removeDps(dps);
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public ArrayList<Integer> findDpsBySec(int sec){
        String query = "SELECT dps_id FROM affectation WHERE secouriste_id = " +sec;
        ArrayList<Integer> list = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                

                list.add(rs.getInt(1));
            }
            
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }



}
