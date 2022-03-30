package ca.bcit.comp2522.termproject.christiebelal;

import java.sql.*;
import java.util.Properties;

/**
 * Demonstrates how to communicate with a MySQL database installed on localhost.
 *
 * I have created a MySQL poweruser for you. This MySQL poweruser can create new databases on my computer.
 *
 * Username: comp2522
 * Password: I was born in 1973
 *
 * I've included an sql file called comp2522.sql that you can use in MySQL Workbench on your development
 * computer to create the simple schema. There is one table. You will need to add the poweruser to your
 * local development rig in order to test your code before submitting it.
 *
 * I would like you to modify the Java program you are reading so that it adds a row to the table called users.
 *
 * It's just a proof of concept. Easy-peasy. That's all!
 *
 * First you must download the MySQL Java JDBC Connector from here: https://www.mysql.com/products/connector/
 *
 * Unzip it, and add the jar file in it to the list of libraries in your project properties.
 *
 * I will execute your code on my computer. Hopefully your program will add a row to my table!
 *
 * @author BCIT
 * @version 2022
 */
public final class DatabaseExample {

    private DatabaseExample() { }

    /**
     * Drives the example.
     *
     * @param args unused
     * @throws SQLException if a connection cannot be made.
     * @throws ClassNotFoundException if the MySQL ConnectorJ JDBC JAR cannot be found, i.e., is not on classpath
     */
    public static void main(final String[] args) throws SQLException, ClassNotFoundException {

        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String URL = "jdbc:mysql://localhost:3306/comp2522";

        // We need to send a user and a password when we try to connect!
        final Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "admin");

        // We establish a connection...
        final Connection connection = DriverManager.getConnection(URL, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database test");
        }

        // Create a statement to send on the connection...
        Statement stmt = connection.createStatement();

        // Execute the statement and receive the result...
//        stmt.executeUpdate("INSERT INTO users VALUES('belal', 'how do we even implement this into our games');");

        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        // And then display the result!
        System.out.println("user_id\t\tpassword");
        while (rs.next()) {
            String userID = rs.getString("user_id");
            String password = rs.getString("password");
            System.out.println(userID + "\t\t" + password);
        }
    }
}
