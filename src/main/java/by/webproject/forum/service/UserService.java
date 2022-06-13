package by.webproject.forum.service;

import by.webproject.forum.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUserAsAdmin(String login, String password);

    boolean addUserAsClient(String login, String password);

    Optional<User> authenticationIfAdmin(String login, String password);

    Optional<User> authenticationIfClient(String login, String password);


}
