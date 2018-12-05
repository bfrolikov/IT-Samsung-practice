package com.example.bfrol.it_samsung;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<FeedItem> items;

    public Adapter(ArrayList<FeedItem> itms) {
        super();
        items = itms;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (convertView != null)
            itemView = convertView;
        else
            itemView = inflater.inflate(R.layout.list_item, parent, false);
        TextView itemTextView = itemView.findViewById(R.id.item_title);
        FeedItem item = (FeedItem) getItem(position);
        itemTextView.setText(item.getTitle());
        return itemView;
    }
}
