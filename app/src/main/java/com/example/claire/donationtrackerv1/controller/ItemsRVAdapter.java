package com.example.claire.donationtrackerv1.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;


/**
 * ItemsRVAdapter controls the items recycler view-- controls data set that populates it
 */
public class ItemsRVAdapter extends RecyclerView.Adapter<ItemsRVAdapter.ItemsRVViewHolder> {
    private final java.util.List<Item> mDataSet;
    private final Context mContext;

    public class ItemsRVViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mContentView;
        Item mItem;

        ItemsRVViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.itemForList);
        }
    }

    /**
     * ItemsRVAdapter allow local data and context to be set
     * @param myContext context
     * @param myDataSet data set for replacing the old one
     */
    public ItemsRVAdapter(Context myContext, java.util.List<Item> myDataSet) {
        mContext = myContext;
        mDataSet = myDataSet;
    }

    /**
     * refreshItems method helps clear and refresh data in the recycler view data set local store
     * @param items new items for update
     */
    public void refreshItems(java.util.Collection<Item> items) {
        this.mDataSet.clear();
        this.mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    @NonNull public ItemsRVAdapter.ItemsRVViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                                         int viewType) {
        //create new view
        android.view.LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_rv, parent, false);
        return new ItemsRVAdapter.ItemsRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsRVAdapter.ItemsRVViewHolder holder,
                                 final int position) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        holder.mItem = mDataSet.get(position);
        String text = holder.mItem.getShortDesc();
        holder.mContentView.setText(text);

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
        return mDataSet.size();
    }
}
