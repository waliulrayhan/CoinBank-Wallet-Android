package com.test.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Spalash_Screen extends AppCompatActivity {
    private Button spalash1button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen);

        getSupportActionBar().setTitle("");

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));


        spalash1button = findViewById(R.id.Spalash1Button);

        spalash1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Spalash_Screen.this, Spalash_Screen2.class);
                startActivity(intent);
            }
        });
    }
}