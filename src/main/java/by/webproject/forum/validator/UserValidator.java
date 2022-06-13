package by.webproject.forum.validator;

public interface UserValidator {
    boolean validateUserDataByLoginAndPasswordWithSecretKey(String login, String password, String secretKey);

    boolean validateUserDataByLoginAndPassword(String login, String password);
}//
