package com.test.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity {
    ProgressBar FP;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        FP = findViewById(R.id.fp);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();
                startApp();
            }
        });
        thread.start();
    }

    private void dowork() {
         try {
                Thread.sleep(1500);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Exception " + e, Toast.LENGTH_LONG).show();
            }
    }

    private void startApp() {
        Intent intent = new Intent(FirstScreen.this, Spalash_Screen.class);
        startActivity(intent);
        finish();
    }
}