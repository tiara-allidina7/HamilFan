package com.example.tiara.hamilfan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tiara on 2017-03-13.
 */

public class FactsActivity extends Activity{

    @Bind(R.id.factsListView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        ButterKnife.bind(this);
        String[] facts = ((MyApplication)getApplication()).getInformationManager().getFacts().toArray(new String[0]);
       // String[] facts = {};

        listView.setAdapter(new ArrayAdapter<>(this,  R.layout.fact_list_item, facts));
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
