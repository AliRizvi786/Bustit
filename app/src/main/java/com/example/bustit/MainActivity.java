package com.example.bustit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Tweet> tweetArrayList;
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL = "https://bust-it.herokuapp.com/api/";
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                while(tweetArrayList==null) {
                    getListfromAPI(URL);
                }
                Intent intent=new Intent(MainActivity.this,HomeScreenActivity.class);
                startActivity(intent);//set the new Content of your activity

            }
        }.start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 5000);

    }

    private void getListfromAPI (String url) {
        HttpGetRequest getRequest = new HttpGetRequest();
        String jsonStr = "";
        try {
            jsonStr = getRequest.execute(url).get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.d("error","unable to connect");
        }
        try{
            GetTweetList getTweetList = new GetTweetList();
            tweetArrayList = getTweetList.execute(jsonStr).get();
//            Log.d(TAG,""+cakeArrayList.size());
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.d("error","unable to get data");
        }
    }

    public void onClickCalled(Tweet tweet) {
        Intent intent=new Intent(MainActivity.this,TweetListItem.class);
        intent.putExtra("tweetclicked",tweet);
        startActivity(intent);
    }
}