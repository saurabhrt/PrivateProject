package com.example.zues.healthok.model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Abhay-Jaiswal on 3/22/2016.
 */
public class User {

    private String FirstName;
    private String LastName;

    private int UserId;
    private String emailId;
    private String phone;
    private String password;
    private String registrationNumber;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String emailId, String phone, String password, String registrationNumber) {
        setUserId(userId);
        setEmailId(emailId);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setPhone(phone);
        setRegistrationNumber(registrationNumber);
    }

    public User(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            setUserId(jsonObject.getInt("userId"));
            setEmailId(jsonObject.getString("emailId"));
            setFirstName(jsonObject.getString("firstName"));
            setLastName(jsonObject.getString("lastName"));
            setPassword(jsonObject.getString("password"));
            setPhone(jsonObject.getString("mobile"));
            setRegistrationNumber("registrationNumber");


/*
    //Typing mustakes in field names
    setUserId(jsonObject.getInt("UserId"));
    setEmailId(jsonObject.getString("EmailId"));
    setFirstName(jsonObject.getString("FirstName"));
    setLastName(jsonObject.getString("LastName"));
    setPassword(jsonObject.getString("Password"));
    setPhone(jsonObject.getString("Mobile"));
*/
        } catch (Exception e) {
            Log.e("User", e.getMessage());
        }
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;

    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
