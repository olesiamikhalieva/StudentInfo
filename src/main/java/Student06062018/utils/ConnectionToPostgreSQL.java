package Student06062018.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionToPostgreSQL {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String driverName = "org.postgresql.Driver";
    private static String username = "postgres";
    private static String password = "qwerty";
    private static Connection connection;

    public static Connection createConnection() {
        connection = null;
        System.out.println("-------- PostgreSQL JDBC ConnectionToPostgreSQL Testing ------------");
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ConnectionToPostgreSQL Failed! Check output console");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        return connection;
    }
}
