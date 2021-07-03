package com.example.bustit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeScreenActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("tweet");
    TweetListAdapter adapter;
    String BASE_URL;
    public static ArrayList<Tweet> hashTagArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        fab = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.homeRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        BASE_URL = "https://bust-it.herokuapp.com/api/";

        adapter = new TweetListAdapter(MainActivity.tweetArrayList);
        recyclerView.setAdapter(adapter);

//        HttpGetRequest getRequest = new HttpGetRequest();
//        String jsonStr = "";
//        try {
//            jsonStr = getRequest.execute(BASE_URL).get();
//            Log.d("return",jsonStr.toString());
//        }catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//            Log.d("error","unable to connect");
//        }

//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                ArrayList<Tweet> tweets = new ArrayList<>();
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    Tweet tweet = data.getValue(Tweet.class);
//                    tweets.add(tweet);
//
//                }
//                Log.d("message", "tweet id is: "+ tweets.get(0).getUser_name());
//
//                adapter = new TweetListAdapter(tweets);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Log.w("Error", "Failed to read value.", error.toException());
//            }
//        });








        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeScreenActivity.this);
                dialog.setContentView(R.layout.search_dialog);
                dialog.setTitle("This is my custom dialog box");
                dialog.setCancelable(true);
                RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                RadioButton rd_hashtag = (RadioButton) dialog.findViewById(R.id.rd_hashtag);
                RadioButton rd_tweet = (RadioButton) dialog.findViewById(R.id.rd_tweet);
                RadioButton rd_username = (RadioButton) dialog.findViewById(R.id.rd_username);
                Button search = (Button) dialog.findViewById(R.id.dialog_search_button);
                EditText searchText = (EditText) dialog.findViewById(R.id.search_text);
                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.d("yo","inside on click");
                        int selectedId=radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton=(RadioButton)dialog.findViewById(selectedId);
                        String text;
                        if(searchText.getText()==null){
                            Toast toast = Toast.makeText(getApplicationContext(),"Enter text to be Search",Toast.LENGTH_SHORT);
                        }
                        else{
                            text = searchText.getText().toString();
                            if(radioButton==null){
                                Toast toast = Toast.makeText(getApplicationContext(),"Kindly Select one of the options",Toast.LENGTH_SHORT);
                            }
                            else if(radioButton.equals(rd_hashtag)) {
                                hashTagArrayList=null;
                                while(hashTagArrayList==null) {
                                    getListfromAPI(BASE_URL+"search?hashtag="+text);
                                }
                                Intent intent = new Intent(HomeScreenActivity.this, HashTagActivity.class);
                                startActivity(intent);
                            }
                            else if(radioButton.equals(rd_username)){
                                HttpGetRequest getRequest = new HttpGetRequest();
                                String jsonStr = "";
                                try {
                                    jsonStr = getRequest.execute(BASE_URL+"search?username="+text).get();
                                    Log.d("yo",jsonStr);
                                }catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                    Log.d("error","unable to connect");
                                }
                                String result = "";
                                while(jsonStr.equals("")){
                                }
                                try {
                                    JSONObject jsonObj = new JSONObject(jsonStr);
                                    result = jsonObj.getString("credibility");
                                }
                                catch (Exception e){

                                }
                                Intent intent = new Intent(HomeScreenActivity.this, UserActivity.class);
                                intent.putExtra("username",text);
                                intent.putExtra("result",result);
                                startActivity(intent);
                            }
                            else{
                                HttpGetRequest getRequest = new HttpGetRequest();
                                String jsonStr = "";
                                try {
                                    jsonStr = getRequest.execute(BASE_URL+"search?tweet="+text).get();
                                    Log.d("yo",jsonStr);
                                }catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                    Log.d("error","unable to connect");
                                }
                                String result = "";
                                while(jsonStr.equals("")){
                                }
                                try {
                                    JSONObject jsonObj = new JSONObject(jsonStr);
                                    result = jsonObj.getString("result");
                                }
                                catch (Exception e){

                                }
                                Intent intent = new Intent(HomeScreenActivity.this, PostActivity.class);
                                intent.putExtra("tweet",text);
                                intent.putExtra("result",result);
                                startActivity(intent);

                            }
                        }

                    }
                });

                // now that the dialog is set up, it's time to show it
                dialog.show();
            }
        });
    }
    public void onClickCalled(Tweet tweet) {
        Intent intent=new Intent(this,TweetListItem.class);
        Log.d("Hello friends","Intent Passed");
        intent.putExtra("tweetclicked",tweet);
        startActivity(intent);
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
            hashTagArrayList = getTweetList.execute(jsonStr).get();
//            Log.d(TAG,""+cakeArrayList.size());
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.d("error","unable to get data");
        }
    }

}