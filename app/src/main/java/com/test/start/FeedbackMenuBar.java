package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackMenuBar extends AppCompatActivity {

    private EditText name, feddback;
    private Button feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_menu_bar);

        try {

            getSupportActionBar().setTitle("Feedback Option");
            //Notification Color
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.TitleBarColor)));

            //This is for Back Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            name = findViewById(R.id.NameFeedback);
            feddback = findViewById(R.id.Feedback);

            String NAME = name.getText().toString();
            String FEEDBACK = feddback.getText().toString();

            feedbackButton = findViewById(R.id.feedbackButton);

            feedbackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/email");


                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"waliulislamrayhan@gmail.com"});

                    intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback From App");
                    intent.putExtra(Intent.EXTRA_TEXT,"Name: "+NAME+"\nMessage: "+FEEDBACK);

                    startActivity(intent.createChooser(intent,"Feedback with"));
                }
            });


        }

        catch (Exception e){

            Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();

        }
    }

    //This is for Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}