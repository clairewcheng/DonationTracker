package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * ItemDetailActivity controls the page that shows the details for an item when it's general
 * card is clicked.
 */
public class ItemDetailActivity extends AppCompatActivity {
    private DatabaseReference mItemRef;
    private ValueEventListener mItemListener;

    private Item item;
    private TextView itemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        itemInfo = findViewById(R.id.itemInfo);

        Intent intent = getIntent();
        String itemID = intent.getStringExtra("itemID");
        mItemRef = FirebaseDatabase.getInstance().getReference().child("items").child(itemID);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the item
        // [START item_event_listener]
        ValueEventListener itemListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location object and use the values to update the UI
                item = dataSnapshot.getValue(Item.class);

                if (item == null) {
                    Toast.makeText(ItemDetailActivity.this,
                            "Unable to retrieve item from database.",
                            Toast.LENGTH_LONG).show();
                } else {
                    itemInfo.setText(item.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ItemDetailActivity.this, "Failed to load item.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mItemRef.addValueEventListener(itemListener);
        // [END location_event_listener]

        // Keep copy of listener so we can remove it when app stops
        mItemListener = itemListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        if(mItemListener != null) {
            mItemRef.removeEventListener(mItemListener);
        }
    }
}




