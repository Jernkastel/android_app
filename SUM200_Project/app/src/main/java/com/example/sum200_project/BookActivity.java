package com.example.sum200_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {

    ImageButton closeSpellbookButton, meatballButton;

    private SoundLibrary sndLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Recieves and keeps the score from the main menu, this way the score stays even if the user goes to the spellbook view
        final String tempScore;
        sndLib = new SoundLibrary(this);
        tempScore = (getIntent().getStringExtra("EXTRA_SCORE"));

        closeSpellbookButton = (ImageButton)findViewById(R.id.closeSpellbookButton);
        meatballButton = (ImageButton)findViewById(R.id.chaosMeteorIMG);

        //sends the temporary score back to the menu
        closeSpellbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent closeIntent = new Intent(BookActivity.this, MainActivity.class);
                closeIntent.putExtra("EXTRA_SCORE", tempScore);
                closeIntent.putExtra("EXTRA_GAME_SCORE", "Last Game's Score:");
                startActivity(closeIntent);

            }
        });

        //Just a fun little easter egg
        meatballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sndLib.soundPool.play(sndLib.soundMeatball,1,1,0,0,1);
            }
        });
    }
}
