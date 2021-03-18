package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
// USED BY GREG DO NOT CHANGE !!!
public class Book4Sell extends Book implements Parcelable{

    private String price, studentID, status, date;

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

    protected Book4Sell(Parcel in) {
        super(in);
        price = in.readString();
        studentID = in.readString();
        status = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(price);
        dest.writeString(studentID);
        dest.writeString(status);
        dest.writeString(date);
    }



    public static final Creator<Book4Sell> CREATOR = new Creator<Book4Sell>() {
        @Override
        public Book4Sell createFromParcel(Parcel in) {
            return new Book4Sell(in);
        }

        @Override
        public Book4Sell[] newArray(int size) {
            return new Book4Sell[size];
        }
    };

    //Getter Setter
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
    public static Parcelable.Creator<Book4Sell> getCREATORsell() {
        return CREATOR;
    }
    @Override
    public int describeContents() {
        return 0;
    }

}
