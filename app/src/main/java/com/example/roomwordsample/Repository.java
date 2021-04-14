package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.roomdao.RelationDao;
import com.example.roomwordsample.roomdao.TagDao;
import com.example.roomwordsample.roomdao.WordDao;

import java.util.List;

public class Repository {
    private WordDao mWordDao;
    private TagDao mTagDao;
    private RelationDao mRelationDao;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<Tag>> mAllTags;
    private LiveData<List<Tag>> mTags;
    //private List<Tag> mTags;
    private LiveData<List<Relation>> mRelations;

    private String flag;
    private int itemId;

    public Repository(Application application){
        WordRoomDatabase word_db = WordRoomDatabase.getDatabase(application);
        TagRoomDatabase tag_db = TagRoomDatabase.getDatabase(application);
        RelationRoomDatabase relation_db = RelationRoomDatabase.getDatabase(application);
        mWordDao = word_db.wordDao();
        mTagDao = tag_db.tagDao();
        mRelationDao = relation_db.relationDao();
        mAllWords = mWordDao.getAlphabetizedWords();
        mAllTags = mTagDao.getAlphabetizedTags();
        mTags = mTagDao.getFilteredTags(flag);
        mRelations = mRelationDao.getRelations();
    }

    public LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }
    public LiveData<List<Tag>> getmAllTags(){ return mAllTags; }
    public LiveData<List<Tag>> getmTags(String flag){ return mTags; }
    //public List<Tag> getmTags(String flag){ return mTags; }
    public LiveData<List<Relation>> getmRelations(){ return mRelations; }

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
    public void insert(Relation relation){
        RelationRoomDatabase.databaseWriteExecutor.execute(() ->{
            mRelationDao.insert(relation);
        });
    }

    public void update(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.update(word);
        });
    }
    public void update(Tag tag){
        TagRoomDatabase.databaseWriteExecutor.execute(() ->{
            mTagDao.update(tag);
        });
    }
    public void update(Relation relation){
        RelationRoomDatabase.databaseWriteExecutor.execute(() ->{
            mRelationDao.update(relation);
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
    public void delete(Relation relation){
        RelationRoomDatabase.databaseWriteExecutor.execute(() ->{
            mRelationDao.delete(relation);
        });
    }

    public int getNumRows() {
        return mRelationDao.getCount();
    }
}
