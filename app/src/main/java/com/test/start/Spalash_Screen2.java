package com.test.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Spalash_Screen2 extends AppCompatActivity {
    private Button spalash2button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen2);

        getSupportActionBar().setTitle("");

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));


        spalash2button = findViewById(R.id.Spalash2Button);

        spalash2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Spalash_Screen2.this, Spalash_Screen3.class);
                startActivity(intent);
            }
        });
    }
}