package com.example.roomwordsample;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder {
    TextView wordItemView;

    WordViewHolder(View itemView){
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }
}
