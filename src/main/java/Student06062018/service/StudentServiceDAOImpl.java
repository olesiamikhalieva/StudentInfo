package Student06062018.service;

import Student06062018.utils.ConnectionToPostgreSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentServiceDAOImpl implements StudentServiceDAO{

    Connection connection;

    public StudentServiceDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void selectByMail(String mail) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM student WHERE email LIKE ?");
        ps2.setString(1, mail);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            System.out.println("name " + rs2.getString(1) + " , ser name: " + rs2.getString(2)+ ", email: "+rs2.getString(4));
        }
    }

    @Override
    public void selectduplicateSername() throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM student WHERE ser_name IN ( SELECT ser_name  " +
                "from student group BY ser_name HAVING count(ser_name)>1);");
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            System.out.println("name " + rs2.getString(1) + " , ser name: " + rs2.getString(2));
        }
    }

    @Override
    public void selectAllStudent() throws SQLException {
        String sqlQwery = "select * from student";
        PreparedStatement ps = connection.prepareStatement(sqlQwery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("name " + rs.getString(1) + " , ser name: " + rs.getString(2));
        }
    }

}
