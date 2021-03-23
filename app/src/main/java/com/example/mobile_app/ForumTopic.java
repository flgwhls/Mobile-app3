package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

public class ForumTopic implements Parcelable {
    //initialise class properties
    private String topicName, topicMessage, topicDesc;

    //constructor
    public ForumTopic(String topicName, String topicMessage, String topicDesc) {
        this.topicName = topicName;
        this.topicMessage = topicMessage;
        this.topicDesc = topicDesc;
    }

    //empty constructor
    public ForumTopic() {

    }

    protected ForumTopic(Parcel in) {
        topicMessage = in.readString();
        topicName = in.readString();
        topicDesc = in.readString();
    }

    //getter methods
    public String getTopicName() {
        return topicName;
    }

    public String getTopicMessage() {
        return topicMessage;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    //setter methods
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicMessage(String topicMessage) {
        this.topicMessage = topicMessage;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topicMessage);
        dest.writeString(topicName);
        dest.writeString(topicDesc);
    }

    public static final Creator<ForumTopic> CREATOR = new Creator<ForumTopic>() {
        @Override
        public ForumTopic createFromParcel(Parcel in) {
            return new ForumTopic(in);
        }

        @Override
        public ForumTopic[] newArray(int size) {
            return new ForumTopic[size];
        }
    };

}
