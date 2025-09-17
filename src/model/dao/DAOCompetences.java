package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOCompetences {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOCompetences() {
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
    
    public int create(Competences comp){
        String intitule = comp.getIntitule();


        String query = "INSERT INTO competence VALUES ('" +intitule+ "')";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public ArrayList<Competences> listComp(){
        
        ArrayList<Competences> list = new ArrayList<>();
        String query = "SELECT * FROM competence";
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

    private Competences resultatSet(ResultSet rs) throws SQLException{
        Competences newC;
        newC = new Competences(rs.getString(1));
        return newC;
    }

}

