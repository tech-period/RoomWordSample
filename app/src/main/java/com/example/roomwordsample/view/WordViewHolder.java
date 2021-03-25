package com.example.roomwordsample.view;

import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    CheckBox wordItemCheckbox;

    WordViewHolder(View itemView){
        super(itemView);
        wordItemCheckbox = itemView.findViewById(R.id.checkbox);
    }
}
