package com.example.roomwordsample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.R;

import java.util.List;

public class TagListAdapter2 extends RecyclerView.Adapter<TagViewHolder2> {
    private final LayoutInflater mInflater;
    private List<Tag> mTags;
    private List<Relation> mRelations;
    private  onItemClickListener listener;
    private int itemId;

    //コンストラクタ（データソースを準備）
    TagListAdapter2(Context context){
        mInflater = LayoutInflater.from(context);
    }
    TagListAdapter2(Context context, int itemId){
        mInflater = LayoutInflater.from(context);
        this.itemId = itemId;
    }

    //ビューホルダを生成
    @Override
    public TagViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview3_item, parent, false);
        return new TagViewHolder2(itemView);
    }

    //ビューにデータを割り当て、リスト項目を生成
    @Override
    public void onBindViewHolder(TagViewHolder2 holder, int position){
        Tag current = mTags.get(position);
        if (mTags != null){
            holder.tagItemView.setText(current.getName());
        }else{
            holder.tagItemView.setText("No Word");
        }
        Relation relation = new Relation(itemId , current.getId());
        holder.tagItemView.setChecked(mRelations.contains(relation));
        holder.tagItemView.setTextSize(20);

        holder.tagItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, current.getId());
            }
        });
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    void setmTags(List<Tag> tags){
        mTags = tags;
        notifyDataSetChanged();
    }
    void setmRelations(List<Relation> relations){
        mRelations = relations;
        notifyDataSetChanged();
    }

    //データの項目数を取得
    @Override
    public int getItemCount(){
        if (mTags != null)
            return mTags.size();
        else return 0;
    }

    //リスナーインターフェース
    public interface onItemClickListener{
        void onClick(View view, int id);
    }
}
