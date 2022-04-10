package ca.bcit.comp2522.termproject.christiebelal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Handles the behavior of spawned animals.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public final class DatabaseHandler {
    private DatabaseHandler() {
    }

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String url = "jdbc:mysql://localhost:3306/animalhustlerdatabase";

        // We need to send a user and a password when we try to connect!
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "girlguide");

        // We establish a connection...
        Connection connection = DriverManager.getConnection(url, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database test");
        }
        return connection;
    }

    /**
     * Checks the username and password.
     *
     * @param usernameEntry must be a string representing the user's username
     * @param passwordEntry must be a string representing the user's password
     * @return Boolean value of True or False
     * @throws ClassNotFoundException if class with the specified name cannot be found
     * @throws SQLException           if there is a database error
     */
    public static boolean checkUserNamePassword(final String usernameEntry, final String passwordEntry) throws
            ClassNotFoundException, SQLException {

        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id = '" + usernameEntry
                + "'AND password = '" + passwordEntry + "'");
        return rs.next();
    }

    /**
     * Creates username.
     *
     * @param usernameEntry must be a string representing the user's username
     * @param passwordEntry must be a string representing the user's password
     * @return boolean value of True or False
     * @throws ClassNotFoundException if class with the specified name cannot be found
     * @throws SQLException           if there is a database error
     */
    public static boolean createUserName(final String usernameEntry, final String passwordEntry) throws SQLException,
            ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE user_id = '" + usernameEntry + "'");
        if (!rs.next()) {
            stmt.executeUpdate("INSERT INTO users VALUES('" + usernameEntry + "', '" + passwordEntry + "')");
            return true;
        }
        return false;
    }

    /**
     * Adds the user's score to the database at the end of the game.
     *
     * @param usernameEntry must be a string representing the user's username
     * @param score         must be an integer representing the user's score
     * @throws ClassNotFoundException if class with the specified name cannot be found
     * @throws SQLException           if there is a database error
     */
    public static void addScore(final String usernameEntry, final int score) throws SQLException,
            ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        stmt.executeUpdate("INSERT INTO userscores VALUES('" + usernameEntry + "', " + score + ")");
    }

    /**
     * Returns the top 5 scores and usernames.
     *
     * @return an Array List of String containing the top 5 scores
     * @throws ClassNotFoundException if class with the specified name cannot be found
     * @throws SQLException           if there is a database error
     */
    public static ArrayList<String> getTopScores() throws SQLException, ClassNotFoundException {
        ArrayList<String> topScores = new ArrayList<>();
        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * \n"
                + "FROM userscores\n"
                + "ORDER BY scores DESC\n"
                + "LIMIT 5");
        while (rs.next()) {
            topScores.add(rs.getString("user_id"));
            topScores.add(rs.getString("scores"));
        }
        return topScores;
    }
}
