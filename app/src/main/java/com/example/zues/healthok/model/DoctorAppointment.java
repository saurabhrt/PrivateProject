package com.example.zues.healthok.model;

import java.util.Date;

public class DoctorAppointment extends OrderBase {

    private int doctorAppointmentId;
    //	private int orderId;
//	private int doctorId;
    private Date appointmentDate;
    private int prescriptionImageId;
    private String description;
//	private String comments;


    public int getDoctorAppointmentId() {
        return doctorAppointmentId;
    }

    public void setDoctorAppointmentId(int doctorAppointmentId) {
        this.doctorAppointmentId = doctorAppointmentId;
    }

    /*
        public int getOrderId() {
            return orderId;
        }
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        public int getDoctorId() {
            return doctorId;
        }
        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }
    */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(int prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setOrderDescription(description);

    }
/*
    public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
*/
}
