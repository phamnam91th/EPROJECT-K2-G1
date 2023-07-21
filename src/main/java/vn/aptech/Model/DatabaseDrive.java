package vn.aptech.Model;

import java.sql.*;

public class DatabaseDrive {
    private static final String url = "jdbc:mysql://localhost:3306/digishop";
    private static final String userName = "root";
    private static final String password = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        try(conn) {
            String sql = "select * from category";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        }
    }
}
