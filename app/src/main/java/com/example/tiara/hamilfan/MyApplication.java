package com.example.tiara.hamilfan;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Tiara on 2016-11-23.
 */
public class MyApplication extends Application {
    private InformationManager informationManager;
    private static final String TWITTER_KEY = "PJmkgIFP28Epby9jgzlpwFdDf"; //TWITTER_KEY obtained from Fabric
    private static final String TWITTER_SECRET = "cwA7M1xShhpwSB2OOqPFS1jWCKamLRBIE89oYIn6k044zOsA6H"; //TWITTER_SECRET obtained from Fabric
    private List<String> facts = new ArrayList<>();

    @Override
    public void onCreate(){
        super.onCreate();

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        informationManager = new InformationManager();

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("facts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // dataSnapshot.getChildren()
                facts = (List<String>) dataSnapshot.getValue();
                informationManager.setFacts(facts);
                //for ()
                Log.d("Firebase Database", facts.size() + " facts");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Firebase Database", databaseError.getMessage());

            }
        });


        LyricsParser lyricsParser = new LyricsParser(this);
        for (String song : InformationManager.SONGS) {
            lyricsParser.parse(song);
        }

    //    informationManager.setFacts(facts);
    }

    public InformationManager getInformationManager(){
        return informationManager;
    }
}
