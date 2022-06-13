package by.webproject.forum.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum PastaConnectionPool implements ConnectionPool {
    INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(PastaConnectionPool.class);
    private BlockingQueue<ProxyConnection> freeConnection;
    private Queue<ProxyConnection> giveAwayConnection;

    private final static int DEFAULT_POOL_SIZE = 6;

    PastaConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Cannot register drivers for jdbc", e);
            throw new RuntimeException("Cannot register drivers for jdbc");
        }
        freeConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        giveAwayConnection = new ArrayDeque<>();
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testphones", "root", "pass");
                try {
                    freeConnection.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnection.take();
            giveAwayConnection.offer((ProxyConnection) connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        giveAwayConnection.remove(connection);
        freeConnection.offer((ProxyConnection) connection);
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ProxyConnection proxyConnection = freeConnection.take();
                try {
                    proxyConnection.reallyCLose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        final Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(driverEnumeration.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
//
        }
    }
}
