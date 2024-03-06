package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class homePage extends AppCompatActivity {

    //For Floating Action Button
    FloatingActionButton add, income, expanse;
    Animation fabOpen, fabClose, rotateForword, rotateBackword;
    ImageView cashImg, expanseImg;
    boolean isOpen = false;
    String Fname;
    String Lname;
    String Mail;
    int adIncome, exIncome;
    TextView savings, sngImg, cashValue, in, ex, expanseValue, cValue;
    Integer totalI = 0, totalE = 0, totalCash;


    DatabaseReference TotalIncome, TotalExpanse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().setTitle(R.string.Coinbank_Wallet);
        //Notification Color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.TitleBarColor)));

        TotalIncome = FirebaseDatabase.getInstance().getReference("Add Income User Data");
        TotalExpanse = FirebaseDatabase.getInstance().getReference("Add Expanse User Data");


        add = findViewById(R.id.addFloatingActionButton);
        income = findViewById(R.id.addIncomeFloatingActionButton);
        expanse = findViewById(R.id.addExpanseFloatingActionButton);
        cashImg = findViewById(R.id.cashImage);
        expanseImg = findViewById(R.id.expanseImage);
        //savings = findViewById(R.id.savingsValue);
        //sngImg = findViewById(R.id.savingsImageId);
        cashValue = findViewById(R.id.cashValue);
        expanseValue = findViewById(R.id.expenseValue);
        cValue = findViewById(R.id.savingsValue);
        in = findViewById(R.id.IncomeText);
        ex = findViewById(R.id.ExpanseText);


        TotalIncome.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    AddUserDetails UserData = dataSnapshot1.getValue(AddUserDetails.class);

                    Integer cost1 = Integer.valueOf(UserData.getAmount());
                    totalI = totalI + cost1;
                }
                cashValue.setText("" + totalI);

                totalCash = totalI - totalE;
                cValue.setText("" + totalCash);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        TotalExpanse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    AddUserDetails UserData = dataSnapshot1.getValue(AddUserDetails.class);

                    Integer cost2 = Integer.valueOf(UserData.getAmount());
                    totalE = totalE + cost2;
                }
                expanseValue.setText("" + totalE);

                totalCash = totalI - totalE;
                cValue.setText("" + totalCash);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//
//        totalCash = totalI - totalE;
//        cValue.setText("" + totalCash);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Fname = bundle.getString("Fname");
            Lname = bundle.getString("Lname");
            Mail = bundle.getString("mail");
        }


//        Bundle bundle2 = getIntent().getExtras();
//        if (bundle2 != null) {
//            adIncome = bundle2.getInt("IncomeAmount");
//        }
//        cashValue.setText(""+adIncome);
//
//
//
//        Bundle bundle3 = getIntent().getExtras();
//        if (bundle3 != null) {
//            exIncome = bundle3.getInt("ExpanseAmount");
//        }
//        expanseValue.setText(""+exIncome);


        cashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homePage.this, CashHistory.class);
                startActivity(intent);

            }
        });

        expanseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homePage.this, ExpanseHistory.class);
                startActivity(intent);
            }
        });

        //Animation
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateForword = AnimationUtils.loadAnimation(this, R.anim.rotate_forword);
        rotateBackword = AnimationUtils.loadAnimation(this, R.anim.rotate_backword);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent intent = new Intent(homePage.this, addIncome.class);
                startActivity(intent);
            }
        });

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent intent = new Intent(homePage.this, addIncome.class);
                startActivity(intent);
            }
        });

        expanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent intent = new Intent(homePage.this, addExpanse.class);
                startActivity(intent);
            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent intent = new Intent(homePage.this, addExpanse.class);
                startActivity(intent);
            }
        });
    }


    private void animateFab() {
        if (isOpen) {
            add.startAnimation(rotateForword);
            income.startAnimation(fabClose);
            expanse.startAnimation(fabClose);
            in.startAnimation(fabClose);
            ex.startAnimation(fabClose);
            income.setClickable(false);
            expanse.setClickable(false);
            in.setClickable(false);
            ex.setClickable(false);
            isOpen = false;
        } else {
            add.startAnimation(rotateBackword);
            income.startAnimation(fabOpen);
            expanse.startAnimation(fabOpen);
            in.startAnimation(fabOpen);
            ex.startAnimation(fabOpen);
            income.setClickable(true);
            expanse.setClickable(true);
            in.setClickable(true);
            ex.setClickable(true);
            isOpen = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.SettingsMenu) {
            Intent intent = new Intent(homePage.this, settings.class);
            Bundle bundle = new Bundle();
            bundle.putString("Fname", Fname);
            bundle.putString("Lname", Lname);
            bundle.putString("mail", Mail);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.ShareMenu) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "CoinBank Wallet App";
            String body = "Wallet by CoinBank Wallet is a personal finance management tools that helps you track your spending, so you stay in control and achieve your goals.";

            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "Share with "));


        }

        if (item.getItemId() == R.id.RateUsMenu) {
            Intent intent = new Intent(homePage.this, error.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.FeedbackMenu) {
            Intent intent = new Intent(homePage.this, FeedbackMenuBar.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.HelpMenu) {
            Intent intent = new Intent(homePage.this, HelpMenuBar.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}