package com.nibm.taskTinker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_position")
    private String userPosition;

    @Column(name = "user_password")
    private String userPassword;

    private String confirmPassword;

    public User() {
    }

    public User(String userEmail, String userName, String userPosition) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPosition = userPosition;
    }

    public User(String userEmail, String userName, String userPosition, String userPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userPassword = userPassword;
    }

    public User(String userEmail, String userName, String userPosition, String userPassword, String confirmPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPosition = userPosition;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
