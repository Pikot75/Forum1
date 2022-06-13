package by.webproject.forum.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection() throws SQLException;

    void releaseConnection(Connection connection);

    void destroyPool();
//
}