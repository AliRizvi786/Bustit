package com.example.bustit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HashTagActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TweetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tag);
        recyclerView = findViewById(R.id.hashtagRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        ArrayList<Tweet> tweets = new ArrayList<>();
        adapter = new TweetListAdapter(HomeScreenActivity.hashTagArrayList);
        recyclerView.setAdapter(adapter);
    }
}