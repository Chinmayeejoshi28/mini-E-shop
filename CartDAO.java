import java.sql.*;

public class CartDAO {

    public void addToCart(int userId, int productId, int qty) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO cart(user_id, product_id, quantity) VALUES(?,?,?)"
            );
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, qty);
            ps.executeUpdate();

            System.out.println("Product added to cart.");

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewCart(int userId) {
        try {
            Connection con = DBConnection.getConnection();

            String query =
                "SELECT p.name, p.price, c.quantity " +
                "FROM cart c JOIN products p ON c.product_id=p.id " +
                "WHERE c.user_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            double subtotal = 0;

            while(rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");

                System.out.println(name + " x " + qty + " = ₹" + (price * qty));
                subtotal += price * qty;
            }

            double delivery = 50;
            double total = subtotal + delivery;

            System.out.println("Subtotal: ₹" + subtotal);
            System.out.println("Delivery: ₹" + delivery);
            System.out.println("Total: ₹" + total);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkout(int userId) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM cart WHERE user_id=?"
            );
            ps.setInt(1, userId);
            ps.executeUpdate();

            System.out.println("Checkout complete!");

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}