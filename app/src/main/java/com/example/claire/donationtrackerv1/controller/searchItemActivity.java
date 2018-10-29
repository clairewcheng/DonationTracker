package com.example.claire.donationtrackerv1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.example.claire.donationtrackerv1.R;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.text.Editable;
import android.text.TextWatcher;
import java.util.ArrayList;





public class searchItemActivity extends AppCompatActivity {
    private EditText search_edit_text;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    private RecyclerView mItemsRecyclerView;
    private RecyclerView.Adapter mItemsAdpater;
    private RecyclerView.LayoutManager mItemsLayoutManager;
    private Query query;
    ArrayList<String> nameList;
    ArrayList<String> categoryList;
    SearchAdapter searchAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nameList = new ArrayList<>();
        categoryList = new ArrayList<>();


        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    nameList.clear();
                    categoryList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }


    private void setAdapter(final String searchedString) {
        databaseReference.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */
                nameList.clear();
                categoryList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                 * Search all users for matching searched string
                 * */


                searchAdapter = new SearchAdapter(searchItemActivity.this, nameList, categoryList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}