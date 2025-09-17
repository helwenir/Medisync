package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOSport {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOSport() {
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

    public ArrayList<Sport> listSport(){
        ArrayList<Sport> list = new ArrayList<>();
        String query = "SELECT * FROM sport";
         try(PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                list.add(this.resultatSet(rs));
            }

            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }

    public String getCodeByNom(String nom){
        String query = "SELECT code FROM sport WHERE nom = '" +nom+"'";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }

            return null;
        }catch( Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Sport resultatSet(ResultSet rs) throws SQLException{
        Sport newC;
        newC = new Sport(rs.getString(1),
                        rs.getString(2));
        return newC;
    }
    
    

}

