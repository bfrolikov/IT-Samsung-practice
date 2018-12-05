package com.example.bfrol.it_samsung;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecHolder> {
    private ArrayList<FeedItem> itemList;

    public RecAdapter(ArrayList<FeedItem> iL) {
        super();
        itemList = iL;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new RecHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder recHolder, int position) {
        FeedItem item = itemList.get(position);
        recHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
