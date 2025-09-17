package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAOSecouriste_Journee {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOSecouriste_Journee() {
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
    
    public int create(Secouriste sec, Journee jour){
        int idsec = sec.getId();
        int j = jour.getJour();
        int m = jour.getMois();
        int a = jour.getAnnee();

        DAOJournee  dao=new DAOJournee();
        dao.create(jour);

        String query = "INSERT INTO secouriste_journee VALUES (" +idsec+ ", " + j+ ", " +m+", " +a+")";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public int delete(Secouriste sec, Journee jour){
        int idsec = sec.getId();
        int j = jour.getJour();
        int m = jour.getMois();
        int a = jour.getAnnee();
        int i=1;

        String query = "DELETE FROM secouriste_journee WHERE secouriste_id =" +idsec+ " AND jour = " + j+ " AND mois = " +m+" AND annee = " +a+";";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public ArrayList<Journee> listDispoBySec(int id){
        ArrayList<Journee> list = new ArrayList<>();
        String query = "SELECT * from secouriste_journee  WHERE secouriste_id  = " +id;
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                

                list.add(this.setResult(rs));
            }
            
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }

    private Journee setResult(ResultSet rs)throws SQLException{
        Journee newS;
        newS = new Journee(rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
        return newS;

    }

}

