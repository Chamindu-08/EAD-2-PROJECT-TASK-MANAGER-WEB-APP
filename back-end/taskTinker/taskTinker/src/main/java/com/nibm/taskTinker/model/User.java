package com.nibm.taskTinker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Task")
public class User {
    @Id
    @Column(name = "UserEmail")
    private String userEmail;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "UserPosition")
    private String userPosition;
    @Column(name = "UserPassword")
    private String userPassword;

    public User() {
    }

    public User(String userEmail, String userName, String userPosition, String userPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
