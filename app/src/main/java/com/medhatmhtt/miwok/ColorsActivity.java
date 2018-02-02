package com.medhatmhtt.miwok;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordslist);
        final ArrayList<Word> words=new ArrayList<Word>();
        words.addAll(Arrays.asList(new Word("weṭeṭṭi","Red",R.drawable.color_red,R.raw.color_red),
                new Word("chokokki","Green",R.drawable.color_green,R.raw.color_green),
                new Word("ṭakaakki","Brown",R.drawable.color_brown,R.raw.color_brown),
                new Word("ṭopoppi","Gray",R.drawable.color_gray,R.raw.color_gray),
                new Word("kululli","Black",R.drawable.color_black,R.raw.color_black),
                new Word("kelelli","White",R.drawable.color_white,R.raw.color_white),
                new Word("ṭopiisә","Dusty Yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow),
                new Word("chiwiiṭә","Mustard Yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow)
        ));
        ListView listView=(ListView) findViewById(R.id.list);
        WordAdapter wordAdapter=new WordAdapter(this,words,R.color.category_colors);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Word word =words.get(i);
                        releaseMediaPlayer();
                        mediaPlayer= MediaPlayer.create(ColorsActivity.this,word.getRecordResource());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMediaPlayer();
                            }
                        });
                    }
                }
        );
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
