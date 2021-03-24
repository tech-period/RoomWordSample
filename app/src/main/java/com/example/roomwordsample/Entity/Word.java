package com.example.roomwordsample.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    public Word() {
    }

    public Word(@NonNull String name) {
        Name = name;
        Check = false;
    }

    @PrimaryKey(autoGenerate = true)
    private int Id;

    @NonNull
    private String Name;

    private boolean Check;

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

    public boolean isCheck() {
        return Check;
    }

    public void setCheck(boolean check) {
        Check = check;
    }
}
