package by.webproject.forum.security;

public interface PasswordHasher {
    boolean checkIsEqualsPasswordAndPasswordHash(String password, String passwordHash);

    String hashPassword(String password);
}//
