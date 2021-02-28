package com.example.mobile_app;

public class Book {
    // class to make Book object
    // variables
    private String title, author, edition, ISBN, Category;
    private Integer bookID;

    //TODO: pleqse look and think if we need something more in this class

    // Constructor to create object and write to FireBase
    public Book(String title, String author, String edition, String ISBN, String category, Integer bookID) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.ISBN = ISBN;
        Category = category;
        this.bookID = bookID;
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
    public String getCategory() {return Category;}
    public Integer getBookID() {return bookID;}

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
        Category = category;
    }
    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

}
wwwwwwwwww