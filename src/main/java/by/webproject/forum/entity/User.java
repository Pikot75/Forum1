package by.webproject.forum.entity;

import java.util.Objects;

public class User {
    private final long id;
    private final String password;
    private final String login;

    private final Role userRole;

    private User(Builder builder) {
        id = builder.userId;
        password = builder.userPassword;
        login = builder.userLogin;
        userRole = builder.userRole;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public Role getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(password, user.password) && Objects.equals(login, user.login) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, login, userRole);
    }

    public  static class Builder{
        private  long userId;
        private  String userPassword;
        private  String userLogin;

        private static Role userRole;

        public Builder withUserId(Long userId){
            this.userId = userId;
            return this;
        }
        public Builder withUserPassword(String userPassword){
            this.userPassword = userPassword;
            return this;
        }
        public Builder withUserLogin(String userLogin){
            this.userLogin = userLogin;
            return this;
        }
        public Builder withUserRole(Role userRole) {
            Builder.userRole = userRole;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}