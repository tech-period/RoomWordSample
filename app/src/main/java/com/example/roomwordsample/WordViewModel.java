package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getmAllWords();
    }

    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        mRepository.insert(word);
    }

    public  void delete(Word word){mRepository.delete(word);}
}
