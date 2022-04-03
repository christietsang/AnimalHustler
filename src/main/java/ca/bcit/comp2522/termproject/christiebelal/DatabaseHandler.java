package ca.bcit.comp2522.termproject.christiebelal;

import java.sql.*;
import java.util.Properties;

/**
 * Demonstrates how to communicate with a MySQL database installed on localhost.
 * <p>
 * I have created a MySQL poweruser for you. This MySQL poweruser can create new databases on my computer.
 * <p>
 * Username: comp2522
 * Password: I was born in 1973
 * <p>
 * I've included an sql file called comp2522.sql that you can use in MySQL Workbench on your development
 * computer to create the simple schema. There is one table. You will need to add the poweruser to your
 * local development rig in order to test your code before submitting it.
 * <p>
 * I would like you to modify the Java program you are reading so that it adds a row to the table called users.
 * <p>
 * It's just a proof of concept. Easy-peasy. That's all!
 * <p>
 * First you must download the MySQL Java JDBC Connector from here: https://www.mysql.com/products/connector/
 * <p>
 * Unzip it, and add the jar file in it to the list of libraries in your project properties.
 * <p>
 * I will execute your code on my computer. Hopefully your program will add a row to my table!
 *
 * @author BCIT
 * @version 2022
 */
public final class DatabaseHandler {

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String URL = "jdbc:mysql://localhost:3306/comp2522";

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
        System.out.println(works);
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
}
