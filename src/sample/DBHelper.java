package sample;
import java.sql.*;
public class DBHelper {

    private Connection conn;
    private Statement statement;

    public DBHelper(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/merairctc?autoReconnect=true&useSSL=false","root","");

            statement = conn.createStatement();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public boolean login(String email, String password){

        try {
            ResultSet set = statement.executeQuery("SELECT * FROM client WHERE email LIKE" + "'"+ email + "'" + "AND password LIKE" + "'" + password+ "'");
            //If no rows entered then set.next() will fully empty!
            if (!set.next()){
                return false;
            }else{
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    public String searchname(String email){
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM client WHERE email LIKE '"+ email + "'");
            rs.next();
            return (rs.getString("name"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "some error";
        }
    }

    public String searchuid (String email){
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM client WHERE email LIKE '"+ email + "'");
            rs.next();
            return (rs.getString("userid"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "some error";
        }
    }


    public boolean register(String name,String email, String password){
        try {
            //This is used to refer to the current dbhelper object
            statement.executeUpdate("INSERT INTO client (userid,name,email,password) VALUES (null," + "'"+ name + "'"+ ","+ "'" + email + "'" + ","+ "'"+ password+"'"+")");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public  void insert(String tr, String em){
        try {
            statement.executeUpdate("UPDATE client SET oldtrain='"+tr+"' WHERE email ='"+em+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String searchtno(String email){
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM client WHERE email LIKE '"+ email + "'");
            rs.next();
            return (rs.getString("oldtrain"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "some error";
        }
    }

    public  void insertstn(String st, String em){
        try {
            statement.executeUpdate("UPDATE client SET oldstn='"+st+"' WHERE email ='"+em+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String searchstn(String email){
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM client WHERE email LIKE '"+ email + "'");
            rs.next();
            return (rs.getString("oldstn"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "some error";
        }
    }
}
