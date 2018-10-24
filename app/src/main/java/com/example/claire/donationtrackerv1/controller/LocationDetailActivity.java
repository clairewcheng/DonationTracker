package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.example.claire.donationtrackerv1.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocationDetailActivity extends AppCompatActivity {

    private DatabaseReference mLocRef;
    private ValueEventListener mLocListener;

    private DatabaseReference mItemsRef;
    private ValueEventListener mItemsListener;
    private ArrayList<Item> items;
    private Location location;

    private TextView locInfo;
    private TextView locationName;
    private RecyclerView mItemsRecyclerView;
    private RecyclerView.Adapter mItemsAdpater;
    private RecyclerView.LayoutManager mItemsLayoutManager;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);

        locInfo = (TextView) findViewById(R.id.loc_info);
        locationName = (TextView) findViewById(R.id.locationName);

        Intent intent = getIntent();
        String locIndex = intent.getStringExtra("locationID");
        mLocRef = FirebaseDatabase.getInstance().getReference().child("locations").child(locIndex);
        mItemsRef = FirebaseDatabase.getInstance().getReference().child("items");


        mItemsRecyclerView = (RecyclerView) findViewById(R.id.itemlistrecylerview);
        mItemsLayoutManager = new LinearLayoutManager(this);
        mItemsRecyclerView.setLayoutManager(mItemsLayoutManager);

        // Add value event listener to the location
        // [START location_event_listener]
        ValueEventListener locListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location object and use the values to update the UI
                location = dataSnapshot.getValue(Location.class);
                query = mItemsRef.orderByChild("location").equalTo(location.getName());
                query.addListenerForSingleValueEvent(mItemsListener);

                if (location == null) {
                    Toast.makeText(LocationDetailActivity.this,
                            "Unable to retrieve location from database.",
                            Toast.LENGTH_LONG).show();
                } else {
                    locInfo.setText(location.toString());
                    locationName.setText(location.getName());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LocationDetailActivity.this, "Failed to load location.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mLocRef.addValueEventListener(locListener);
        // [END location_event_listener]

        // Keep copy of listener so we can remove it when app stops
        mLocListener = locListener;

        //Add items event listener to the items list
        ValueEventListener itemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items = new ArrayList<>();
                //Get items objects and update values
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    items.add(itemSnapshot.getValue(Item.class));
                }
                mItemsAdpater = new MyAdapter(items);
                mItemsRecyclerView.setAdapter(mItemsAdpater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LocationDetailActivity.this, "Failed to load items.", Toast.LENGTH_SHORT).show();
            }
        };
        mItemsRef.addValueEventListener(itemsListener);
        mItemsListener = itemsListener;
    }

    public class MyAdapter extends RecyclerView.Adapter<LocationDetailActivity.MyAdapter.MyViewHolder> {
        private ArrayList<Item> mDataset;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            public TextView mContentView;
            public Item mItem;

            public MyViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.itemForList);
            }
        }

        public MyAdapter(ArrayList<Item> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public LocationDetailActivity.MyAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            //create new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itemslistforrecylerview, parent, false);
            return new LocationDetailActivity.MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final LocationDetailActivity.MyAdapter.MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mItem = mDataset.get(position);
            holder.mContentView.setText(mDataset.get(position).getShortDesc());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on a phone, we need to change windows to the detail view
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                /*
                    pass along the id of the location so we can retrieve the correct data in
                    the next window
                 */

                    // TODO: 10/23/18  Please change item ref to a unique key
                    intent.putExtra("itemID", "" + "ID:" + holder.mItem.getShortDesc());

                    //now just display the new window
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove location event listener
        if (mLocListener != null) {
            mLocRef.removeEventListener(mLocListener);
        }

        if(mItemsListener != null) {
            mItemsRef.removeEventListener(mItemsListener);
        }
    }
}
