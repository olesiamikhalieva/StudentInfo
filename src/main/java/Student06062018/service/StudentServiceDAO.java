package Student06062018.service;

import java.sql.SQLException;

public interface StudentServiceDAO {
     void selectByMail(String mail) throws SQLException;
     void selectduplicateSername() throws SQLException;
     void selectAllStudent() throws SQLException;
}
