package com.example.tiara.hamilfan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tiara on 2016-12-03.
 */
public class LyricsMenuActivity extends Activity {
    @Bind(R.id.listView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_menu);
        ButterKnife.bind(this);

        listView.setAdapter(new SongLyricAdapter(this, Arrays.asList(InformationManager.SONGS)));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String songTitle = (String)adapterView.getAdapter().getItem(position);
                showLyrics(songTitle);
            }
        });
    }

    public void showLyrics(String songTitle){
        Intent intent = new Intent(this, LyricsActivity.class);
        intent.putExtra("songTitle",songTitle);
        startActivity(intent);
        finish();
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
