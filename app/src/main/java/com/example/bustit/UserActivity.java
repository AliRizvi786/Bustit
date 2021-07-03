package com.example.bustit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView username = (TextView) findViewById(R.id.twitter_handle);
        TextView credibility = (TextView) findViewById(R.id.credibility_score3);
        TextView link = (TextView) findViewById(R.id.link2);
        Intent intent = getIntent();
        String user = intent.getExtras().getString("username");
        String result = intent.getExtras().getString("result");
        username.setText("@"+user);
        credibility.setText("User Credibility Score: "+result);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+user));
                startActivity(browserIntent);
            }
        });

    }
}