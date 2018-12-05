package com.example.bfrol.it_samsung;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;

public class RecHolder extends RecyclerView.ViewHolder {
    public RecHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void bind(FeedItem item)
    {
        TextView itemTitle = itemView.findViewById(R.id.item_title);
        ImageView itemThumbnail = itemView.findViewById(R.id.item_thumb);
        TextView itemDescription = itemView.findViewById(R.id.item_description);
        itemTitle.setText(item.getTitle());
        itemDescription.setText(item.getDescription());
        Picasso.with(itemThumbnail.getContext()).load(item.getThumbnail()).into(itemThumbnail);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(item.getLink()));
                itemThumbnail.getContext().startActivity(i);
            }
        });
    }
}
