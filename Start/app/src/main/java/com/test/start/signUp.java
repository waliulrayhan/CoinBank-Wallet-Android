package com.test.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity implements View.OnClickListener {

    private EditText firstNameEditText, lastNameEditText, EmailEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseReference = FirebaseDatabase.getInstance().getReference("Sign Up User Data");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        mAuth = FirebaseAuth.getInstance();


        firstNameEditText = findViewById(R.id.SignUpFirstNameEditText);
        lastNameEditText = findViewById(R.id.SignUpLastNameEditText);
        passwordEditText = findViewById(R.id.SignUpPasswordEditText);
        EmailEditText = findViewById(R.id.SignUpEmailEditText);
        confirmPasswordEditText = findViewById(R.id.SignUpConfirmPasswordEditText);
        signUpButton = findViewById(R.id.SignUpButton);
        progressBar = findViewById(R.id.SignUpProgressBar);


        signUpButton.setOnClickListener(this);
//        signInHereTextView.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.SignUpButton) {
            userSaveData();
            userRegister();
        }
    }


    private void userSaveData() {

        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = EmailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        SignUpUserSaveData data = new SignUpUserSaveData(firstName, lastName, email, password);
        databaseReference.child(key).setValue(data);
        //Toast.makeText(getApplicationContext(), "User data saved Successfully", Toast.LENGTH_LONG).show();

    }


    private void userRegister() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = EmailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (firstName.isEmpty()) {
            firstNameEditText.setError("Enter Your First Name");
            firstNameEditText.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            lastNameEditText.setError("Enter Your Last Name");
            lastNameEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            EmailEditText.setError("Enter a Email Address");
            EmailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailEditText.setError("Enter a Valid Email Address");
            EmailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Enter a Password");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Minimum Length of Password should be 6");
            passwordEditText.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Password Does not Match");
            confirmPasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    Bundle bundle = new Bundle();
                    bundle.putString("Fname", firstName);
                    bundle.putString("Lname", lastName);
                    bundle.putString("mail", email);


                    Intent intent = new Intent(signUp.this, signIn.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Registration is Successful", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    progressBar.setVisibility(View.GONE);
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User is already Registered", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Registration is Unsuccessful. Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


}