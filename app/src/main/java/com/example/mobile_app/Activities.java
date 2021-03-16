package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Activities implements Parcelable {

    private String date,type,url;

    public Activities(String date, String type, String url) {
        this.date = date;
        this.type = type;
        this.url = url;
    }

    public Activities() {

    }


    protected Activities(Parcel in) {
        date = in.readString();
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<Activities> CREATOR = new Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel in) {return new Activities(in);}

        @Override
        public Activities[] newArray(int size) {return new Activities[size];}
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
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
        dest.writeString(type);
        dest.writeString(url);
    }
}
