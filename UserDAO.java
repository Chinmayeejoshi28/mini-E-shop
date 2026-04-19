import java.sql.*;

public class UserDAO {

    public boolean register(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password) VALUES(?,?)"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int login(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("id");

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}