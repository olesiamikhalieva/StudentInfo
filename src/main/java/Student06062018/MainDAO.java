package Student06062018;


import Student06062018.service.StudentServiceDAO;
import Student06062018.service.StudentServiceDAOImpl;
import Student06062018.utils.ConnectionToPostgreSQL;
import java.sql.SQLException;

public class MainDAO {

    public static void main(String[] args) throws SQLException {

        StudentServiceDAO studentServiceDAO = new StudentServiceDAOImpl(ConnectionToPostgreSQL.createConnection());
        studentServiceDAO.selectAllStudent();
        System.out.println();
        studentServiceDAO.selectByMail("%gmail.com");
        System.out.println();
        studentServiceDAO.selectduplicateSername();

    }
}
