package com.example.tiara.hamilfan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tiara on 2016-12-03.
 */
public class SongLyricAdapter extends ArrayAdapter<String> {

    public SongLyricAdapter(Context context, List<String> songTitles){
        super(context, R.layout.song_title_tile, songTitles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater li = LayoutInflater.from(this.getContext());
            view = li.inflate(R.layout.song_title_tile, parent, false);
        }

        TextView songTitle = (TextView) view.findViewById(R.id.song_title_textview);
        String song = getItem(position);
        songTitle.setText(song);


        return view;
    }


}
