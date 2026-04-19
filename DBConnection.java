import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ecommerce",
                "root",
                "root"   // replace with your actual password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}