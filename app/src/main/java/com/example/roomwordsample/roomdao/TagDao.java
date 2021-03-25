package com.example.roomwordsample.roomdao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomwordsample.Entity.Tag;

import java.util.List;

@Dao
public interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tag tag);

    @Update
    void update(Tag tag);

    @Delete
    void delete(Tag tag);

    @Query("DELETE FROM tag_table")
    void deleteAll();

    @Query("SELECT * FROM tag_table")
    LiveData<List<Tag>> getTags();

    @Query("SELECT * FROM tag_table ORDER BY name ASC")
    LiveData<List<Tag>> getAlphabetizedTags();
}
