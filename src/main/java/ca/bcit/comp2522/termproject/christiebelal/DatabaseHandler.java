package ca.bcit.comp2522.termproject.christiebelal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author BCIT
 * @version 2022
 */
public final class DatabaseHandler {

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String URL = "jdbc:mysql://localhost:3306/animalhustlerdatabase";

        // We need to send a user and a password when we try to connect!
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "admin");

        // We establish a connection...
        Connection connection = DriverManager.getConnection(URL, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database test");
        }
        return connection;
    }

    public static boolean checkUserNamePassword(String usernameEntry, String passwordEntry) throws ClassNotFoundException, SQLException {

        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id = '" + usernameEntry + "' AND password = '" + passwordEntry + "'");
        boolean works = rs.next();
        return works;
    }

    public static boolean CreateUserName(String usernameEntry, String passwordEntry) throws SQLException, ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE user_id = '" + usernameEntry + "'");
        if (!rs.next()) {
            stmt.executeUpdate("INSERT INTO users VALUES('" + usernameEntry + "', '" + passwordEntry + "')");
            return true;
        }
        return false;
    }

    public static void addScore(String usernameEntry, int score) throws SQLException, ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        stmt.executeUpdate("INSERT INTO userscores VALUES('" + usernameEntry + "', " + score + ")");
    }

    public static ArrayList<String> getTopScores() throws SQLException, ClassNotFoundException {
        ArrayList<String> topScores = new ArrayList<>();
        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * \n" +
                "FROM userscores\n" +
                "ORDER BY scores DESC\n" +
                "LIMIT 5");
        while (rs.next()) {
            topScores.add(rs.getString("user_id"));
            topScores.add(rs.getString("scores"));
        }
        return topScores;
    }
}
