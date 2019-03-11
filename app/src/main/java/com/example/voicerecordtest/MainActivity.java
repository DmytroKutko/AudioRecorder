package com.example.voicerecordtest;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";
    private Button btnStart, btnStop, btnPlay;
    private MediaRecorder recorder;
    private MediaPlayer mediaPlayer;
    private String outputFile;


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

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(outputFile);
    }

    private void initListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    recorder.prepare();
                    recorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                Toast.makeText(MainActivity.this, "Recording started", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recorder != null) {
                    try {
                        recorder.stop();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    } finally {
                        recorder.release();
                        recorder = null;
                    }
                }

                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);
                Toast.makeText(MainActivity.this, "Audio recorded successfully", Toast.LENGTH_SHORT).show();

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                try {
                    Log.d(TAG, "onClick: dataSource");
                    mediaPlayer.setDataSource(outputFile);
                    Log.d(TAG, "onClick: prepare");
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(MainActivity.this, "Playing audio", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
