package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOSite {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOSite() {
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

    public ArrayList<Site> listSite(){
        ArrayList<Site> list = new ArrayList<>();
        String query = "SELECT * FROM site";
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
        String query = "SELECT code FROM site WHERE nom = '" +nom+"'";

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

    private Site resultatSet(ResultSet rs) throws SQLException{
        Site newC;
        newC = new Site(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4));
        return newC;
    }
    
    

}

