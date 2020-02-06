package com.example.sum200_project;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

class CountDown {

    boolean timeHasStarted = false;
    //Sets the time limit at 40 sec
    long startTime = 40 * 1000;
    long interval = 1 * 1000;

    TextView countdownText;

    public CountDown(final GameActivity context){

        countdownText = (TextView)context.findViewById(R.id.timer);
        countdownText.setText(countdownText.getText() + String.valueOf(startTime / 4000));

        CountDownTimer countDownTimer = new CountDownTimer(startTime, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownText.setText("" + millisUntilFinished / 1000);
            }

            //Sends the score to the main menu when the countdown reaches 0
            @Override
            public void onFinish() {
                Intent sendScore = new Intent(context, MainActivity.class);
                sendScore.putExtra("EXTRA_SCORE", context.currentScore.getText().toString());
                sendScore.putExtra("EXTRA_GAME_SCORE", "Last Game's Score:");
                context.startActivity(sendScore);
                context.finish();
            }
        };

        countDownTimer.start();
        timeHasStarted = true;
    }
}
