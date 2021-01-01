package com.example.roomwordsample;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public CustomItemDecoration(int space){
        this.space = space;
    }

    public static CustomItemDecoration createDefaultDecoration(Context context){
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
        return new CustomItemDecoration(spacingInPixels);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        outRect.top = space;
        outRect.bottom = space;
        outRect.right = space;
        outRect.left = space;
    }
}
