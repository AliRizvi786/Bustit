package com.example.bustit;

import java.io.Serializable;

public class Tweet implements Serializable {
    private String credibility;
    private String tweet_id;
    private String screen_name;
    private String user_name;
    private String text;
    public Tweet(){

    }
    public Tweet(String credibility, String tweet_id, String screen_name, String user_name, String text) {
        this.credibility = credibility;
        this.tweet_id = tweet_id;
        this.screen_name = screen_name;
        this.user_name = user_name;
        this.text = text;
    }

    public String getCredibility() {
        return credibility;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getText() {
        return text;
    }

    public String getTweetURL(){
        return "https://twitter.com/"+user_name+"/status/"+tweet_id;
    }

}
