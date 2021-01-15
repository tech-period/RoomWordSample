package com.example.roomwordsample.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;

public class TagViewHolder extends RecyclerView.ViewHolder {
    TextView tagItemView;

    TagViewHolder(View itemView){
        super(itemView);
        tagItemView = itemView.findViewById(R.id.textView2);
    }

}
