package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AppHome extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private DatabaseReference mLocationsRef;
    private ValueEventListener mUserListener;
    private ValueEventListener mLocationsListener;
    private User user;
    private ArrayList<Location> locations;

    private Button backbutton;
    private TextView userType;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mLocationsRef = FirebaseDatabase.getInstance().getReference().child("locations");
        locations = new ArrayList<>();
        

        mRecyclerView = (RecyclerView) findViewById(R.id.locationList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new MyAdapter(locations);
        //mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<Location> mDataset;

        public class MyViewHolder extends RecyclerView.ViewHolder {
              public View mView;
              public TextView mContentView;
              public Location mLocation;

              public MyViewHolder(View view) {
                          super(view);
                          mView = view;
                          mContentView = (TextView) view.findViewById(R.id.content);
              }

        }

        public MyAdapter(ArrayList<Location> myDataset) {
                mDataset = myDataset;
        }

        @Override

        public MyAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
           //create new view
           View view = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.location_list_content, parent, false);
           return new MyViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mLocation = mDataset.get(position);
            holder.mContentView.setText(mDataset.get(position).getName());

        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
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
                user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    Toast.makeText(AppHome.this, "Unable to retrieve user from database.", Toast.LENGTH_LONG).show();
                } else {
                    userType.setText(user.getUserType());
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

        // Add value event listener to the locations
        // [START locations_event_listener]
        ValueEventListener locationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location objects and use the values to update the UI
                for (DataSnapshot locSnapshot: dataSnapshot.getChildren()) {
                    locations.add(locSnapshot.getValue(Location.class));
                }
                //mRecyclerView = (RecyclerView) findViewById(R.id.locationList);
                mAdapter = new MyAdapter(locations);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppHome.this, "Failed to load locations.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mLocationsRef.addValueEventListener(locationsListener);
        // [END locations_event_listener]

        // Keep copy of listeners so we can remove them when app stops
        mUserListener = userListener;
        mLocationsListener = locationsListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove user event listener
        if (mUserListener != null) {
            mUserRef.removeEventListener(mUserListener);
        }

        // Remove locations event listener
        if (mLocationsListener != null) {
            mLocationsRef.removeEventListener(mLocationsListener);
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
