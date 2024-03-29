package com.example.roomwordsample.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Repository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Tag>> mAllTags;
    private LiveData<List<Tag>> mTags;
    //private List<Tag> mTags;
//    private MutableLiveData<String> searchText;
//    private LiveData<List<Tag>> items = Transformations.map(searchText,text -> {
//        if(text == null || text.length() == 0){
//
//        }else{
//            mTags.getValue();
//        }
//        return null;
//    });
    private String flag;
    private LiveData<List<Relation>> mRelations;

    public TagViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllTags = mRepository.getmAllTags();
        mTags = mRepository.getmTags(flag);
        mRelations = mRepository.getmRelations();
    }

    LiveData<List<Tag>> getmAllTags(){
        return mAllTags;
    }

    LiveData<List<Tag>> getTags(String flag){
        this.flag = flag;
        return mTags;}
    //List<Tag> getTags(String flag){return mTags;}
    public void setTags(LiveData<List<Tag>> data){mTags = data;}

    public void insert(Tag tag){
        mRepository.insert(tag);
    }

    public void delete(Tag tag){mRepository.delete(tag);}

    public void createRelation(Relation relation){mRepository.insert(relation);}
    public void removeRelation(Relation relation){mRepository.delete(relation);}

    public LiveData<List<Relation>> getRelations(){return mRelations;}

}
