package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button yesbutton;
    private Button nobutton;
    private Button nextbutton;
    private Button clearbutton;
    private EditText editText;
    private int currentQid = 0;
    private boolean flag = false;
    private boolean returnfromactivity = false;
    private boolean lastq = false;
    private static final String TAG = "MainActivity";
    String Currentstate = "", prevstate = "Launched Activity";

    private Questionbank[] questionbanks = new Questionbank[]{
            new Questionbank(R.string.fever, false),
            new Questionbank(R.string.runningnose, false),
            new Questionbank(R.string.cough, false),
            new Questionbank(R.string.headBodyAche, false),
            new Questionbank(R.string.taste, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Currentstate = "create";
        toastlog(Currentstate, prevstate);

        yesbutton = findViewById(R.id.yes);
        nobutton = findViewById(R.id.no);
        nextbutton = findViewById(R.id.nextbutton);
        clearbutton = findViewById(R.id.clearbutton);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.covid_symptom);

        yesbutton.setOnClickListener(this);
        nobutton.setOnClickListener(this);
        clearbutton.setOnClickListener(this);
        nextbutton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.yes:
                flag = true;
                questionbanks[currentQid].setAnswer(true);
                nextbutton.setEnabled(true);
                Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.no:
                flag = true;
                questionbanks[currentQid].setAnswer(false);
                nextbutton.setEnabled(true);
                Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nextbutton:
                //common part
                String s = editText.getText().toString();
                if (s.matches("")) {
                    Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (flag) {

                    if (currentQid < 5 && !lastq)
                        currentQid = (currentQid + 1);
                    textView.setText(questionbanks[currentQid].getAnswerid());
                    flag = false;
                    if (currentQid == 4 && !lastq) {
                        nextbutton.setText(R.string.submit);
                        nextbutton.setEnabled(false);
                        lastq = true;
                    } else if (currentQid == 4 && lastq) {
                        Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                        intent.putExtra("full_name", editText.getText().toString());
                        intent.putExtra("answer1", questionbanks[0].answer);
                        intent.putExtra("answer2", questionbanks[1].answer);
                        intent.putExtra("answer3", questionbanks[2].answer);
                        intent.putExtra("answer4", questionbanks[3].answer);
                        intent.putExtra("answer5", questionbanks[4].answer);
                        intent.putExtra("CurrentState", Currentstate);
                        returnfromactivity = true;
                        lastq = false;
                        startActivity(intent);

                        //finish();
                    }
                }
                break;

            case R.id.clearbutton:
                clearbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentQid = 0;
                        for (int i = 0; i < 5; i++)
                            questionbanks[i].setAnswer(false);
                        flag = false;
                        textView.setText(questionbanks[currentQid].getAnswerid());
                        nextbutton.setText(R.string.next);
                        editText.setText("");
                        lastq = false;
                    }
                });
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        prevstate = Currentstate;
        Currentstate = "Start";
        toastlog(Currentstate, prevstate);
    }

    @Override
    public void onPause() {
        super.onPause();
        prevstate = Currentstate;
        Currentstate = "Paused";
        toastlog(Currentstate, prevstate);
    }

    @Override
    public void onResume() {
        super.onResume();
        prevstate = Currentstate;
        Currentstate = "Resumed";
        toastlog(Currentstate, prevstate);

    }

    @Override
    public void onStop() {
        super.onStop();
        prevstate = Currentstate;
        Currentstate = "Stopped";
        toastlog(Currentstate, prevstate);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        prevstate = Currentstate;
        Currentstate = "Destroyed";
        toastlog(Currentstate, prevstate);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        prevstate = Currentstate;
        Currentstate = "Restart";
        toastlog(Currentstate, prevstate);
        if (returnfromactivity) {
            currentQid = 0;
            for (int i = 0; i < 5; i++)
                questionbanks[i].setAnswer(false);
            flag = false;
            textView.setText(questionbanks[currentQid].getAnswerid());
            nextbutton.setText(R.string.next);
            editText.setText("");
            yesbutton.setOnClickListener(this);
            nobutton.setOnClickListener(this);
            clearbutton.setOnClickListener(this);
            nextbutton.setOnClickListener(this);
        }
        returnfromactivity = false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        prevstate = Currentstate;
        Currentstate = "SaveInstanceState";
        toastlog(Currentstate, prevstate);
        super.onSaveInstanceState(outState);
        EditText editText = (EditText) findViewById(R.id.editText);
        CharSequence fullname = editText.getText();
        outState.putCharSequence("SavedData", fullname);
        outState.putInt("currentQid", currentQid);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        prevstate = Currentstate;
        Currentstate = "RestoreInstanceState";
        toastlog(Currentstate, prevstate);
        super.onRestoreInstanceState(savedInstanceState);
        CharSequence fullname = savedInstanceState.getCharSequence("SavedData");
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(fullname);
        currentQid = savedInstanceState.getInt("currentQid");
        textView.setText(questionbanks[currentQid].getAnswerid());


    }

    public void toastlog(String Currentstate, String prevstate) {
        Log.d(TAG, "State of activity " + TAG + " changed from " + prevstate + " to " + Currentstate);
        Toast.makeText(MainActivity.this, "State of activity " + TAG + " changed from " + prevstate + " to " + Currentstate, Toast.LENGTH_SHORT).show();
    }
}

