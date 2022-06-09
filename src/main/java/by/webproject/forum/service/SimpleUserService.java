package by.webproject.forum.service;

import by.webproject.forum.dao.UserDao;
import by.webproject.forum.entity.Role;
import by.webproject.forum.entity.User;
import by.webproject.forum.exception.DaoException;
import by.webproject.forum.exception.ServiceError;
import by.webproject.forum.security.PasswordHasher;
import by.webproject.forum.validator.UserValidator;

import java.util.List;
import java.util.Optional;

public class SimpleUserService implements UserService{
   private final UserValidator userValidator;
   private final UserDao userDao;
   private final PasswordHasher passwordHasher;

    public SimpleUserService(UserValidator userValidator, UserDao userDao, PasswordHasher passwordHasher) {
        this.userValidator = userValidator;
        this.userDao = userDao;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User addUserAsAdmin(String login, String password) {
        if(!userValidator.validateUserDataByLoginAndPassword(login, password)){
            throw new ServiceError("Invalid User data userPassword: " + password + "userLogin: " + login);
        }
        try {
        final String hashedPassword = passwordHasher.hashPassword(password);
        final User user = new User.Builder().
                withUserLogin(login).
                withUserPassword(hashedPassword).
                withUserRole(Role.ADMIN).
                build();

            userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceError("Invalid User data userPassword: " + password + "userLogin: " + login);
        }
        throw new ServiceError("Invalid User data userPassword: " + password + "userLogin: " + login);
    }
    @Override
    public boolean  addUserAsClient(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return false;
        }
        try {
        final String hashedPassword = passwordHasher.hashPassword(password);
        final User user = new User.Builder().
                withUserLogin(login).
                withUserPassword(password).
                withUserRole(Role.CLIENT).
                build();

            userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceError("Cannot add User");
        }
        return true;
    }

    @Override
    public Optional<User> authenticationIfAdmin(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return Optional.empty();
        }
        try {
            final Optional <User>  userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()){
                final User userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (userInstance.getUserRole().equals(Role.ADMIN) && passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, hashedPasswordFromDB)){
                    return userFromDB;
                }
            }
        } catch (DaoException e) {
           throw new ServiceError("Cannot authorize User, userLogin: " + login + "userPassword: " + password);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> authenticationIfClient(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)){
            return Optional.empty();
        }
        try {
            final Optional<User> userFromDB = userDao.findUserByLogin(login);
            if(userFromDB.isPresent()){
                final User userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (userInstance.getUserRole().equals(Role.CLIENT) && passwordHasher.checkIsEqualsPasswordAndPasswordHash(password,hashedPasswordFromDB)){
                    return userFromDB;
                }
            }
        } catch (DaoException e) {
            throw new SecurityException("Cannot authorise User, userlogin: " + login + "userPassword: " + password);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllClient() {

        return null;
    }
}
