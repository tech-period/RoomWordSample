package com.example.roomwordsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class ItemEditActivity extends AppCompatActivity implements TextWatcher {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;
    private EditText mSearchTagView;
    private TagViewModel mTagViewModel;

    private TagListAdapter2 tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        //Test App ID
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView adView = findViewById(R.id.ie_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Viewモデルを作成
        mEditWordView = findViewById(R.id.edit_word2);
        mSearchTagView = findViewById(R.id.search_tag);
        mSearchTagView.addTextChangedListener(this);
//        mSearchTagView.addTextChangedListener(new TextWatcher() {
//        });
        mTagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        Intent intent = getIntent();
        int itemId = intent.getIntExtra("ITEM_ID",0);
        String itemName = intent.getStringExtra("ITEM_NAME");
        mEditWordView.setText(itemName);

        //LiveData<List<Relation>> itemIds = mTagViewModel.getRelations();
        //全てのRelationを取得
        mTagViewModel.getRelations().observe(this, new Observer<List<Relation>>() {
            @Override
            public void onChanged(@NonNull final List<Relation> relations) {
                tagAdapter.setmRelations(relations);
            }
        });

        if(itemId == 0){
            tagAdapter = new TagListAdapter2(this);
        }else {
            tagAdapter = new TagListAdapter2(this, itemId);
        }

        //RecyclerViewを設定（縦スクロール）
        RecyclerView recyclerView = findViewById(R.id.recyclerview3);
        recyclerView.setAdapter(tagAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //全てのタグを取得
        mTagViewModel.getmAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(@NonNull final List<Tag> tags) {
                tagAdapter.setmTags(tags);
            }
        });

        tagAdapter.setOnItemClickListener(new TagListAdapter2.onItemClickListener() {
            @Override
            public void onClick(View view, int id) {
                Relation relation = new Relation(itemId, id);
                mTagViewModel.createRelation(relation);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
        String flag = s.toString();
        List<Tag> data;
        if(flag.equals("")){
            data = mTagViewModel.getmAllTags().getValue();
        }else {
            mTagViewModel.setTags(mTagViewModel.getTags(flag));
        }
    }

    @Override
    public void onBackPressed(){
        Intent replyIntent = new Intent();
        replyIntent.putExtra("RESULT_TYPE","BACK_PRESSED");
        setResult(RESULT_CANCELED,replyIntent);
        finish();
    }
}