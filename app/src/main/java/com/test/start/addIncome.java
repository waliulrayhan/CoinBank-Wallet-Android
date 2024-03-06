package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addIncome extends AppCompatActivity {
    String[] IncomeSpinner;
    private Button addIncomeButton;
    private EditText IncomeAmount, IncomeComment;
    Spinner spinner;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    DatePickerDialog datePickerDialog;
    TextView date;
    String montth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        databaseReference = FirebaseDatabase.getInstance().getReference("Add Income User Data");
        mAuth = FirebaseAuth.getInstance();


        IncomeSpinner = getResources().getStringArray(R.array.Add_Income_Items);

        getSupportActionBar().setTitle("Add Income");
        //Notification Color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.TitleBarColor)));

        //This is for Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addIncomeButton = findViewById(R.id.addIncomeButton);
        spinner = findViewById(R.id.AddIncomeSpinner);
        IncomeAmount = findViewById(R.id.amountTakaEditText);
        IncomeComment = findViewById(R.id.commentEditText);
        date = findViewById(R.id.DateID2);
        //t = findViewById(R.id.textIncome);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = new DatePicker(addIncome.this);
                int currentDay = datePicker.getDayOfMonth();
                int currentMonth = datePicker.getMonth()+1;
                int currentYear = datePicker.getYear();

                datePickerDialog = new DatePickerDialog(addIncome.this,

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                if(i1==0)
                                {
                                    montth = "January";
                                }

                                if(i1==1)
                                {
                                    montth = "February";
                                }

                                if(i1==2)
                                {
                                    montth = "March";
                                }

                                if(i1==3)
                                {
                                    montth = "April";
                                }

                                if(i1==4)
                                {
                                    montth = "May";
                                }

                                if(i1==5)
                                {
                                    montth = "June";
                                }

                                if(i1==6)
                                {
                                    montth = "July";
                                }

                                if(i1==7)
                                {
                                    montth = "August";
                                }

                                if(i1==8)
                                {
                                    montth = "September";
                                }

                                if(i1==9)
                                {
                                    montth = "October";
                                }

                                if(i1==10)
                                {
                                    montth = "November";
                                }

                                if(i1==11)
                                {
                                    montth = "December";
                                }

                                date.setText(i2+" "+montth+" "+i);
                            }
                        }, currentYear, (currentMonth-1), currentDay);

                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.TextViewSample,IncomeSpinner);
        spinner.setAdapter(adapter);

        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeFunction();
            }
        });

    }

    //This is for Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void addIncomeFunction() {

        String Income_Type = spinner.getSelectedItem().toString();
        String Income_Amount = IncomeAmount.getText().toString().trim();
        String Income_Comment = IncomeComment.getText().toString().trim();

        //Toast.makeText(getApplicationContext(), "String"+Income_Amount, Toast.LENGTH_LONG).show();

        //int myNum = Integer.parseInt(IncomeAmount.getText().toString());



        if (Income_Amount.isEmpty()) {
            IncomeAmount.setError("This field is required");
            IncomeAmount.requestFocus();
            return;
        }

        if (Income_Comment.isEmpty()) {
            IncomeComment.setError("This field is required");
            IncomeComment.requestFocus();
            return;
        }


           // int1 = Integer.parseInt(IncomeAmount.getText().toString());
            //t.setText(""+int1);


        String key = databaseReference.push().getKey();

        AddUserDetails data = new AddUserDetails(Income_Type, Income_Amount, Income_Comment);
        databaseReference.child(key).setValue(data);
        //Toast.makeText(getApplicationContext(), "User data saved Successfully", Toast.LENGTH_LONG).show();


        IncomeAmount.setText("");
        IncomeComment.setText("");

//        Bundle bundle2 = new Bundle();
//        bundle2.putInt("IncomeAmount", int1);

        Intent intent = new Intent(addIncome.this, homePage.class);
        //intent.putExtras(bundle2);
        startActivity(intent);

    }
}