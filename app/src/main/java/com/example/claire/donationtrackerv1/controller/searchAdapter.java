package com.example.claire.donationtrackerv1.controller;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.R;

import java.util.ArrayList;

/**
 * Created by Dushyant Mainwal on 29-Oct-17.
 */

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> nameList;
    ArrayList<String> categoryList;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView name, category;
        public SearchViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }

    public searchAdapter(Context context, ArrayList<String> nameList, ArrayList<String> categoryList) {
        this.context = context;
        this.nameList = nameList;
        this.categoryList = categoryList;
    }

    @Override
    public searchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchlistitems, parent, false);
        return new searchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.name.setText(nameList.get(position));
        holder.category.setText(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}