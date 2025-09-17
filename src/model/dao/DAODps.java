package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import persistence.*;

public class DAODps {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAODps() {
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

    public int create(DPS dps){
        int id = dps.getId();
        int hDep = dps.getHoraireDepart();
        int hFin = dps.getHoraireFin();
        int j = dps.getJour();
        int m = dps.getMois();
        int a = dps.getAnnee();
        String site = dps.getSite();
        String sport = dps.getSport();

        String q = "SELECT * FROM journee WHERE jour =" +j+ " AND mois =" +m+ " AND annee = " +a;
        boolean existe = false;
        try(PreparedStatement stmt = conn.prepareStatement(q)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                existe = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        if(!existe){
            DAOJournee daoJour = new DAOJournee();
            daoJour.create( new Journee(j, m, a));
        }



        String query = "INSERT INTO dps VALUES (" +id+ ", " +hDep+ ", " +hFin+ "," +j+ "," +m+ "," +a+", '"+site+ "', '" +sport+ "')";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    /*
    public int delete(DPS dps){
        
    }*/

    public ArrayList<DPS> getListDps(){
        ArrayList<DPS> list = new ArrayList<>();

        String query = "SELECT * FROM dps";
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

    public ArrayList<Integer> getIdDps(){
        ArrayList<Integer> list = new ArrayList<>();

        String query = "SELECT id FROM dps ";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                list.add(rs.getInt(1));
            }

            Collections.sort(list);
            return list;

        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }

    private DPS resultatSet(ResultSet rs) throws SQLException{
        DPS newD;
        newD = new DPS(rs.getInt("id"),
                        rs.getInt("horaire_depart"),
                        rs.getInt("horaire_fin"),
                        rs.getInt("jour"),
                        rs.getInt("mois"),
                        rs.getInt("annee"),
                        rs.getString("site_code"),
                        rs.getString("sport_code"));
        return newD;
    }



}
