package com.example.mobile_app;

public class Student {
    // class to create Student object
    //Variables
    private String studentID, firstName, lastName, email, password;

    //Constructor to make and write object to FireBase


    public Student(String studentID, String firstName, String lastName, String email, String password) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    // Constructor to make object and read data into object from FireBase

    public Student() {
    }
    //Methods
    //get

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    // set

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //TODO: any proposition to put somthink more?
}
