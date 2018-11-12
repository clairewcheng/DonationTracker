package com.example.claire.donationtrackerv1.controller;

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

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Location;
import com.example.claire.donationtrackerv1.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppHomeActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private DatabaseReference mLocationsRef;
    private ValueEventListener mUserListener;
    private ValueEventListener mLocationsListener;
    private User user;
    private ArrayList<Location> locations;

    private Button backButton;
    private Button donateItemButton;
    private Button searchButton;

    private Button viewMapButton;
    //private TextView userType;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);

        backButton = (Button) findViewById(R.id.tempSignOutButton);
        //userType = (TextView) findViewById(R.id.user_type_field);
        donateItemButton = (Button) findViewById(R.id.goToDonateItemButton);
        searchButton = (Button) findViewById(R.id.search_button);
        viewMapButton = (Button) findViewById(R.id.viewItems);

        backButton.setOnClickListener(this);
        donateItemButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        viewMapButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("users")
                .child(email.substring(0, email.indexOf(".")));
        mLocationsRef = FirebaseDatabase.getInstance().getReference().child("locations");
        locations = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.locationList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Add value event listener to the locations
        ValueEventListener locationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Empty ArrayList on Data Change to prevent duplicates
                locations = new ArrayList<>();
                // Get Location objects and use the values to update the UI
                for (DataSnapshot locSnapshot: dataSnapshot.getChildren()) {
                    locations.add(locSnapshot.getValue(Location.class));
                }
                // Connect Locations ArrayList to RecyclerView
                mAdapter = new MyAdapter(locations);
                mRecyclerView.setAdapter(mAdapter);
            }

            // If no data to display, notify user with TOAST message
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppHomeActivity.this,
                        "Failed to load locations.", Toast.LENGTH_SHORT).show();
            }
        };
        mLocationsRef.addValueEventListener(locationsListener);
        //Save copy of Location Listener for onStop so it can be removed later
        mLocationsListener = locationsListener;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private java.util.List<Location> mDataSet;

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

        public MyAdapter(java.util.List<Location> myDataSet) {
                mDataSet = myDataSet;
        }

        //Attaching Location Card for RecyclerView to the View Holder when displaying locations.
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
           //create new view
           View view = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.location_card_rv, parent, false);
           return new MyViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            //Get element from your data set at this position
            //Replace the contents of the view with that element
            holder.mLocation = mDataSet.get(position);
            holder.mContentView.setText(mDataSet.get(position).getName());


            //Set up a listener to handle if the user clicks on this list item.

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //on a phone, we need to change windows to the detail view
                //create our new intent with the new screen (activity)
                Intent intent = new Intent(getApplicationContext(), LocationDetailActivity.class);
                /*
                    pass along the id of the location so we can retrieve the correct data in
                    the next window
                    position = position of location in list of locations
                 */
                intent.putExtra("locationID", "" + position);

                //now just display the new window
                startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the user
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    // Notify User Object Failure
                    Toast.makeText(AppHomeActivity.this,
                            "Unable to retrieve user from database.",
                            Toast.LENGTH_LONG).show();
                }
                /*
                else {
                    userType.setText(user.getUserType());
                }
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Notify Database Failure
                Toast.makeText(AppHomeActivity.this, "Failed to load user.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mUserRef.addValueEventListener(userListener);
        // Keep copy of listeners so we can remove them when app stops
        mUserListener = userListener;

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

        if (view == backButton) {
            // navigate to log in screen
            Intent intentLogOut = new Intent(getApplicationContext(),SignInActivity.class);
            startActivity(intentLogOut);
        }

        if (view == donateItemButton) {
            // navigate to donate item screen
            Intent intentDonateItem = new Intent(getApplicationContext(), AddItemActivity.class);
            startActivity(intentDonateItem);
        }

        if (view == searchButton) {
            // navigate to search screen
            Intent intentGoToSearch = new Intent(getApplicationContext(),
                    SearchAndFiltersActivity.class);
             startActivity(intentGoToSearch);
        }
        if (view == viewMapButton) {
            // navigate to Maps
            Intent intentViewMap = new Intent( getApplicationContext(), MapsActivity.class);
            startActivity(intentViewMap);
        }

    }
}
