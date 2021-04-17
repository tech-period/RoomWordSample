package com.example.roomwordsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AllItemActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item);

        WordListAdapter wordAdapter = new WordListAdapter(this);

        //Test App ID
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView = findViewById(R.id.all_item_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //ワードのRecyclerViewを設定（縦スクロール）
        RecyclerView recyclerView = findViewById(R.id.all_item_recyclerview);
        recyclerView.setAdapter(wordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Viewモデルを作成
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        wordAdapter.setOnItemClickListener(new WordListAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, Word word) {
                word.setCheck(!word.isCheck());
                mWordViewModel.update(word);
            }
        });

        //全てのワードを取得
        mWordViewModel.getmAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@NonNull final List<Word> words) {
                wordAdapter.setmWords(words);
            }
        });

        //region RecyclerViewの長押しリスナーを設定
        wordAdapter.setOnItemLongClickListener(new WordListAdapter.onItemLongClickListener() {
            @Override
            public void onLongClick(View view,Word item) {
                Intent intent = new Intent(getApplication(),ItemEditActivity.class);
                intent.putExtra("ITEM_ID", item.getId());
                intent.putExtra("ITEM_NAME",item.getName());
                startActivity(intent);
            }
        });
        //endregion

        //+ボタンの定義
        FloatingActionButton fab = findViewById(R.id.all_item_fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DialogFragment dialog = new ItemAddDialogFragment();
                ItemAddDialogFragment itemDialog = new ItemAddDialogFragment();
                itemDialog.show(getSupportFragmentManager(), "Sample");
            }
        });

    }
}