package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    private DatabaseReference mItemsRef;
    private ValueEventListener mItemsListener;
    private ArrayList<Item> results;
    private String searchTerm;
    private String category;
    private String locationName;
    private RecyclerView mItemsRecyclerView;
    private RecyclerView.Adapter mItemsAdapter;
    private RecyclerView.LayoutManager mItemsLayoutManager;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();
        searchTerm = intent.getStringExtra("searchTerm");
        if (searchTerm.equals("")) {
            searchTerm = null;
        }
        //TODO: change this based on selected category
        category = null;
        locationName = intent.getStringExtra("location");
        mItemsRef = FirebaseDatabase.getInstance().getReference().child("items");

        //TODO: check for correct recycler ID
        mItemsRecyclerView = (RecyclerView) findViewById(R.id.itemresultsrecyclerview);
        mItemsLayoutManager = new LinearLayoutManager(this);
        mItemsRecyclerView.setLayoutManager(mItemsLayoutManager);

        if (locationName != null) {
            query = mItemsRef.orderByChild("location").equalTo(locationName);
            query.addListenerForSingleValueEvent(mItemsListener);
        }

        //Add items event listener to the items list
        ValueEventListener itemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                results = new ArrayList<>();
                //Get items objects and update values
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item i = itemSnapshot.getValue(Item.class);
                    if (searchTerm != null && category != null) {
                        if (i.getShortDesc().equals(searchTerm) && i.getCategory().equals(category)) {
                            results.add(i);
                        }
                    } else if ((searchTerm != null && i.getShortDesc().equals(searchTerm))
                            || (category != null && i.getCategory().equals(i))) {
                        results.add(i);
                    } else if (searchTerm == null && category == null){
                        results.add(i);
                    }
                }
                mItemsAdapter = new SearchResultsActivity.MyAdapter(results);
                mItemsRecyclerView.setAdapter(mItemsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchResultsActivity.this, "Failed to load items.", Toast.LENGTH_SHORT).show();
            }
        };
        mItemsRef.addValueEventListener(itemsListener);
        mItemsListener = itemsListener;
    }

    public class MyAdapter extends RecyclerView.Adapter<SearchResultsActivity.MyAdapter.MyViewHolder> {
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
        public SearchResultsActivity.MyAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            //create new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_rv, parent, false);
            return new SearchResultsActivity.MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SearchResultsActivity.MyAdapter.MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mItem = mDataset.get(position);
            //holder.mContentView.setText(mDataset.get(position).getShortDesc());

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
    public void onStop() {
        super.onStop();

        // Remove items event listener
        if(mItemsListener != null) {
            mItemsRef.removeEventListener(mItemsListener);
        }
    }
}
