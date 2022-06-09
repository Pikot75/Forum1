package by.webproject.forum.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import com.zaxxer.hikari.pool.ProxyConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//public class HikariCPonnectionPool //implements ConnectionPool{
// private static final HikariConfig CONFIG = new HikariConfig();
//  private static final Properties PROPERTIES = new Properties();
//  private static final String PROPERTIES_DATABASE_FILE_NAME = "database.properties";
//  private static final String PROPERTY_URL = "db.url";
//  private static final String PROPERTY_USER = "db.user";
//  private static final String PROPERTY_PASSWORD = "db.password";
//  private static final String PROPERTY_MAX_POOL_SIZE = "db.maxpoolsize";
//  private static final String PROPERTY_DRIVER = "db.driver";
//  private static final String DATABASE_URL;
//  private static final String DATABASE_PASSWORD;
//  private static final String DATABASE_USER;
//  private static final String DATABASE_DRIVER;
//  private static final String DATABASE_MAX_POOL_SIZE;
//  private static final HikariDataSource ds;
//
//  static {
//      try (InputStream inputStream = HikariDataSource.class.getClassLoader()
//              .getResourceAsStream(PROPERTIES_DATABASE_FILE_NAME)) {
//           PROPERTIES.load(inputStream);
//           DATABASE_URL = PROPERTIES.getProperty(PROPERTY_URL);
//           DATABASE_USER = PROPERTIES.getProperty(PROPERTY_USER);
//           DATABASE_PASSWORD = PROPERTIES.getProperty(PROPERTY_PASSWORD);
//           DATABASE_MAX_POOL_SIZE = PROPERTIES.getProperty(PROPERTY_MAX_POOL_SIZE);
//           DATABASE_DRIVER = PROPERTIES.getProperty(PROPERTY_DRIVER);
//
//      }catch (FileNotFoundException e){
//          throw new RuntimeException("FileNotFoundException",e);
//      }catch (IOException e){
//          throw new RuntimeException("IOException", e);
//      }
//      CONFIG.setJdbcUrl(DATABASE_URL);
//      CONFIG.setUsername(DATABASE_USER);
//      CONFIG.setPassword(DATABASE_PASSWORD);
//      CONFIG.setDriverClassName(DATABASE_DRIVER);
//      CONFIG.setMaximumPoolSize(Integer.parseInt(DATABASE_MAX_POOL_SIZE));
//      ds = new HikariDataSource(CONFIG);
//  }
// @Override
// public Connection getConnection() throws SQLException {
//     return (ProxyConnection)ds.getConnection();
//
// }
//
//