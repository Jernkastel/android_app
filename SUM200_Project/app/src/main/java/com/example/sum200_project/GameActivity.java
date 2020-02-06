package com.example.sum200_project;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private SoundLibrary sndLib;
    private CountDown cntDwn;

    ImageButton quas, wex, exort, invoke, spellOne, spellTwo;
    ImageView one, two, three, randomSpell;
    TextView cheatBoxOne, cheatBoxTwo, cheatBoxThree, cheatBoxFour, cheatBoxFive, randomCheatBox, currentScore, activeMultiplier, activeCombo;

    Integer multiplier = 0;
    Integer scoreMultiplier = 1;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sndLib = new SoundLibrary(this);
        cntDwn = new CountDown(this);

        quas = (ImageButton)findViewById(R.id.quasButton);
        wex = (ImageButton)findViewById(R.id.wexButton);
        exort = (ImageButton)findViewById(R.id.exortButton);

        spellOne = (ImageButton)findViewById(R.id.spellOne);
        spellTwo = (ImageButton)findViewById(R.id.spellTwo);

        invoke = (ImageButton)findViewById(R.id.invokeButton);

        one = (ImageView)findViewById(R.id.emptyOne);
        two = (ImageView)findViewById(R.id.emptyTwo);
        three = (ImageView)findViewById(R.id.emptyThree);

        randomSpell = (ImageView)findViewById(R.id.randomSpell);

        //Invisible textviews paired with each interactable button. I use these to check the active spell
        //Is set and updated with the image
        cheatBoxOne = (TextView)findViewById(R.id.cheatBoxOne);
        cheatBoxTwo = (TextView)findViewById(R.id.cheatBoxTwo);
        cheatBoxThree = (TextView)findViewById(R.id.cheatBoxThree);
        cheatBoxFour = (TextView)findViewById(R.id.cheatBoxFour);
        cheatBoxFive = (TextView)findViewById(R.id.cheatBoxFive);

        randomCheatBox = (TextView)findViewById(R.id.randomCheatBox);

        currentScore = (TextView)findViewById(R.id.currentScore);
        activeMultiplier = (TextView)findViewById(R.id.multiplier);
        activeCombo = (TextView)findViewById(R.id.combo);

        //Randomizes the first "goal"
        SpellRandomizer();

        //element buttons, invokes one of the three elements
        quas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckPreviousInvokeBox();
                //updates the hidden cheatbox to match its element
                cheatBoxOne.setText("q");
                //updates the image
                one.setImageDrawable(getDrawable(R.drawable.quas));
            }
        });
        wex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckPreviousInvokeBox();
                cheatBoxOne.setText("w");
                one.setImageDrawable(getDrawable(R.drawable.wex));
            }
        });
        exort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckPreviousInvokeBox();
                cheatBoxOne.setText("e");
                one.setImageDrawable(getDrawable(R.drawable.exort));
            }
        });

        //combines the three active elements
        invoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                CheckPreviousSpellBox();
                InvokeSpell(cheatBoxOne.getText().toString(), cheatBoxTwo.getText().toString(), cheatBoxThree.getText().toString());

            }
        });

        spellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                CastSpell(cheatBoxFour.getText().toString());
            }
        });

        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                CastSpell(cheatBoxFive.getText().toString());
            }
        });
    }

    //Each time a element is invoked ever box checks the one before it and copies its content. This is so simulate the elements moving from left to right.
    //This method will be refactores at a later time.
    @TargetApi(Build.VERSION_CODES.M)
    private void CheckPreviousInvokeBox(){

        /*
        I will refactor this at a later stage. The overly long if-statement will have to suffice for now.
        Want I'd like to do is something similar to the commented code below. It looks better and is more efficient.

        int id = this.getResources().getIdentifier(cheatBoxTwo.getText().toString(),"drawable", getApplicationContext().getPackageName());
        three.setImageResource(id);
        cheatBoxThree.setText(cheatBoxTwo.getText().toString());

        for now, the old code is the only way I can properly "copy" the image from one imageview to another
        */

        if(cheatBoxTwo.getText().toString().equals("q")){
            three.setBackground(getDrawable(R.drawable.quas));
            cheatBoxThree.setText("q");
        }
        else if(cheatBoxTwo.getText().toString().equals("w")){
            three.setBackground(getDrawable(R.drawable.wex));
            cheatBoxThree.setText("w");
        }
        else if(cheatBoxTwo.getText().toString().equals("e")){
            three.setBackground(getDrawable(R.drawable.exort));
            cheatBoxThree.setText("e");
        }

        if(cheatBoxOne.getText().toString().equals("q")){
            two.setBackground(getDrawable(R.drawable.quas));
            cheatBoxTwo.setText("q");
        }
        else if(cheatBoxOne.getText().toString().equals("w")){
            two.setBackground(getDrawable(R.drawable.wex));
            cheatBoxTwo.setText("w");
        }
        else if(cheatBoxOne.getText().toString().equals("e")){
            two.setBackground(getDrawable(R.drawable.exort));
            cheatBoxTwo.setText("e");
        }
    }

    //Same as the above method but for the spell boxes
    //Will also be refactored
    @TargetApi(Build.VERSION_CODES.M)
    private void CheckPreviousSpellBox(){

        if(cheatBoxFour.getText().toString().equals("qqq")){
            spellTwo.setBackground(getDrawable(R.drawable.coldsnap));
            cheatBoxFive.setText("qqq");
        }
        else if(cheatBoxFour.getText().toString().equals("www")){
            spellTwo.setBackground(getDrawable(R.drawable.emp));
            cheatBoxFive.setText("www");
        }
        else if(cheatBoxFour.getText().toString().equals("eee")){
            spellTwo.setBackground(getDrawable(R.drawable.strike));
            cheatBoxFive.setText("eee");
        }
        else if(cheatBoxFour.getText().toString().equals("qqw")){
            spellTwo.setBackground(getDrawable(R.drawable.ghost));
            cheatBoxFive.setText("qqw");
        }
        else if(cheatBoxFour.getText().toString().equals("qqe")){
            spellTwo.setBackground(getDrawable(R.drawable.wall));
            cheatBoxFive.setText("qqe");
        }
        else if(cheatBoxFour.getText().toString().equals("wwq")){
            spellTwo.setBackground(getDrawable(R.drawable.tornado));
            cheatBoxFive.setText("wwq");
        }
        else if(cheatBoxFour.getText().toString().equals("wwe")){
            spellTwo.setBackground(getDrawable(R.drawable.alacrity));
            cheatBoxFive.setText("wwe");
        }
        else if(cheatBoxFour.getText().toString().equals("eeq")){
            spellTwo.setBackground(getDrawable(R.drawable.spirit));
            cheatBoxFive.setText("eeq");
        }
        else if(cheatBoxFour.getText().toString().equals("eew")){
            spellTwo.setBackground(getDrawable(R.drawable.meteor));
            cheatBoxFive.setText("eew");
        }
        else if(cheatBoxFour.getText().toString().equals("qwe")){
            spellTwo.setBackground(getDrawable(R.drawable.blast));
            cheatBoxFive.setText("qwe");
        }
    }

    private ArrayList<Integer> CheckCurrentSpell(String one, String two, String three){

        //initiates an arraylist for the element counters
        ArrayList<Integer> elementPower = new ArrayList<>();

        //initiate element counters
        Integer quasCount = 0;
        Integer wexCount = 0;
        Integer exortCount = 0;

        //should be refactored into a loop
        if(one == "q"){
            quasCount++;
        }
        if(one == "w"){
            wexCount++;
        }
        if(one == "e"){
            exortCount++;
        }

        if(two == "q"){
            quasCount++;
        }
        if(two == "w"){
            wexCount++;
        }
        if(two == "e"){
            exortCount++;
        }

        if(three == "q"){
            quasCount++;
        }
        if(three == "w"){
            wexCount++;
        }
        if(three == "e"){
            exortCount++;
        }

        //puts the counters into the list and returns it
        //every spell cares about the amount of each element, not their order, which is why I do it like this
        elementPower.add(0, quasCount);
        elementPower.add(1, wexCount);
        elementPower.add(2, exortCount);

        return elementPower;

        /*
        Will refactor into something more simillar to this:

        elementCounter.add(0, one);
        elementCounter.add(1, two);
        elementCounter.add(2, three);
        ArrayList<String> elementCounter = new ArrayList<>();

        for(int i = 0; i < elementCounter.size(); i++) {
            if (elementCounter.get(i) == "q") {
                quasCount++;
            }
            else if (elementCounter.get(i) == "w") {
                wexCount++;
            }
            else if(elementCounter.get(i) == "e") {
                quasCount++;
            }
            i++;
        }

        I don't have the time to test it right now though, so the old code will remain
        */
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void InvokeSpell(String one, String two, String three) {

        Integer quasPower, wexPower, exortPower;
        ArrayList<Integer> spell = new ArrayList<>();
        //sends the active elements to the spellchecker to count each elements power
        spell = CheckCurrentSpell(one, two, three);
        quasPower = spell.get(0);
        wexPower = spell.get(1);
        exortPower = spell.get(2);

        Log.d("Quas Power:",String.valueOf(quasPower));
        Log.d("Wex Power:",String.valueOf(wexPower));
        Log.d("Exort Power:",String.valueOf(exortPower));

        //then, based on the "power level" of each element the correct spell is invoked and added in one of the two slots
        if((quasPower) == 3){
            cheatBoxFour.setText("qqq");
            spellOne.setBackground(getDrawable(R.drawable.coldsnap));
            //rolls on the "invoke" table in the sound library class
            sndLib.SoundRandomizer("invoke");
        }
        else if ((wexPower) == 3){
            cheatBoxFour.setText("www");
            spellOne.setBackground(getDrawable(R.drawable.emp));
            sndLib.SoundRandomizer("invoke");
        }
        else if ((exortPower) == 3){
            cheatBoxFour.setText("eee");
            spellOne.setBackground(getDrawable(R.drawable.strike));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((quasPower) == 2) && ((wexPower) == 1)){
            cheatBoxFour.setText("qqw");
            spellOne.setBackground(getDrawable(R.drawable.ghost));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((quasPower) == 2) && ((exortPower) == 1)){
            cheatBoxFour.setText("qqe");
            spellOne.setBackground(getDrawable(R.drawable.wall));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((wexPower) == 2) && ((quasPower) == 1)){
            cheatBoxFour.setText("wwq");
            spellOne.setBackground(getDrawable(R.drawable.tornado));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((wexPower) == 2) && ((exortPower) == 1)){
            cheatBoxFour.setText("wwe");
            spellOne.setBackground(getDrawable(R.drawable.alacrity));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((exortPower) == 2) && ((quasPower) == 1)){
            cheatBoxFour.setText("eeq");
            spellOne.setBackground(getDrawable(R.drawable.spirit));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((exortPower) == 2) && ((wexPower) == 1)){
            cheatBoxFour.setText("eew");
            spellOne.setBackground(getDrawable(R.drawable.meteor));
            sndLib.SoundRandomizer("invoke");
        }
        else if (((quasPower) == 1) && ((wexPower) == 1) && ((exortPower) == 1)){
            cheatBoxFour.setText("qwe");
            spellOne.setBackground(getDrawable(R.drawable.blast));
            sndLib.SoundRandomizer("invoke");
        }
    }

    //sets a random "goal" for the user to match
    //should be refactored into a separate class
    @TargetApi(Build.VERSION_CODES.M)
    public void SpellRandomizer(){

        Random rnd = new Random();
        Integer number;
        number = rnd.nextInt(10) + 1;

        //can (and should) also be refactored into a loop
        if (number == 1){
            randomSpell.setBackground(getDrawable(R.drawable.coldsnap));
            randomCheatBox.setText("qqq");
        }
        else if (number == 2){
            randomSpell.setBackground(getDrawable(R.drawable.emp));
            randomCheatBox.setText("www");
        }
        else if (number == 3){
            randomSpell.setBackground(getDrawable(R.drawable.strike));
            randomCheatBox.setText("eee");
        }
        else if (number == 4){
            randomSpell.setBackground(getDrawable(R.drawable.ghost));
            randomCheatBox.setText("qqw");
        }
        else if (number == 5){
            randomSpell.setBackground(getDrawable(R.drawable.wall));
            randomCheatBox.setText("qqe");
        }
        else if (number == 6){
            randomSpell.setBackground(getDrawable(R.drawable.tornado));
            randomCheatBox.setText("wwq");
        }
        else if (number == 7){
            randomSpell.setBackground(getDrawable(R.drawable.alacrity));
            randomCheatBox.setText("wwe");
        }
        else if (number == 8){
            randomSpell.setBackground(getDrawable(R.drawable.spirit));
            randomCheatBox.setText("eeq");
        }
        else if (number == 9){
            randomSpell.setBackground(getDrawable(R.drawable.meteor));
            randomCheatBox.setText("eew");
        }
        else if (number == 10){
            randomSpell.setBackground(getDrawable(R.drawable.blast));
            randomCheatBox.setText("qwe");
        }
    }

    public void CastSpell(String spellSlot){

        String spellCast, spellGoal;

        spellCast = spellSlot;
        spellGoal = randomCheatBox.getText().toString();

        //Compares the cast spell to the goal, and if they match one out of 10 different sound effects are played and the score goes up
        if(spellCast == spellGoal){

            ScoreCalculator("success");

            if (spellGoal == "qqq"){
                sndLib.soundPool.play(sndLib.soundColdsnap,1,1,0,0,1);
            }
            else if (spellGoal == "www"){
                sndLib.soundPool.play(sndLib.soundEmp,1,1,0,0,1);
            }
            else if (spellGoal == "eee"){
                sndLib.soundPool.play(sndLib.soundStrike,1,1,0,0,1);
            }
            else if (spellGoal == "qqw"){
                sndLib.soundPool.play(sndLib.soundGhost,1,1,0,0,1);
            }
            else if (spellGoal == "qqe"){
                sndLib.soundPool.play(sndLib.soundWall,1,1,0,0,1);
            }
            else if (spellGoal == "wwq"){
                sndLib.soundPool.play(sndLib.soundTornado,1,1,0,0,1);
            }
            else if (spellGoal == "wwe"){
                sndLib.soundPool.play(sndLib.soundAlacrity,1,1,0,0,1);
            }
            else if (spellGoal == "eeq"){
                sndLib.soundPool.play(sndLib.soundSpirit,1,1,0,0,1);
            }
            else if (spellGoal == "eew"){
                sndLib.soundPool.play(sndLib.soundMeteor,1,1,0,0,1);
            }
            else if (spellGoal == "qwe"){
                sndLib.soundPool.play(sndLib.soundBlast,1,1,0,0,1);
            }
        }
        else{
            ScoreCalculator("failure");
        }
        //Sets a new goal
        SpellRandomizer();
    }

    public void ScoreCalculator(String attempt) {

        Integer points, result;

        //These if-statements can be tidied up somewhat
        if (attempt == "success") {
            Log.d("MULTIPLIER BEFORE: ", multiplier.toString());
            //the multiplier goes up for every successful cast
            multiplier++;
            Log.d("MULTIPLIER AFTER: ", multiplier.toString());
            if((multiplier >= 3) && (multiplier < 5)){
                if(multiplier == 3){
                    //if the multiplier reaches 3, the score multiplier is set to 2x and text appears above the score
                    activeMultiplier.setText("x2");
                    activeCombo.setText("Rampage!");
                    sndLib.soundPool.play(sndLib.soundRampage,1,1,0,0,1);
                }
                if(multiplier == 4){
                    sndLib.SoundRandomizer("comboStart");
                }
                scoreMultiplier = 2;
            }
            else if ((multiplier >= 5) && (multiplier < 10)){
                if(multiplier == 5){
                    activeMultiplier.setText("x3");
                    activeMultiplier.setTextColor(Color.YELLOW);
                    activeCombo.setText("Dominating!");
                    activeCombo.setTextColor(Color.YELLOW);
                    sndLib.soundPool.play(sndLib.soundDominating,1,1,0,0,1);
                }
                if(multiplier == 7){
                    sndLib.SoundRandomizer("comboStart");
                }
                scoreMultiplier = 3;
            }
            else if ((multiplier >= 10 && (multiplier < 15))){
                if(multiplier == 10){
                    activeMultiplier.setText("x4");
                    activeCombo.setText("Unstoppable!");
                    activeCombo.setTypeface(activeCombo.getTypeface(), Typeface.BOLD);
                    sndLib.soundPool.play(sndLib.soundUnstoppable,1,1,0,0,1);
                }
                if(multiplier == 12){
                    sndLib.SoundRandomizer("comboStart");
                }
                scoreMultiplier = 4;
            }
            else if ((multiplier >= 15 && (multiplier < 20))){
                if(multiplier == 15){
                    activeMultiplier.setText("x5");
                    activeMultiplier.setTextSize(38);
                    activeMultiplier.setTextColor(Color.RED);
                    activeCombo.setText("Godlike!");
                    activeCombo.setTextSize(38);
                    activeCombo.setTextColor(Color.RED);
                    sndLib.soundPool.play(sndLib.soundGodlike,1,1,0,0,1);
                }
                if(multiplier == 17){
                    sndLib.SoundRandomizer("comboStart");
                }
                scoreMultiplier = 5;

            }
            else if ((multiplier == 20)) {
                activeCombo.setText("HOLY SHIT!");
                activeMultiplier.setText("x6");
                activeCombo.setTextSize(40);
                sndLib.soundPool.play(sndLib.soundHolyShit, 1, 1, 0, 0, 1);
                scoreMultiplier = 6;
            }
            else if(multiplier > 22){
                //for successful casts above 22 the score multiplier does not increase, the game instead rolls on a special "success sound" table
                sndLib.SoundRandomizer("wickedSick");
            }

            points = ((100) * scoreMultiplier);
            result = Integer.parseInt(currentScore.getText().toString()) + points;

            if(result > 2500){
                currentScore.setTextColor(Color.YELLOW);
            }
            if(result > 5000) {
                currentScore.setTextColor(Color.RED);
            }
            currentScore.setText(result.toString());
        }

        if (attempt == "failure") {
            if(multiplier >= 5 ){
                sndLib.SoundRandomizer("comboDrop");
            }
            else{
                sndLib.SoundRandomizer("failedSpell");
            }
            //the multiplier is set to 0 when a combo chain is broken
            multiplier = 0;
            activeMultiplier.setText("x0");
            activeCombo.setText("");
            activeMultiplier.setTextColor(Color.BLACK);
            activeCombo.setTextColor(Color.BLACK);

            points = 150;
            result = Integer.parseInt(currentScore.getText().toString()) - points;

            if(result > 2500){
                currentScore.setTextColor(Color.BLACK);
            }
            if((result > 5000) && (result > 2000)) {
                currentScore.setTextColor(Color.YELLOW);
            }

            currentScore.setText(result.toString());
        }

    }
}
