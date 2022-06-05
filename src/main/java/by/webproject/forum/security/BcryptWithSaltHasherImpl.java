package by.webproject.forum.security;

import org.mindrot.jbcrypt.BCrypt;


public class BcryptWithSaltHasherImpl implements PasswordHasher {
    private static final String SALT = BCrypt.gensalt();

    @Override
    public boolean checkIsEqualsPasswordAndPasswordHash(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, SALT);
    }

}
