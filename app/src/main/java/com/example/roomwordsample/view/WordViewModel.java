package com.example.roomwordsample.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordsample.Entity.Word;
import com.example.roomwordsample.Repository;

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

    public void update(Word word){ mRepository.update(word);}

    public void delete(Word word){mRepository.delete(word);}
}
