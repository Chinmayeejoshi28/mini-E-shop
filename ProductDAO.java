import java.sql.*;

public class ProductDAO {

    public void viewProducts() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");

            while(rs.next()) {
                System.out.println(
                    rs.getInt("id") + ". " +
                    rs.getString("name") + " - ₹" +
                    rs.getDouble("price")
                );
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}