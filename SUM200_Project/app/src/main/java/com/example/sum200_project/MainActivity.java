package com.example.sum200_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton goToGame, goToBook;
    TextView scoreText, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToGame = (ImageButton)findViewById(R.id.moveToGame);
        goToBook = (ImageButton)findViewById(R.id.moveToBook);

        scoreText = (TextView)findViewById(R.id.lastScoreText);
        score = (TextView)findViewById(R.id.lastScore);

        scoreText.setText(getIntent().getStringExtra("EXTRA_GAME_SCORE"));
        score.setText(getIntent().getStringExtra("EXTRA_SCORE"));

        /*
        the above code should probably be changed to the following:

        if (getIntent().getStringExtra("EXTRA_SCORE") != "0"){
            scoreText.setText(getIntent().getStringExtra("EXTRA_GAME_SCORE"));
            score.setText(getIntent().getStringExtra("EXTRA_SCORE"));
        }

        that way there's no empty score displayed if the user back out of a game.
        I've no way to test it at the moment, however, so the old code will remain
        */

        //starts the game
        goToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent menuIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(menuIntent);

            }
        });

        //goes to the spellbook activity
        goToBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //it has to send the score as well, otherwise the score wou disappear if you left for another activity and came back
                Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
                bookIntent.putExtra("EXTRA_SCORE", score.getText().toString());
                startActivity(bookIntent);
            }
        });
    }
}
