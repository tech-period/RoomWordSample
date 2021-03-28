package com.example.roomwordsample.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;

public class TagViewHolder2 extends RecyclerView.ViewHolder {
    CheckBox tagItemView;

    TagViewHolder2(View itemView){
        super(itemView);
        tagItemView = itemView.findViewById(R.id.checkBoxTag);
    }

}
