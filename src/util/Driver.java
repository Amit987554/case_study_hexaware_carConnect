package util;
import java.sql.SQLException;
public class Driver {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Loading Driver Class
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Loaded driver successfully");
    }
}
