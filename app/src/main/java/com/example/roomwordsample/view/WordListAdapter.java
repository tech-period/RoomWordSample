package com.example.roomwordsample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.R;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private onItemClickListener clickListener;
    private onItemLongClickListener listener;

    //コンストラクタ（データソースを準備）
    WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    //ビューホルダを生成
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(itemView);
    }

    //ビューにデータを割り当て、リスト項目を生成
    @Override
    public void onBindViewHolder(WordViewHolder holder,int position){
        Word current = mWords.get(position);
        if (mWords != null){
            holder.wordItemCheckbox.setChecked(current.isCheck());
            holder.wordItemCheckbox.setText(current.getName());
        }else{
            holder.wordItemCheckbox.setText("No Word");
            holder.wordItemCheckbox.setChecked(false);
        }

        holder.wordItemCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view,current);
            }
        });
        holder.wordItemCheckbox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(view,current.getName());
                return false;
            }
        });
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.clickListener = listener;
    }
    public void setOnItemLongClickListener(onItemLongClickListener listener){
        this.listener = listener;
    }

    void setmWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    //データの項目数を取得
    @Override
    public int getItemCount(){
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    //region リスナーインターフェース
    public interface onItemClickListener{
        void onClick(View view,Word word);
    }
    public interface onItemLongClickListener{
        void onLongClick(View view, String text);
    }
    //endregion
}
