package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppHome extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private ValueEventListener mUserListener;
    private User _user;

    private Button backbutton;
    private TextView userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);

        backbutton = (Button) findViewById(R.id.tempsignoutbutton);
        userType = (TextView) findViewById(R.id.user_type_field);

        backbutton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(email.substring(0, email.indexOf(".")));
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the user
        // [START user_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                _user = dataSnapshot.getValue(User.class);
                if (_user == null) {
                    Toast.makeText(AppHome.this, "Unable to retrieve user from database.", Toast.LENGTH_LONG).show();
                } else {
                    userType.setText(_user.getUserType());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppHome.this, "Failed to load user.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mUserRef.addValueEventListener(userListener);
        // [END user_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mUserListener = userListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove user event listener
        if (mUserListener != null) {
            mUserRef.removeEventListener(mUserListener);
        }
    }

    @Override
    public void onClick(View view) {

        if (view == backbutton) {
            // navigate to logged in screen
            Intent intentLogOut = new Intent(getApplicationContext(),SignInScreen.class);
            startActivity(intentLogOut);
        }

    }
}
