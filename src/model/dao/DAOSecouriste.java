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

public class DAOSecouriste {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOSecouriste() {
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
    
    public Secouriste findById(int id) {
        String query = "SELECT * FROM secouriste WHERE id = " +id;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){

                return resultatSetSecouriste(rs);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Secouriste> listSec(){
        ArrayList<Secouriste> list = new ArrayList<>();
        String query = "SELECT * FROM secouriste";
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Secouriste s = resultatSetSecouriste(rs);
                list.add(s);
            }

            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }

    public ArrayList<Integer> listId(){
        ArrayList<Integer> list = new ArrayList<>();
         String query = "SELECT id FROM secouriste";
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

    public int idDispo(){
        ArrayList<Integer> list = this.listId();
        return list.get(list.size()-1) +1;
    }

    private Secouriste resultatSetSecouriste(ResultSet rs) throws SQLException{
        Secouriste newS;
        newS = new Secouriste(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateNaissance").toString(),
                        rs.getString("email"),
                        rs.getString("tel"),
                        rs.getString("adresse"));
        return newS;
    }

    public int create(Secouriste sec){
        long id = sec.getId();
        String nom = sec.getNom();
        String prenom = sec.getPrenom();
        String dateNaissance = sec.getDateNaissance();
        String email = sec.getEmail();
        String tel = sec.getTel();
        String adresse = sec.getAdresse();

        String query = "INSERT INTO secouriste VALUES ('" +id+"', '" +nom+"', '" +prenom+"', '" +dateNaissance+"', '" +email+"', '" +tel+"', '" +adresse+ "')";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public int delete(String sec){
        String[] info = sec.split(" ");
        String nom = info[0];
        String prenom = info[1];

        String recup = "SELECT id FROM secouriste WHERE nom = '" +nom+ "' AND prenom = '" +prenom+"'";
        try(PreparedStatement stmt = conn.prepareStatement(recup)){
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                DAOUser daoUser = new DAOUser();
                daoUser.deleteCompSec(id);
                this.deleteLie(id);

                String query = "DELETE FROM secouriste WHERE id = " +id;
                try(Connection con = getConnection(); Statement st = con.createStatement()){
                    return st.executeUpdate(query);
                }catch(SQLException ex){
                    ex.printStackTrace();
                    return -1;
                }
            }

            return 1;


        }catch(SQLException ex){
            ex.printStackTrace();
            return -1;
        }
    }
    

    private int deleteLie(int id){

        String query = "DELETE FROM secouriste_competence WHERE secouriste_id = " +id;
        try(Connection con = getConnection(); Statement st = con.createStatement()){
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
            return -1;
        }

        String q = "DELETE FROM secouriste_journee WHERE secouriste_id = " +id;
        try(Connection con = getConnection(); Statement st = con.createStatement()){
            st.executeUpdate(q);
        }catch(SQLException ex){
            ex.printStackTrace();
            return -1;
        }

        String q2 = "DELETE FROM affectation WHERE secouriste_id = " +id;
        try(Connection con = getConnection(); Statement st = con.createStatement()){
            st.executeUpdate(q2);
        }catch(SQLException ex){
            ex.printStackTrace();
            return -1;
        }

        return 1;

    }

}

