package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.roomdao.TagDao;
import com.example.roomwordsample.roomdao.WordDao;

import java.util.List;

public class Repository {
    private WordDao mWordDao;
    private TagDao mTagDao;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Tag>> mAllTags;

    public Repository(Application application){
        WordRoomDatabase word_db = WordRoomDatabase.getDatabase(application);
        TagRoomDatabase tag_db = TagRoomDatabase.getDatabase(application);
        mWordDao = word_db.wordDao();
        mTagDao = tag_db.tagDao();
        mAllWords = mWordDao.getAlphabetizedWords();
        mAllTags = mTagDao.getAlphabetizedTags();
    }

    public LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }
    public LiveData<List<Tag>> getmAllTags(){ return mAllTags; }

    public void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.insert(word);
        });
    }
    public void insert(Tag tag){
        TagRoomDatabase.databaseWriteExecutor.execute(() ->{
            mTagDao.insert(tag);
        });
    }

    public void  delete(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.delete(word);
        });
    }
    public void  delete(Tag tag){
        TagRoomDatabase.databaseWriteExecutor.execute(() ->{
            mTagDao.delete(tag);
        });
    }
}
