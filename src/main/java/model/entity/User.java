package model.entity;

import model.service.UserServiceFactory;

import java.util.Objects;

public class User {
    ;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    private String username;
    private String password;
    private UserServiceFactory.UserRole role;
    private boolean isBanned;

    public User(int id, String username, String password, UserServiceFactory.UserRole role, boolean isBanned) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBanned = isBanned;
    }

    public User(String username, String password, UserServiceFactory.UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserServiceFactory.UserRole getRole() {
        return role;
    }

    public void setRole(UserServiceFactory.UserRole role) {
        this.role = role;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
