package com.nibm.taskTinker.dto;

public class UserDTO {
    private String userEmail;
    private String userName;
    private String userPosition;

    public UserDTO() {
    }

    public UserDTO(String userEmail, String userName, String userPosition) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPosition = userPosition;
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
}
