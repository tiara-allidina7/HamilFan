package com.example.tiara.hamilfan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tiara on 2016-11-23.
 */
public class WhoSaidItActivity extends Activity {
    private List<Lyric> lyrics;
    private int index;
    private int currentAnswer;
    //private Lyric currentLyric;

    @Bind(R.id.quote_text) TextView quoteText;
    @Bind(R.id.answer_1) Button answer1;
    @Bind(R.id.answer_2) Button answer2;
    @Bind(R.id.answer_3) Button answer3;
    @Bind(R.id.answer_4) Button answer4;
    @Bind(R.id.count_correct) TextView countCorrect;
    @Bind(R.id.count_incorrect) TextView countIncorrect;
    @Bind(R.id.symbol) ImageView markSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_said_it);
        ButterKnife.bind(this);
        List<Lyric> lyrics = ((MyApplication)getApplication()).getInformationManager().getLyrics();
        Collections.shuffle(lyrics);
        this.lyrics = lyrics;
        index = 0;

        setUpQuestion();
    }

    public void setUpQuestion(){
        if (lyrics.size() == 0){
            return;
        }
        Lyric lyric = lyrics.get(index);
        quoteText.setText(lyric.getText());
        setAnswerOptions(lyric.getSpeaker());
    }


    public void setAnswerOptions(String speaker) {
        List<String> options = new ArrayList<>();
        options.add(speaker);
        List<String> characters = new ArrayList<>(Arrays.asList(InformationManager.CHARACTERS));

        while (options.size() < 4) {
            int random = (int) (Math.random() * characters.size());
            String character = characters.get(random).toUpperCase();
            if (!options.contains(character)) {
                options.add(character);
            }
        }

        Collections.shuffle(options);

        for (int i = 0; i < options.size(); i++) {
            if (speaker.equals(options.get(i))) {
                currentAnswer = i;
            }

            //int goldColour = ContextCompat.getColor(getApplicationContext(), R.color.gold);
            answer1.setText(options.get(0));
            //answer1.setBackgroundColor(goldColour);
            answer2.setText(options.get(1));
            //answer2.setBackgroundColor(goldColour);
            answer3.setText(options.get(2));
            //answer3.setBackgroundColor(goldColour);
            answer4.setText(options.get(3));
            //answer4.setBackgroundColor(goldColour);

        }
    }

    public void submitAnswer(View view){
        int userAnswer = -1;
        switch (view.getId()){
            case R.id.answer_1: userAnswer = 0;
                break;
            case R.id.answer_2: userAnswer = 1;
                break;
            case R.id.answer_3: userAnswer = 2;
                break;
            case R.id.answer_4: userAnswer = 3;
                break;
        }
        if (userAnswer == currentAnswer){
            proceedToNextQuestion(true);
        } else {
            proceedToNextQuestion(false);
        }
    }

    public void proceedToNextQuestion(boolean correct){
        index++;
        if (correct) {
            Integer correctCount = Integer.valueOf(countCorrect.getText().toString());
            countCorrect.setText(String.valueOf(correctCount + 1));
            markSymbol.setImageDrawable(getDrawable(R.drawable.check_mark));
        } else {
            Integer incorrectCount = Integer.valueOf(countIncorrect.getText().toString());
            countIncorrect.setText(String.valueOf(incorrectCount + 1));
            markSymbol.setImageDrawable(getDrawable(R.drawable.x_mark));
        }
        setUpQuestion();
    }

    public void wrongAnswer(int userAnswer){ //incorrect button turns red
        Button buttonClicked = null;
        switch (userAnswer){
            case 0: buttonClicked = answer1;
                break;
            case 1: buttonClicked = answer2;
                break;
            case 2: buttonClicked = answer3;
                break;
            case 3: buttonClicked = answer4;
                break;
        }
        if (buttonClicked != null) {
            buttonClicked.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.wrong_red));
        }
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
