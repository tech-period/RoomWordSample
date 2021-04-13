package com.example.roomwordsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;
    private TagViewModel mTagViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int ITEM_EDIT_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Test App ID
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        WordListAdapter wordAdapter = new WordListAdapter(this);
        TagListAdapter tagAdapter = new TagListAdapter(this);

        //BottomNavigationViewの設定
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //region RecyclerViewの設定
        //タグのRecyclerViewを設定（横スクロール）
        RecyclerView recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setAdapter(tagAdapter);
        //recyclerView2.addItemDecoration(CustomItemDecoration.createDefaultDecoration(this));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(manager);

        //ワードのRecyclerViewを設定（縦スクロール）
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(wordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //endregion

        //Viewモデルを作成
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mTagViewModel = new ViewModelProvider(this).get(TagViewModel.class);


        wordAdapter.setOnItemClickListener(new WordListAdapter.onItemClickListener() {
            @Override
            public void onClick(View view,Word word) {
                word.setCheck(!word.isCheck());
                mWordViewModel.update(word);
            }
        });
        //region RecyclerViewの長押しリスナーを設定
        wordAdapter.setOnItemLongClickListener(new WordListAdapter.onItemLongClickListener() {
            @Override
            public void onLongClick(View view,Word item) {
                //長押ししたデータをWord型で再定義してDBから削除
                //Word word = new Word(text);
                //Toast.makeText(MainActivity.this, text + "　を削除", Toast.LENGTH_SHORT).show();
                //mWordViewModel.delete(word);
                Intent intent = new Intent(getApplication(),ItemEditActivity.class);
                intent.putExtra("ITEM_ID", item.getId());
                intent.putExtra("ITEM_NAME",item.getName());
                startActivity(intent);
                //startActivityForResult(intent,ITEM_EDIT_ACTIVITY_REQUEST_CODE);
            }
        });
        tagAdapter.setOnItemLongClickListener(new TagListAdapter.onItemLongClickListener() {
            @Override
            public void onLongClick(View view, String text) {
                //長押ししたデータをTag型で再定義してからDBから削除
                Tag tag = new Tag(text);
                Toast.makeText(MainActivity.this,text + "を削除", Toast.LENGTH_SHORT).show();

            }
        });

        //endregion

        //全てのワードを取得
        mWordViewModel.getmAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@NonNull final List<Word> words) {
                wordAdapter.setmWords(words);
            }
        });
        //全てのタグを取得
        mTagViewModel.getmAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(@NonNull final List<Tag> tags) {
                tagAdapter.setmTags(tags);
            }
        });

        //右下の+ボタンの定義
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent intent = new Intent(MainActivity.this,NewWordActivity.class);
//                startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
                DialogFragment dialog = new TagAddDialogFragment();
                dialog.show(getSupportFragmentManager(), "Sample");
            }
        });
        //右下の+ボタンの定義(2)
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DialogFragment dialog = new ItemAddDialogFragment();
//                ItemAddDialogFragment dialog = new ItemAddDialogFragment();
                dialog.show(getSupportFragmentManager(), "Sample");
            }
        });
    }

    //NewWord画面からの処理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //requestCodeはstartActivityForResultの2つ目の引数の数値
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String tagFlag = data.getStringExtra("RESULT_TYPE");
            if(tagFlag.equals("TAG")) {
                Tag tag = new Tag(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
                mTagViewModel.insert(tag);
            }else{
                Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
                mWordViewModel.insert(word);
            }
        }else if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED){
            String tagFlag = data.getStringExtra("RESULT_TYPE");
            if(tagFlag.equals("TAG")){
                toastNoItem(R.string.empty_not_saved_tag);
            }else if (tagFlag.equals("WORD")){
                toastNoItem(R.string.empty_not_saved_word);
            }else{

            }
        }else{

        }
    }

    //region private method
    private void toastNoItem(int notice){
        Toast.makeText(
            getApplicationContext(),
            notice,
            Toast.LENGTH_LONG
        ).show();
    }
    //endregion
}