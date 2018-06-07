package Student2205;

import Student2205.service.ScriptReaderService;

import java.sql.*;

public class Main {

    private static ScriptReaderService scriptReaderService;

    public static void main(String[] args) {
    scriptReaderService.insertToTableStudentScript30students();
    scriptReaderService.printStudents();
    scriptReaderService.printStudentWithEmail("gmail.com");
    scriptReaderService.printTheSameSernameStudent();
    scriptReaderService.insertToTableInfoScript();
    }


    public static class MainJDBC {

        private static Connection connection = null;

        public static void main(String[] args) throws SQLException {
            connection = createConnection();
            if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }
            selectAllStudent();
            System.out.println();
            selectByGmail();
            System.out.println();
            selectduplicateSername();
        }


        private static void selectByGmail() throws SQLException {
            PreparedStatement ps2 = connection.prepareStatement("select * from student WHERE email = ?");
            ps2.setString(1, "%gmail.com%");
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                System.out.println("name " + rs2.getString(1) + " , ser name: " + rs2.getString(2)+ ", email: "+rs2.getString(4));
            }
        }

        private static void selectduplicateSername() throws SQLException {
            PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM student WHERE ser_name IN ( SELECT ser_name  " +
                    "from student group BY ser_name HAVING count(ser_name)>1);");
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                System.out.println("name " + rs2.getString(1) + " , ser name: " + rs2.getString(2));
            }
        }

        private static void selectAllStudent() throws SQLException {
            String sqlQwery = "select * from student";
            PreparedStatement ps = connection.prepareStatement(sqlQwery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("name " + rs.getString(1) + " , ser name: " + rs.getString(2));
            }
        }


        private static Connection createConnection() {

            Connection connection = null;
            System.out.println("-------- PostgreSQL JDBC ConnectionToPostgreSQL Testing ------------");
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres", "postgres",
                        "qwerty");
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
}