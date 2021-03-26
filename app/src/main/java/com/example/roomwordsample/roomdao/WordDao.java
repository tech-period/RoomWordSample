package com.example.roomwordsample.roomdao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomwordsample.Entity.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Update
    void update(Word word);

    @Delete
    void delete(Word word);


    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getWords();

    @Query("SELECT * FROM word_table ORDER BY `Check` ASC, name ASC")
    LiveData<List<Word>> getAlphabetizedWords();
}
