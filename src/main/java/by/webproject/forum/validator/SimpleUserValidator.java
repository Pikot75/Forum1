package by.webproject.forum.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUserValidator implements UserValidator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserValidator.class);
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]{6,100}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,100}$";

    @Override
    public boolean validateUserDataByLoginAndPasswordWithSecretKey(String login, String password, String secretKey) {
        if (login != null && password != null) {
            Pattern pattern = Pattern.compile(LOGIN_REGEX);
            Matcher userLoginMatcher = pattern.matcher(login);
            final boolean userLoginIsValid = userLoginMatcher.find();
            pattern = Pattern.compile(PASSWORD_REGEX);
            Matcher userPasswordMatcher = pattern.matcher(password);
            final boolean userPasswordIsValid = userPasswordMatcher.find();
            return userLoginIsValid && userPasswordIsValid;
        }
        LOG.error("Error login and password");
        return false;
    }

    @Override
    public boolean validateUserDataByLoginAndPassword(String login, String password) {
        return validateUserDataByLoginAndPassword(login, password);
    }
}//
