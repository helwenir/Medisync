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

public class DAOSecouriste_Competence {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOSecouriste_Competence() {
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
    
    public int create(Secouriste sec, Competences comp){
        int idsec = sec.getId();
        String compS = comp.getIntitule();


        String query = "INSERT INTO secouriste_competence VALUES (" +idsec+ ", '" + compS+ "')";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                sec.addCompetence(comp);
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public int delete(Secouriste sec, Competences comp){
        int idsec = sec.getId();
        String compS = comp.getIntitule();


        String query = "DELETE FROM secouriste_competence WHERE secouriste_id =" +idsec+ "AND competence_intitule = '" + compS+ "'";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                sec.removeCompetence(comp);
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

}

