package com.example.yolobob.testsqlite;
/**
 * @Description : Practicing with SQLite and lab 06
 * @author : Frédérick Gaudet - 8035208
 */
public class User {
    private String username, password, id;

    public User() {
        username = "";
        password = "";
        id = "-1";//Mean no ID
    }

    public User(String username, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = "-1";
    }

    public User(User user){
        this.username = new String(user.getUsername());
        this.password = new String(user.getPassword());
        this.id = new String(user.getId());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
