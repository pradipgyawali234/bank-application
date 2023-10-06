package demo1;
import java.sql.*;

public class Main1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "root", ""
            );
            String sql = "UPDATE tb SET id = ?,  name = ? WHERE id = 1";
            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setInt(1, 2);

            statement.setString(2, "DUIKU");
            statement.executeUpdate();
            System.out.println("Record updated.");

            connection.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}