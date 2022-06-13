package by.webproject.forum.dao;

import by.webproject.forum.connection.ConnectionPool;
import by.webproject.forum.entity.Message;
import by.webproject.forum.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleMessageDao implements MessageDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageDao.class);
    private static final String SQL_ADD_MESSAGE = "insert into message(from_id, to_id, message, send_date) VALUES (?,?,?,?)";
    private static final String SQL_FIND_FROM_ID = "select to_id, message, send_date from message where from_id = ?";
    private static final String SQL_FIND_TO_ID = "select from_id, message, send_date from message where to_id = ?";
    private final ConnectionPool connectionPool;

    public SimpleMessageDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean addMessage(long fromId, long toId, String message, Date currentDate) throws DaoException {
        try (final Connection connection = connectionPool.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_MESSAGE)) {
            preparedStatement.setInt(1, (int) fromId);
            preparedStatement.setInt(2, (int) toId);
            preparedStatement.setString(3, message);
            preparedStatement.setDate(4, currentDate);
            final int countRowsCreated = preparedStatement.executeUpdate();
            return countRowsCreated > 0;
        } catch (SQLException e) {
            LOG.error("Cannot add message, fromID: " + fromId + " toID: " + toId + "Message : " + message, e);
            throw new DaoException("Cannot add message, fromID: " + fromId + " toID: " + toId + "Message : " + message, e);
        }

    }

    @Override
    public Optional<Message> findFromId(long userId) throws DaoException {
        try (final Connection connection = connectionPool.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_FROM_ID)) {
            preparedStatement.setInt(1, (int) userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Message.Builder().
                        withUserMessageFrom(resultSet.getInt("from_id")).
                        withText(resultSet.getString("message")).
                        withSendDate(resultSet.getDate("send_date")).
                        build());
            }
        } catch (SQLException e) {
            LOG.error("Cannot find message, user_id" + userId, e);
            throw new DaoException("Cannot find message, user_id" + userId, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Message> findToId(long userId) throws DaoException {
        List<Message> messageList = new ArrayList<>();
        try (final Connection connection = connectionPool.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TO_ID)) {
            preparedStatement.setInt(1, (int) userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final Message message = new Message.Builder().
                        withUserMessageFrom(resultSet.getInt("from_id")).
                        withText(resultSet.getString("message")).
                        withSendDate(resultSet.getDate("send_date")).
                        build();
                messageList.add(message);
            }
        } catch (SQLException e) {
            LOG.error("Cannot find message, user_id" + userId, e);
            throw new DaoException("Cannot find message, user_id" + userId, e);
        }
        return messageList;
    }

    //  @Override
    //  public Optional<Message> findToId(long toId) {
    //      try(  final Connection connection = connectionPool.getConnection();
    //            final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TO_ID);) {
    //          preparedStatement.setInt(1, (int) userId);
    //          ResultSet resultSet = preparedStatement.executeQuery();
    //          if(resultSet.next()){
    //              return new Message.Builder().
    //                      withUserMessageTo(resultSet.getInt("to_id")).
    //                      withText(resultSet.getString("message")).withSendDate(resultSet.getDate("send_date")).
    //          }
    //      } catch (SQLException e) {
    //          e.printStackTrace();
    //      }
    //      return Optional.empty();
    //  }

}//
