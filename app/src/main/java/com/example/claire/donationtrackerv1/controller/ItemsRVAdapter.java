package com.example.claire.donationtrackerv1.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;

import java.util.ArrayList;

public class ItemsRVAdapter extends RecyclerView.Adapter<ItemsRVAdapter.ItemsRVViewHolder> {
    private ArrayList<Item> mDataset;
    private Context mContext;

    public class ItemsRVViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mContentView;
        public Item mItem;

        public ItemsRVViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.itemForList);
        }
    }

    public ItemsRVAdapter(Context myContext, ArrayList<Item> myDataset) {
        mContext = myContext;
        mDataset = myDataset;
    }

    public void refreshItems(ArrayList<Item> items) {
        this.mDataset.clear();
        this.mDataset.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ItemsRVAdapter.ItemsRVViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        //create new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_rv, parent, false);
        return new ItemsRVAdapter.ItemsRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemsRVAdapter.ItemsRVViewHolder holder,
                                 final int position) {
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
                Intent intent = new Intent(mContext, ItemDetailActivity.class);
                /*
                    pass along the id of the location so we can retrieve the correct data in
                    the next window
                 */

                intent.putExtra("itemID", "" + "ID:" + holder.mItem.getShortDesc());

                //now just display the new window
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
