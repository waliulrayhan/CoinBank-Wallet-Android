package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class settings extends AppCompatActivity {
    private TextView account, noti, privacy, about;
    String Fname, Lname, Mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        //Notification Color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.TitleBarColor)));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        account = findViewById(R.id.accountID);
        noti = findViewById(R.id.SettingNotification);
        privacy = findViewById(R.id.SettingsPrivacy);
        about = findViewById(R.id.SettingsAbout);


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Fname = bundle.getString("Fname");
            Lname = bundle.getString("Lname");
            Mail = bundle.getString("mail");
        }

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, accountSettings.class);
                Bundle bundle = new Bundle();
                bundle.putString("Fname", Fname);
                bundle.putString("Lname", Lname);
                bundle.putString("mail", Mail);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, error.class);
                startActivity(intent);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, error.class);
                startActivity(intent);
            }
        });



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, settings_about.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}