package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText emailField;
    private EditText passwordField;
    private Button createAccountButton;
    private Button goToSignInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = (EditText) findViewById(R.id.emailcreate);
        passwordField = (EditText) findViewById(R.id.passwordcreate);
        goToSignInButton = (Button) findViewById(R.id.signinscreen);
        createAccountButton= (Button) findViewById(R.id.createaccountbutton);

        goToSignInButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }


    private void createAccount() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //send error message email field is empty
            Toast.makeText(this, "Please enter valid email...", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password)) {
            //send error message password field is empty

            Toast.makeText(this, "Please enter valid password...", Toast.LENGTH_SHORT).show();

            return;
        }

        mAuth.createUserWithEmailAndPassword (email, password)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            // call to change activity to main application landing.
                            Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                            // navigate to logged in screen(currently causing app crash
                            //Intent intentSignUP = new Intent(getApplicationContext(),AppHome.class);
                            //startActivity(intentSignUP);

                        }
                        if(!task.isSuccessful()){
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Toast.makeText(MainActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view == createAccountButton) {
            createAccount();

        }

        if (view == goToSignInButton) {
            // navigate to logged in screen(currently causing app crash
            Intent intentSignUP = new Intent(getApplicationContext(),signinscreen.class);
            startActivity(intentSignUP);
        }

    }



}
