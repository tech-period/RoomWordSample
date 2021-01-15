package com.example.roomwordsample.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    TextView wordItemView;

    WordViewHolder(View itemView){
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }
}
