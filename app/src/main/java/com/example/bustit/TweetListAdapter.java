package com.example.bustit;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.ViewHolder> {

    private final ArrayList<Tweet> tweet_list;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tweet_text;
        private final TextView credibility_score;

        private final TextView user_name;
        private final TextView twitter_handle;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tweet_text = (TextView) view.findViewById(R.id.tweet);
            credibility_score = (TextView) view.findViewById(R.id.credibility_score);
            user_name = (TextView) view.findViewById(R.id.twitter_username);
            twitter_handle = (TextView) view.findViewById(R.id.twitter_handle);

        }

        public TextView getUser_name() {
            return user_name;
        }

        public TextView getTwitter_handle() {
            return twitter_handle;
        }


        public TextView getTweet_text() {
            return tweet_text;
        }

        public TextView getCredibility_score() {
            return credibility_score;
        }




    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public TweetListAdapter(ArrayList<Tweet> dataSet) {
        tweet_list = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tweet_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.d("","Hey yo");
        Tweet tweet = tweet_list.get(position);

        viewHolder.getTweet_text().setText(tweet.getText());
        String score = "Credibility: "+tweet.getCredibility();
        viewHolder.getCredibility_score().setText(score);
        viewHolder.getUser_name().setText(tweet.getScreen_name());
        viewHolder.getTwitter_handle().setText("@"+tweet.getUser_name());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Saavariya","aa aa aha");
                Toast.makeText(view.getContext(),"Tweet with id "+tweet.getTweet_id()+" has been clicked",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(view.getContext(),TweetListItem.class);
                intent.putExtra("tweetclicked",tweet);
                view.getContext().startActivity(intent);
//                ((HomeScreenActivity) view.getContext()).onClickCalled(tweet);
            }
        });
        Log.d("Saavariya"," no no aa aa aha");

        if(tweet.getCredibility().equals("real")){
            viewHolder.getCredibility_score().setTextColor(Color.GREEN);
        }
        else{
            viewHolder.getCredibility_score().setTextColor(Color.RED);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tweet_list.size();
    }
}
