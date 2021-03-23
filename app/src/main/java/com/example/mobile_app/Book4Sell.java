package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
/*
USED BY GREG DO NOT CHANGE !!!
class extended from Book class to create book for sell object with details
of the book
*/

public class Book4Sell extends Book implements Parcelable{
    // Variables declaration
    private String price, studentID, status, date;

    public Book4Sell() {
    }


    // Constructor to create Object
    public Book4Sell(String title, String author, String edition, String ISBN, String category,
                     String imagelink, String publisher, String publicyear, String price,
                     String studentID, String status, String date) {
        super(title, author, edition, ISBN, category, imagelink, publisher, publicyear);
        this.price = price;
        this.studentID = studentID;
        this.status = status;
        this.date = date;

    }
    // Parcel read
    protected Book4Sell(Parcel in) {
        super(in);
        price = in.readString();
        studentID = in.readString();
        status = in.readString();
        date = in.readString();
    }
    // Parcel write
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(price);
        dest.writeString(studentID);
        dest.writeString(status);
        dest.writeString(date);
    }


    // Parcel Creator
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


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


/*
    public static Parcelable.Creator<Book4Sell> getCREATOR() {
        return CREATOR;
    }
*/
    @Override
    public int describeContents() {
        return 0;
    }

}
