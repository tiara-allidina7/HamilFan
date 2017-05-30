package com.example.tiara.hamilfan;

import android.util.Log;

import java.io.InputStream;

/**
 * Created by Tiara on 2016-11-23.
 */
public class LyricsParser {
    private static final String TAG = "LyricsParser";
    private MyApplication myApplication;

    public LyricsParser(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    public void parse(String songTitle){
        try {
            int songResourceId = SongUtils.getLyricsResource(songTitle, myApplication);
            InputStream inputStream = myApplication.getResources().openRawResource(songResourceId);

            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String lyrics = new String(bytes);
            //Log.d(TAG, "Lyrics length: " + lyrics.length());
            String[] lyricsArray = lyrics.split("\\[");
            for (String speakerLyrics : lyricsArray){
                if (speakerLyrics.length() > 0) {
                    parseSpeakerBlock(speakerLyrics, songTitle);
                }
            }

        } catch (Exception e){
            Log.d(TAG, "Exception encountered when reading file: " + songTitle, e);
        }
    }

    public void parseSpeakerBlock(String speakerLyrics, String songTitle){
        String speakerName = speakerLyrics.substring(0, speakerLyrics.indexOf("]"));
        speakerLyrics = speakerLyrics.substring(speakerLyrics.indexOf("]") + 1);
        //Log.d(TAG, "Speaker: " + speakerName);
        InformationManager informationManager = myApplication.getInformationManager();

        String[] lyrics = speakerLyrics.split("\r\n");
        for (String lyricString : lyrics){
            if (lyricString.length() > 5 && lyricString.split(" ").length > 2 && informationManager.isCharacter(speakerName)) { //TODO: check for duplicate lyrics by different people - remove original and duplicate
                Lyric duplicateLyric = getDuplicateLyric(lyricString, speakerName);
                if (duplicateLyric == null) {
                    Lyric lyric = new Lyric();
                    lyric.setSong(songTitle);
                    lyric.setSpeaker(speakerName);
                    lyric.setText(lyricString);
                    informationManager.addLyric(lyric);
                } else {
                    informationManager.removeLyric(duplicateLyric);
                }
            }
        }
    }

    public Lyric getDuplicateLyric(String lyricString, String speakerName){

        InformationManager informationManager = myApplication.getInformationManager();
        for (Lyric lyric : informationManager.getLyrics()){
            if (lyric.getText().equals(lyricString) && !(lyric.getSpeaker().equals(speakerName))){
                return lyric;
            }
            if (lyric.getText().contains(lyricString)){
                return new Lyric(); //return non-null Lyric so that lyricString is not added
            }
            if (lyricString.contains(lyric.getText())){
                return lyric;
            }
        }
        return null;
    }
}
