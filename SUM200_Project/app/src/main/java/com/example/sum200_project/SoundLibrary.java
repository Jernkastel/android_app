package com.example.sum200_project;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.Random;

class SoundLibrary {

    public SoundPool soundPool;
    int soundInvoke, soundHurtOne, soundHurtTwo, soundHurtThree,  soundSuccessOne, soundDominating, soundGodlike, soundHolyShit, soundRampage, soundUnstoppable,
            soundAlacrity, soundBlast, soundColdsnap, soundEmp, soundGhost, soundMeteor, soundSpirit, soundStrike, soundTornado, soundWall,
            soundSuccessTwo, soundSuccessThree, soundFailureOne, soundFailureTwo, soundFailureThree, soundMeatball;

    public SoundLibrary(Context context) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }

        soundDominating = soundPool.load(context,R.raw.dominating,1);
        soundGodlike = soundPool.load(context,R.raw.godlike,1);
        soundHolyShit = soundPool.load(context,R.raw.holyshit,1);
        soundRampage = soundPool.load(context,R.raw.rampage,1);
        soundUnstoppable = soundPool.load(context,R.raw.unstoppable,1);

        soundInvoke = soundPool.load(context,R.raw.invoke,1);
        soundAlacrity = soundPool.load(context,R.raw.alacrity,1);
        soundBlast = soundPool.load(context,R.raw.blast,1);
        soundColdsnap = soundPool.load(context,R.raw.coldsnap,1);
        soundEmp = soundPool.load(context,R.raw.emp,1);
        soundGhost = soundPool.load(context,R.raw.ghost,1);
        soundMeteor = soundPool.load(context,R.raw.meteor,1);
        soundSpirit = soundPool.load(context,R.raw.spirit,1);
        soundStrike = soundPool.load(context,R.raw.strike,1);
        soundTornado = soundPool.load(context,R.raw.tornado,1);
        soundWall = soundPool.load(context,R.raw.wall,1);

        soundSuccessOne = soundPool.load(context,R.raw.success1,1);
        soundSuccessTwo = soundPool.load(context,R.raw.success2,1);
        soundSuccessThree = soundPool.load(context,R.raw.success3,1);
        soundFailureOne = soundPool.load(context,R.raw.failure1,1);
        soundFailureTwo = soundPool.load(context,R.raw.failure2,1);
        soundFailureThree = soundPool.load(context,R.raw.failure3,1);
        soundHurtOne = soundPool.load(context,R.raw.hurt1,1);
        soundHurtTwo = soundPool.load(context,R.raw.hurt2,1);
        soundHurtThree = soundPool.load(context,R.raw.hurt3,1);

        soundMeatball = soundPool.load(context,R.raw.meatball,1);

    }

    public void SoundRandomizer(String input){

        //50% to play the sound. I didn't want to make it too repetitive since this is the action you do the most
        if (input == "invoke"){
            Random rnd = new Random();
            Integer state;
            state = rnd.nextInt(2) + 1;
            if((state == 1) || (state == 2)){
                soundPool.play(soundInvoke,1,1,0,0,1);
            }
        }
        if (input == "failedSpell"){
            Random rnd = new Random();
            Integer state;
            state = rnd.nextInt(3) + 1;
            if (state == 1){
                soundPool.play(soundHurtOne,1,1,0,0,1);
            }
            else if (state == 2){
                soundPool.play(soundHurtTwo,1,1,0,0,1);
            }
            else if (state == 3){
                soundPool.play(soundHurtThree,1,1,0,0,1);
            }
        }
        if (input == "comboStart"){
            Random rnd = new Random();
            Integer state;
            state = rnd.nextInt(3) + 1;
            if (state == 1){
                soundPool.play(soundSuccessOne,1,1,0,0,1);
            }
            else if (state == 2){
                soundPool.play(soundSuccessTwo,1,1,0,0,1);
            }
            else if (state == 3){
                soundPool.play(soundSuccessThree,1,1,0,0,1);
            }
        }
        if (input == "comboDrop"){
            Random rnd = new Random();
            Integer state;
            state = rnd.nextInt(3) +  1;
            if (state == 1){
                soundPool.play(soundFailureOne,1,1,0,0,1);
            }
            else if (state == 2){
                soundPool.play(soundFailureTwo,1,1,0,0,1);
            }
            else if (state == 3){
                soundPool.play(soundFailureThree,1,1,0,0,1);
            }
        }
        if (input == "wickedSick"){
            Random rnd = new Random();
            Integer state;
            state = rnd.nextInt(5) + 1;
            if (state == 1){
                soundPool.play(soundSuccessOne,1,1,0,0,1);
            }
            else if (state == 2){
                soundPool.play(soundSuccessTwo,1,1,0,0,1);
            }
            else if (state == 3){
                soundPool.play(soundSuccessThree,1,1,0,0,1);
            }
        }
    }


}
