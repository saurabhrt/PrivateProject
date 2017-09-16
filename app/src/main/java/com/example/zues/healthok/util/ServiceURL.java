package com.example.zues.healthok.util;

/**
 * Created by Abhay-Jaiswal on 3/20/2016.
 */
public class ServiceURL {
    public static String Base = "http://app-myhealthok.rhcloud.com/healthokapp/rest/";
//    public static String Base = "http://10.0.2.2:8010/healthokapp/rest/"; //if using emulator
// public static String Base = "http://192.168.0.8:8010/healthokapp/rest/"; // running locally use this and put ip of local machine
//*    public static String Base = "http://192.168.43.141:8080/healthokapp/rest/"; // running locally use this and put ip of local machine
    //    public static String Login = "EmailRegister/Check";
    public static String Login = "auth/validate";
    public static String GCMRegister = "gcm/register";
    public static String Order = "order";
    public static String SignUp = "users/register";
    public static String UserDetails = "ReturnUserDetails/userDetails/";
    public static String ChangePassword = "auth/changepassword";
    public static String OrderDetails = "ordermanagement/getuserorders/";
    public static String ImageDisplayPath = "files/image/";
    public static String UserAppointmentsPath = "ordermanagement/getuserappointment/";
    public static String PrescriptionUploadPath = "files/uploadprescription";
    public static String SearchDoctor = "doctor/search/";
    public static String DocterImage = "files/image/";
    public static String favDoctors = "doctor/user/";
    public static String reportsPath = "ordermanagement/getlabtests/";

    /* From website
    doctorSearchPath: "doctor/search/",
    userAppointmentsPath: "ordermanagement/getuserappointment/",
    doctorDetailsPath: "Doctor/",
    placeOrderPath:  "order/",
    authenticateUrl: "EmailRegister/Check",
    ordersPath: "ordermanagement/getuserorders/",
    prescriptionUploadPath: "files/uploadprescription",
    quickRegistrationPath: "EmailRegistration/access",
    imageDisplayPath : "files/image/",
    authService:"auth/",
    favDoctors: "doctor/user/",
    guestOrderPath: "order/guestorder",
    statPath: "stats/hit"
*/
}
