package com.example.roomwordsample.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.roomwordsample.Repository;

public class RelationViewModel extends AndroidViewModel {

    private Repository mRepository;

    public RelationViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
    }

    public int getNumRows(){
        return mRepository.getNumRows();
    }
}
