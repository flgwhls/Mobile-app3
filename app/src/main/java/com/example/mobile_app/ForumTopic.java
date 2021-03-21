package com.example.mobile_app;

import android.os.Parcel;
import android.os.Parcelable;

public class ForumTopic implements Parcelable {
    private String topicName, topicMessage;

    public ForumTopic(String topicName, String topicMessage ){
        this.topicName = topicName;
        this.topicMessage = topicMessage;
    }

    public ForumTopic(){

    }

    protected ForumTopic(Parcel in){
        topicMessage = in.readString();
        topicName = in.readString();
    }

    public String getTopicName(){
        return topicName;
    }
    public String getTopicMessage(){
        return topicMessage;
    }
    public void setTopicName(String topicName){
        this.topicName = topicName;
    }
    public void setTopicMessage(String topicMessage){
        this.topicMessage = topicMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(topicMessage);
        dest.writeString(topicName);
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
