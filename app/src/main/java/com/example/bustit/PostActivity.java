package com.example.bustit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        TextView tweet = (TextView) findViewById(R.id.tweet2);
        TextView credibility = (TextView) findViewById(R.id.credibility_score2);
        Intent intent = getIntent();
        String text = intent.getExtras().getString("tweet");
        String result = intent.getExtras().getString("result");
        tweet.setText(text);
        credibility.setText("Credibility: "+result);
        if(result.equals("real")){
            credibility.setTextColor(Color.GREEN);
        }
        else{
            credibility.setTextColor(Color.RED);
        }
    }
}