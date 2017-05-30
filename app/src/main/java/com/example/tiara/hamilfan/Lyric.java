package com.example.tiara.hamilfan;

/**
 * Created by Tiara on 2016-11-23.
 */
public class Lyric {
    private String text;
    private String speaker;
    private String song;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lyric lyric = (Lyric) o;

        if (text != null ? !text.equals(lyric.text) : lyric.text != null) return false;
        if (speaker != null ? !speaker.equals(lyric.speaker) : lyric.speaker != null) return false;
        return song != null ? song.equals(lyric.song) : lyric.song == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (speaker != null ? speaker.hashCode() : 0);
        result = 31 * result + (song != null ? song.hashCode() : 0);
        return result;
    }
}
