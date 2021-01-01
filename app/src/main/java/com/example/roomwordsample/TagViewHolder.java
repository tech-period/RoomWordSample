package com.example.roomwordsample;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TagViewHolder extends RecyclerView.ViewHolder {
    TextView tagItemView;

    TagViewHolder(View itemView){
        super(itemView);
        tagItemView = itemView.findViewById(R.id.textView2);
    }

}
