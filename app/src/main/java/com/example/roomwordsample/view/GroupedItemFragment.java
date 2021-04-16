package com.example.roomwordsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GroupedItemFragment extends Fragment {

    private WordViewModel mWordViewModel;
    private TagViewModel mTagViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grouped_item,container,false);

        WordListAdapter wordAdapter = new WordListAdapter(getContext());
        TagListAdapter tagAdapter = new TagListAdapter(getContext());

        //region RecyclerViewの設定
        //タグのRecyclerViewを設定（横スクロール）
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView2.setAdapter(tagAdapter);
        //recyclerView2.addItemDecoration(CustomItemDecoration.createDefaultDecoration(this));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(manager);

        //ワードのRecyclerViewを設定（縦スクロール）
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(wordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //endregion

        //Viewモデルを作成
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mTagViewModel = new ViewModelProvider(this).get(TagViewModel.class);


        wordAdapter.setOnItemClickListener(new WordListAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, Word word) {
                word.setCheck(!word.isCheck());
                mWordViewModel.update(word);
            }
        });

        //全てのワードを取得
        mWordViewModel.getmAllWords().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(@NonNull final List<Word> words) {
                wordAdapter.setmWords(words);
            }
        });
        //全てのタグを取得
        mTagViewModel.getmAllTags().observe(getViewLifecycleOwner(), new Observer<List<Tag>>() {
            @Override
            public void onChanged(@NonNull final List<Tag> tags) {
                tagAdapter.setmTags(tags);
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
                Intent intent = new Intent(getActivity().getApplication(),ItemEditActivity.class);
                intent.putExtra("ITEM_ID", item.getId());
                intent.putExtra("ITEM_NAME",item.getName());
                startActivity(intent);
                //startActivityForResult(intent,ITEM_EDIT_ACTIVITY_REQUEST_CODE);
            }
        });

        //endregion

        //TagAreaの+ボタンの定義
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent intent = new Intent(MainActivity.this,NewWordActivity.class);
//                startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
                DialogFragment tagDialog = new TagAddDialogFragment();
                tagDialog.show(getFragmentManager(), "Sample");
            }
        });
        //ItemAreaの+ボタンの定義
        FloatingActionButton fab2 = view.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DialogFragment dialog = new ItemAddDialogFragment();
                ItemAddDialogFragment itemDialog = new ItemAddDialogFragment();
                itemDialog.show(getFragmentManager(), "Sample");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}