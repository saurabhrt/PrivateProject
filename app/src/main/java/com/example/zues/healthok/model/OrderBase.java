package com.example.zues.healthok.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Created by Abhay-Jaiswal on 6/16/2016.
 */
public class OrderBase {

    private int orderId;
    private int userId;
    private OrderType orderType;
    private Date orderDate;
    private OrderStatusType orderStatusType;
    private Date orderCompletionDate;
    private Date orderFulfillDate;
    private String orderDescription; // this is entered when placing the order.
    private int imageId; // this is id of image stored in database.
    private int doctorId; // used when ordering Doctor Appointment
    private Doctor doctor;
    private String comments; // This is entered by admin

    public static OrderBase fromJSON(String jsonString) {
        OrderBase order = null;
        Gson gson = new GsonBuilder().setDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ").create();
        try {
            order = gson.fromJson(jsonString, OrderBase.class);
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;

    }

    public OrderBase(int userId, OrderType orderType, Date orderDate, OrderStatusType orderStatusType,
                     Date orderCompletionDate, Date orderFulfillDate, String orderDescription, String comments) {
        super();
        this.userId = userId;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.orderStatusType = orderStatusType;
        this.orderCompletionDate = orderCompletionDate;
        this.orderFulfillDate = orderFulfillDate;
        this.orderDescription = orderDescription;
        this.comments = comments;
    }


    public OrderBase() {
        // TODO Auto-generated constructor stub
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getOrderId() {
        return orderId;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public OrderType getOrderType() {
        return orderType;
    }


    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }


    public Date getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }


    public void setOrderStatusType(OrderStatusType orderStatusType) {
        this.orderStatusType = orderStatusType;
    }


    public Date getOrderCompletionDate() {
        return orderCompletionDate;
    }


    public void setOrderCompletionDate(Date orderCompletionDate) {
        this.orderCompletionDate = orderCompletionDate;
    }


    public Date getOrderFulfillDate() {
        return orderFulfillDate;
    }


    public void setOrderFulfillDate(Date orderFulfillDate) {
        this.orderFulfillDate = orderFulfillDate;
    }


    public String getOrderDescription() {
        return orderDescription;
    }


    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }


    public int getImageId() {
        return imageId;
    }


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public int getDoctorId() {
        return doctorId;
    }


    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
