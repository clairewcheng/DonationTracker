package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    private EditText emailField;
    private EditText passwordField;
    private Button signInButton;
    private Button goBackToCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailField = (EditText) findViewById(R.id.emailSignIn);
        passwordField = (EditText) findViewById(R.id.passwordSignIn);
        signInButton = (Button) findViewById(R.id.signInButton);
        goBackToCreate= (Button) findViewById(R.id.createScreen);

        signInButton.setOnClickListener(this);
        goBackToCreate.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void signIn() {
        android.text.Editable input = emailField.getText();
        String inputString = input.toString();
        String email = inputString.trim();
        input = passwordField.getText();
        inputString = input.toString();
        String password = inputString.trim();

        if(TextUtils.isEmpty(email)) {
            //send error message email field is empty
            Toast toast = Toast.makeText(this, "Please enter valid email...",
                    Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        if(TextUtils.isEmpty(password)) {
            //send error message password field is empty
            Toast toast = Toast.makeText(this, "Please enter valid password...",
                    Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast toast = Toast.makeText(SignInActivity.this, "Sign In Successful",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //navigate to app launch screen
                            Intent intentLaunchApp = new Intent(getApplicationContext(),
                                    AppHomeActivity.class);
                            startActivity(intentLaunchApp);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast toast = Toast.makeText(SignInActivity.this,
                                    "Please Correct Email and or Password", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        // ...
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == goBackToCreate) {
            // navigate to logged in screen(currently causing app crash
            Intent intentSignUP = new Intent(getApplicationContext(),CreateAccountActivity.class);
            startActivity(intentSignUP);
        }

        if (view == signInButton) {
            signIn();

        }

    }

}


