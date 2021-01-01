package com.example.roomwordsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TagListAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private final LayoutInflater mInflater;
    private List<Tag> mTags;

    //コンストラクタ（データソースを準備）
    TagListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    //ビューホルダを生成
    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview2_item, parent, false);
        return new TagViewHolder(itemView);
    }

    //ビューにデータを割り当て、リスト項目を生成
    @Override
    public void onBindViewHolder(TagViewHolder holder, int position){
        if (mTags != null){
            Tag current = mTags.get(position);
            holder.tagItemView.setText(current.getTag());
        }else{
            holder.tagItemView.setText("No Word");
        }
    }

    void setmTags(List<Tag> tags){
        mTags = tags;
        notifyDataSetChanged();
    }

    //データの項目数を取得
    @Override
    public int getItemCount(){
        if (mTags != null)
            return mTags.size();
        else return 0;
    }
}
