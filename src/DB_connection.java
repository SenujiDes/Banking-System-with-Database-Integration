//Database connection
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {
    public static Connection con; // as this is static, we can call this without creating an object
    public static String  connectionString = "jdbc:mysql://localhost:3306/school_management"; //the database which it goes to"
    private static String   username = "root";
    private static String   password = "";

    public static Connection connect() { // Added a method to handle the connection logic
        try {
            con = DriverManager.getConnection(connectionString, username, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
