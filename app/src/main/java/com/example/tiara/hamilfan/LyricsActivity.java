package com.example.tiara.hamilfan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tiara on 2016-12-03.
 */
public class LyricsActivity extends Activity{
    @Bind(R.id.title_textview) TextView titleTextView;
    @Bind(R.id.lyrics_textview) TextView lyricsTextView;

    @Override
    protected void onCreate(Bundle onSavedInstance){
        super.onCreate(onSavedInstance);
        setContentView(R.layout.activity_lyrics);
        ButterKnife.bind(this);

        String songTitle = getIntent().getStringExtra("songTitle");
        titleTextView.setText(songTitle);

        MyApplication myApplication = (MyApplication)getApplication();
        int lyricsResource = SongUtils.getLyricsResource(songTitle, myApplication);

        InputStream inputStream = myApplication.getResources().openRawResource(lyricsResource);
        String lyricsString = "";
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            lyricsString = new String(bytes);
        } catch (Exception e){
            Log.e("LyricsActivity", "Exception while reading lyrics file", e);
        }

        lyricsTextView.setText("\n" + lyricsString + "\n");
        lyricsTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void back(View view){
        Intent intent = new Intent(this, LyricsMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
