package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    // class to make Book object
    // variables
    private String title, author, edition, ISBN, category,imglink,publisher,publicyear;




    // Constructor to create object and write to FireBase
    public Book(String title, String author, String edition, String ISBN, String category, String imagelink, String publisher, String publicyear) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.ISBN = ISBN;
        this.category = category;
        this.imglink = imagelink;
        this.publisher = publisher;

        this.publicyear = publicyear;
    }
    // Constructor to create object and read from FireBase
    public Book() {

    }


    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        edition = in.readString();
        ISBN = in.readString();
        category = in.readString();
        imglink = in.readString();
        publisher =in.readString();

        publicyear = in.readString();
    }
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicyear() {
        return publicyear;
    }

    public void setPublicyear(String publicyear) {
        this.publicyear = publicyear;
    }

    public static Parcelable.Creator<Book> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(edition);
        dest.writeString(ISBN);
        dest.writeString(category);
        dest.writeString(imglink);
        dest.writeString(publisher);
        dest.writeString(publicyear);

    }

}
