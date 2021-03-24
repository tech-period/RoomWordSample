package com.example.roomwordsample.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tag_table")
public class Tag {

    public Tag() {
    }

    public Tag(@NonNull String name) {
        Name = name;
    }

    @PrimaryKey(autoGenerate = true)
    private int Id;

    @NonNull
    private String Name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NonNull
    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }
}
