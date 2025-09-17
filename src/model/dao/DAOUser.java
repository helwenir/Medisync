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

public class DAOUser {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOUser() {
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
    
    public User veriferIdentifiants(String login, String motDePasse) {
        String query = "SELECT * FROM users WHERE username = ? AND password_hash = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){

                return resultatSetUser(rs);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private User resultatSetUser(ResultSet rs) throws SQLException{
        User newU;
        newU = new User(rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role"),
                        rs.getInt("secouriste"),
                        rs.getInt("admin"));
        return newU;
    }

    public int createCompteSec(User user){
        String userName = user.getUsername();
        String password = user.getPassWord();
        String role = "secouriste";
        int sec = user.getSecouriste();


        String query = "INSERT INTO users(username, password_hash, role, secouriste) VALUES ('" +userName+"', '" +password+"', '" +role+"', " +sec+ ")";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public int deleteCompSec(int sec){
        String query = "DELETE FROM users WHERE secouriste = " +sec;
        try (Connection con = getConnection(); Statement st = con.createStatement()){
            return st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
            return -1;
        }
    }
}

