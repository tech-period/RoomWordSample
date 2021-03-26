package com.example.roomwordsample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.R;

import java.util.List;

public class TagListAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private final LayoutInflater mInflater;
    private List<Tag> mTags;
    private  onItemLongClickListener listener;

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
        Tag current = mTags.get(position);
        if (mTags != null){
            holder.tagItemView.setText(current.getName());
        }else{
            holder.tagItemView.setText("No Word");
        }
        holder.tagItemView.setTextSize(20);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(view, current.getName());
                return false;
            }
        });
    }

    public void setOnItemLongClickListener(onItemLongClickListener listener){
        this.listener = listener;
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

    public interface onItemLongClickListener{
        void onLongClick(View view, String text);
    }
}
