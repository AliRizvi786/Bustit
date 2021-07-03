package com.example.bustit;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bustit.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GetTweetList extends AsyncTask<String, Void, ArrayList<Tweet>> {
    private ArrayList<Tweet> tweetList;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("HomeFragment","Json Data is downloading");

    }

    @Override
    protected ArrayList<Tweet> doInBackground(String... params) {
        String jsonStr = params[0];
        tweetList = new ArrayList<Tweet>();
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray data = jsonObj.getJSONArray("data");

                // looping through All cakes
                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);
                    String user_name = c.getString("handle_name");
                    String credibility = c.getString("result");
                    String screen_name = c.getString("screen_name");
                    String text = c.getString("text");
                    String tweet_id = c.getString("id");

                    Tweet tweet = new Tweet(credibility,tweet_id,screen_name,user_name,text);
                    Log.d("tweet",user_name+" "+credibility+" ");
                    // adding cake to mArrayList
                    tweetList.add(tweet);
                }

                return tweetList;
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Tweet> arrayList) {
        super.onPostExecute(arrayList);
    }
}
