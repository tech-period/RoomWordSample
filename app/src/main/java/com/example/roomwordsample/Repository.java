package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private WordDao mWordDao;
    private TagDao mTagDao;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Tag>> mAllTags;

    Repository(Application application){
        WordRoomDatabase word_db = WordRoomDatabase.getDatabase(application);
        TagRoomDatabase tag_db = TagRoomDatabase.getDatabase(application);
        mWordDao = word_db.wordDao();
        mTagDao = tag_db.tagDao();
        mAllWords = mWordDao.getAlphabetizedWords();
        mAllTags = mTagDao.getAlphabetizedTags();
    }

    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }
    LiveData<List<Tag>> getmAllTags(){ return mAllTags; }

    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.insert(word);
        });
    }
    void insert(Tag tag){
        TagRoomDatabase.databaseWriteExecutor.execute(() ->{
            mTagDao.insert(tag);
        });
    }

    void  delete(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.delete(word);
        });
    }
}
