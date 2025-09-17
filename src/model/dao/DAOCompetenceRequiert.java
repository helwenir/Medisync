package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import persistence.*;

public class DAOCompetenceRequiert {
    private Connection conn;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd_sae_secouristes";
    private static String username = "admin";
    private static String password = "admin_hash";


    public DAOCompetenceRequiert() {
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
    
    public int create(Competences comp, Competences compReq){
        String compS = comp.getIntitule();
        String compReqS = compReq.getIntitule();


        String query = "INSERT INTO competenceRequiert VALUES ('" +compS+ "', '" +compReqS+ "')";
        try(Connection con = getConnection();
            Statement st = con.createStatement()){
                return st.executeUpdate(query);
         }catch(SQLException  ex){
            ex.printStackTrace();
            return -1;
         }
    }

    public Map<Competences, ArrayList<Competences>> listCompReq(){
        
        DAOCompetences daoComp = new DAOCompetences();
        ArrayList<Competences> list = daoComp.listComp();
        Map<Competences, ArrayList<Competences>> mapComp = new HashMap<>();

        for(Competences c : list){
            String query = "SELECT compRep FROM competenceRequiert WHERE laComp = '" +c.getIntitule();
            ArrayList<Competences> voisin = new ArrayList<>();
            try(PreparedStatement stmt = conn.prepareStatement(query)){

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    voisin.add(this.resultatSet(rs));
                }

                mapComp.put(c, voisin);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        
        }

        return mapComp;
         
    }

    private Competences resultatSet(ResultSet rs) throws SQLException{
        Competences newC;
        newC = new Competences(rs.getString(1));
        return newC;
    }

}

