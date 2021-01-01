package com.example.roomwordsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tag tag);

    @Query("DELETE FROM tag_table")
    void deleteAll();

    @Query("SELECT * FROM tag_table ORDER BY tag ASC")
    LiveData<List<Tag>> getAlphabetizedTags();
}
