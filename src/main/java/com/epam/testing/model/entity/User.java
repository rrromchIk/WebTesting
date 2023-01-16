package com.epam.testing.model.entity;

/** User entity class
 *
 * @author rom4ik
 */

public class User extends Entity {
    private static final long serialVersionUID = 1L;
    private final String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private final UserRole userRole;
    private UserStatus userStatus;


    private User(UserBuilder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.userRole = builder.userRole;
        this.userStatus = builder.userStatus;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return userRole;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(UserStatus status) {
        this.userStatus = status;
    }

    @Override
    public String toString() {
        return "User {" +
                "login = " + login +
                ", password = " + password +
                ", name=" + name +
                ", surname = " + surname +
                ", email = " + email + "}";
    }

    /**
     * Builder.
     */
    public static class UserBuilder {
        private String login;
        private String password;
        private String name;
        private String surname;
        private String email;
        private UserRole userRole;
        private UserStatus userStatus;

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder role(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserBuilder status(UserStatus status) {
            this.userStatus = status;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
