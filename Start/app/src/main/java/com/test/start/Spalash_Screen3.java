package com.test.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Spalash_Screen3 extends AppCompatActivity {
    private Button spalash3button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen3);

        getSupportActionBar().setTitle("");

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));


        spalash3button = findViewById(R.id.Spalash3Button);

        spalash3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Spalash_Screen3.this, signIn.class);
                startActivity(intent);
            }
        });
    }
}