package com.example.tiara.hamilfan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void whoSaidIt(View view){
        Intent intent = new Intent(this, WhoSaidItActivity.class);
        startActivity(intent);
        finish();
    }

    public void lyrics(View view){
        Intent intent = new Intent(this, LyricsMenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void facts(View view){
        Intent intent = new Intent(this, FactsActivity.class);
        startActivity(intent);
        finish();
    }

    public void twitter(View view){
        Intent intent = new Intent(this, TwitterActivity.class);
        startActivity(intent);
        finish();
    }
}
