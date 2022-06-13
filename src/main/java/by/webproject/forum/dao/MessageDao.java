package by.webproject.forum.dao;

import by.webproject.forum.entity.Message;
import by.webproject.forum.exception.DaoException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MessageDao {
    boolean addMessage(long fromId, long toId, String message, Date currentDate) throws DaoException;

    Optional<Message> findFromId(long userId) throws DaoException;

    List<Message> findToId(long userId) throws DaoException;
}//
