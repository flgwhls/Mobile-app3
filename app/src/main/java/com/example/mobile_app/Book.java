package com.example.mobile_app;

public class Book {
    // class to make Book object
    // variables
    private String title, author, edition, ISBN, category,imglink,publisher,publicyear;


    //TODO: pleqse look and think if we need something more in this class

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
    //Methods
    // get
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getEdition() {return edition;}
    public String getISBN() {return ISBN;}
    public String getCategory() {return category;}

    public String getPublisher() {return publisher;}

    public String getPublicyear() {return publicyear;}

    public String getImglink() {
        return imglink;
    }

    // set
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicyear(String publicyear) {
        this.publicyear = publicyear;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
