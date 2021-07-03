package com.example.bustit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TweetListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_list_item_fullscreen);
        Tweet tw=(Tweet)getIntent().getSerializableExtra("tweetclicked");
        Log.d("Chai",tw.getText());
        TextView tweetusername= (TextView)findViewById(R.id.twitter_username);
        TextView tweethandle= (TextView)findViewById(R.id.twitter_handle);
        TextView tweeter= (TextView)findViewById(R.id.tweet);
        TextView credibility_text= (TextView)findViewById(R.id.credibility_score);
        TextView link = (TextView)findViewById(R.id.link);
        tweetusername.setText(tw.getScreen_name());
        tweethandle.setText("@"+tw.getUser_name());
        tweeter.setText(tw.getText());
        credibility_text.setText("Credibility: "+tw.getCredibility()+"");

        if(tw.getCredibility().equals("real")){
            credibility_text.setTextColor(Color.GREEN);
        }
        else{
            credibility_text.setTextColor(Color.RED);
        }
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tw.getTweetURL()));
                startActivity(browserIntent);
            }
        });






    }
}