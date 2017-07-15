package com.example.zues.healthok.model;

public class GuestOrder extends OrderBase {


    private int userId;


    private String firstName;
    private String lastName;

    private String emailId;
    private String mobile;
    private String password;
    private String registrationNumber;

    private User user = null;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public User getUser() {
        if (user == null) {

            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(mobile);
            user.setRegistrationNumber(registrationNumber);
            user.setPassword(password);
            user.setUserId(userId);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        firstName = user.getFirstName();
        lastName = user.getLastName();
        mobile = user.getPhone();
        registrationNumber = user.getRegistrationNumber();
        password = user.getPassword();
        userId = user.getUserId();
    }

}
