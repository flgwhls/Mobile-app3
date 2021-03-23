package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Activities implements Parcelable {
    // class to make Activities object
    // variables
    private String startweek, date, hour, type, description, url;

    // Constructor to create object and write to FireBase
    public Activities(String startweek, String date, String hour, String type, String description, String url) {
        this.startweek = startweek;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.description = description;
        this.url = url;

    }

    // Constructor to create object and read from FireBase
    public Activities() {

    }


    protected Activities(Parcel in) {
        startweek = in.readString();
        date = in.readString();
        hour = in.readString();
        type = in.readString();
        description = in.readString();
        url = in.readString();


    }

    public static final Creator<Activities> CREATOR = new Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel in) {
            return new Activities(in);
        }

        @Override
        public Activities[] newArray(int size) {
            return new Activities[size];
        }
    };

    //getter
    public String getStartweek() {
        return startweek;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public static Creator<Activities> getCREATOR() {
        return CREATOR;
    }

    //setter
    public void setStartweek(String startweek) {
        this.startweek = startweek;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startweek);
        dest.writeString(date);
        dest.writeString(hour);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(url);

    }

}
