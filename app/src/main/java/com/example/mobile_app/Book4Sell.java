package com.example.mobile_app;

public class Book4Sell extends Book {

    float price;
    String studentID, status, date;



    public Book4Sell() {
    }
// class to make Book object
    // variables

    public Book4Sell(String title, String author, String edition, String ISBN, String category,
                     String imagelink, String publisher, String publicyear, float price,
                     String studentID, String status, String date) {
        super(title, author, edition, ISBN, category, imagelink, publisher, publicyear);
        this.price = price;
        this.studentID = studentID;
        this.status = status;
        this.date = date;

    }

    // Methods

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //TODO: pleqse look and think if we need something more in this class


}
