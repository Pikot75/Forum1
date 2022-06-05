package by.webproject.forum.dao;

import by.webproject.forum.entity.User;
import by.webproject.forum.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    User addUser(User user) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    // List<String> findAllUserMessage(String string);

}
