package com.example.roomwordsample.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.Repository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Tag>> mAllTags;

    public TagViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllTags = mRepository.getmAllTags();
    }

    LiveData<List<Tag>> getmAllTags(){
        return mAllTags;
    }

    public void insert(Tag tag){
        mRepository.insert(tag);
    }

    public void delete(Tag tag){mRepository.delete(tag);}
}
