package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Activities implements Parcelable {

    private String date,type,url,startweek,description,hour;

    public Activities(String date, String type, String url, String startweek,String description,String hour) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.hour = hour;
        this.url = url;
        this.startweek = startweek;
    }

    public Activities() {

    }


    protected Activities(Parcel in) {
        date = in.readString();
        description = in.readString();
        hour = in.readString();
        type = in.readString();
        url = in.readString();
        startweek = in.readString();
    }

    public static final Creator<Activities> CREATOR = new Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel in) {return new Activities(in);}

        @Override
        public Activities[] newArray(int size) {return new Activities[size];}
    };

    public String getDescription() {
        return description;
    }
    public String getHour() {return hour;}
    public String getStartweek() {return startweek;}
    public String getType() {
        return type;
    }
    public String getUrl() {
        return url;
    }
    public String getDate() {
        return date;
    }


    public static Creator<Activities> getCREATOR() {
        return CREATOR;
    }

    public void setDescription(String description) {this.description = description;}
    public void setHour(String hour) {this.hour = hour;}
    public void setStartweek(String startweek) {
        this.startweek = startweek;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setType(String type) {
        this.type = type;
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
        dest.writeString(date);
        dest.writeString(hour);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(startweek);
    }

}
