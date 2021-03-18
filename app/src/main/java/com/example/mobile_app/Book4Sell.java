package com.example.mobile_app;

import java.util.Date;
// USED BY GREG DO NOT CHANGE !!!
public class Book4Sell extends Book {

    String price;
    String studentID, status, date;



    public Book4Sell() {
    }
// class to make Book object
    // variables
    // temporry values for studentid and date
    public Book4Sell(String title, String author, String edition, String ISBN, String category,
                     String imagelink, String publisher, String publicyear, String price,
                     String studentID, String status, String date) {
        super(title, author, edition, ISBN, category, imagelink, publisher, publicyear);
        this.price = price;
        this.studentID = studentID;
        this.status = status;
        this.date = date;


    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
// Methods

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    //TODO: pleqse look and think if we need something more in this class


}
