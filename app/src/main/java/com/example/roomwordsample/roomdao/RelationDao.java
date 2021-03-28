package com.example.roomwordsample.roomdao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.Entity.Tag;

import java.util.List;

@Dao
public interface RelationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Relation relation);

    @Update
    void update(Relation relation);

    @Delete
    void delete(Relation relation);

    @Query("DELETE FROM relation")
    void deleteAll();

    @Query("SELECT * FROM relation")
    LiveData<List<Relation>> getReletion();

    @Query("SELECT * FROM relation ORDER BY itemId ASC")
    LiveData<List<Relation>> getRelations();

    @Query("SELECT * FROM relation WHERE itemId = :itemId")
    List<Relation> getRelationsById(int itemId);
}
