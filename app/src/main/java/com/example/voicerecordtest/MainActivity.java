package com.example.voicerecordtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.voicerecordtest.recorder.MyRecorder;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnStop, btnPlay;
    private MyRecorder myRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        initListener();
    }

    private void setInitialData() {
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnStart = findViewById(R.id.btnStart);

        myRecorder = new MyRecorder();

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);
    }

    private void initListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecorder.startRecording();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                Toast.makeText(MainActivity.this, "Recording started", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecorder.stopRecording();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);
                Toast.makeText(MainActivity.this, "Audio recorded successfully", Toast.LENGTH_SHORT).show();

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecorder.playRecord();
                Toast.makeText(MainActivity.this, "Playing audio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
