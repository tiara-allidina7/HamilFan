package com.example.tiara.hamilfan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara on 2016-11-23.
 */
public class InformationManager {
    public static final String[] CHARACTERS = {"Burr", "Hamilton", "Eliza", "Angelica", "Peggy", "Lafayette", "Laurens", "Mulligan", "Jefferson",
"Washington", "Madison", "Philip Hamilton", "Maria Reynolds", "King George"}; //"Samuel Seabury", "Charles Lee", "James Reynolds","George Eacker" //TODO: priority value (lesser characters listed less often)
    public static final String[] SONGS = {"Alexander Hamilton", "Aaron Burr Sir", "My Shot", "The Story of Tonight", "The Schuyler Sisters", "Farmer Refuted", "You'll Be Back",
            "Right Hand Man", "A Winter's Ball", "Helpless", "Satisfied", "The Story of Tonight Reprise", "Wait For It", "Stay Alive", "Ten Duel Commandments", "Meet Me Inside",
    "That Would Be Enough", "Guns and Ships", "History Has Its Eyes On You", "Yorktown (The World Turned Upside Down)", "What Comes Next?", "Dear Theodosia", "Non-Stop",
    "What'd I Miss", "Cabinet Battle #1", "Take a Break", "Say No to This", "The Room Where It Happens", "Schuyler Defeated", "Cabinet Battle #2", "Washington on Your Side",
    "One Last Time", "I Know Him", "The Adams Administration", "We Know", "Hurricane", "The Reynolds Pamphlet", "Burn", "Blow Us All Away", "Stay Alive (Reprise)",
    "It's Quiet Uptown", "The Election of 1800", "Your Obedient Servant", "Best of Wives and Best of Women", "The World Was Wide Enough", "Who Lives, Who Dies, Who Tells Your Story"};

    private List<Lyric> lyrics = new ArrayList<>();
    private List<String> facts = new ArrayList<>();

    public void addLyric(Lyric lyric){
        lyrics.add(lyric);
    }

    public void removeLyric(Lyric lyric) {
        lyrics.remove(lyric);
    }

    public List<Lyric> getLyrics(){
        return lyrics;
    }

    public boolean isCharacter(String name){
        for (String character : CHARACTERS){
            if (character.toUpperCase().equals(name.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }
}
