package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Second_Activity extends AppCompatActivity {


    private TextView fullname, rtpcrresults;
    private EditText answer1, answer2, answer3, answer4, answer5;
    private Button result;
    private String Currentstate, prevstate;
    private static final String TAG = "Second Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fullname = findViewById(R.id.fullname);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer5 = findViewById(R.id.answer5);
        result = findViewById(R.id.result);
        rtpcrresults = findViewById(R.id.rtpcrresults);

        Intent intent = getIntent();
        String full_name = intent.getStringExtra("full_name");
        boolean answerb1 = intent.getBooleanExtra("answer1", false);
        boolean answerb2 = intent.getBooleanExtra("answer2", false);
        boolean answerb3 = intent.getBooleanExtra("answer3", false);
        boolean answerb4 = intent.getBooleanExtra("answer4", false);
        boolean answerb5 = intent.getBooleanExtra("answer5", false);
        Currentstate = intent.getStringExtra("CurrentState");
        int countyes = 0;
        fullname.setText(full_name);
        if (answerb1) {
            countyes++;
            answer1.setText(R.string.yes);
        } else
            answer1.setText(R.string.no);
        if (answerb2) {
            countyes++;
            answer2.setText(R.string.yes);
        } else
            answer2.setText(R.string.no);
        if (answerb3) {
            countyes++;
            answer3.setText(R.string.yes);
        } else
            answer3.setText(R.string.no);
        if (answerb4) {
            countyes++;
            answer4.setText(R.string.yes);
        } else
            answer4.setText(R.string.no);
        if (answerb5) {
            countyes++;
            answer5.setText(R.string.yes);
        } else
            answer5.setText(R.string.no);

        int finalCountyes = countyes;
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalCountyes <= 3) {
                    rtpcrresults.setText(R.string.positive);
                } else
                    rtpcrresults.setText(R.string.negative);
            }
        });
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
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //prevstate = Currentstate;
        //Currentstate = "SaveInstanceState";
        //toastlog(Currentstate, prevstate);
        super.onSaveInstanceState(outState);
        TextView rtpcrresults = (TextView) findViewById(R.id.rtpcrresults);
        CharSequence fullname = rtpcrresults.getText();
        outState.putCharSequence("SavedData", fullname);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        prevstate = Currentstate;
        Currentstate = "RestoreInstanceState";
        toastlog(Currentstate, prevstate);
        super.onRestoreInstanceState(savedInstanceState);
        CharSequence fullname = savedInstanceState.getCharSequence("SavedData");
        TextView rtpcrresults = (TextView) findViewById(R.id.rtpcrresults);
        rtpcrresults.setText(fullname);
    }

    public void toastlog(String Currentstate, String prevstate) {
        Log.d(TAG, "State of activity " + TAG + " changed from " + prevstate + " to " + Currentstate);
        Toast.makeText(Second_Activity.this, "State of activity " + TAG + " changed from " + prevstate + " to " + Currentstate, Toast.LENGTH_SHORT).show();
    }

}