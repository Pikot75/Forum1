package by.webproject.forum.dao;

import by.webproject.forum.connection.ConnectionPool;
import by.webproject.forum.entity.Role;
import by.webproject.forum.entity.User;
import by.webproject.forum.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class SimpleUserDao implements UserDao{
    private static final String SQL_ADD_USER ="INSERT INTO user(user_login,user_password,user_role_id) VALUES (?,?,?)";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_login,user_password,r.role_name FROM user "+
            "WHERE user_login = ?";
    private static final String SQL_FIND_ALL_USER_SENT_MESSAGE="SELECT send_login, message FROM message"+
            "WHERE user_login = ?";
   private final ConnectionPool connectionPool;

    public SimpleUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User addUser(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(user.getUserRole().ordinal()));
            final int countCreateRow = preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (countCreateRow > 0 && generatedKeys.next()) {
                return new User.Builder().
                        withUserId(generatedKeys.getLong("user_id")).
                        withUserLogin(user.getLogin()).
                        withUserPassword(user.getPassword()).
                        build();
            }
        } catch (SQLException sqlException) {
            throw new DaoException("Cannot add userLogin: " + user.getLogin() + "userPassword: " + user.getPassword() + "userRole: " + user.getUserRole());
        }
        throw new DaoException("Cannot add user userLogin: " + user.getLogin() + " userPassword: " + user.getPassword() + " userRole: " + user.getUserRole());
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new User.Builder().
                        withUserId(resultSet.getLong(1)).
                        withUserLogin(resultSet.getString(2)).
                        withUserPassword(resultSet.getString(3)).
                        withUserRole(Role.valueOf(resultSet.getString(4))).
                        build());
            }
        } catch (SQLException sqlException) {
            throw new DaoException("Cannot find user by login, login: " + login, sqlException);
        }
        return Optional.empty();
    }


}
