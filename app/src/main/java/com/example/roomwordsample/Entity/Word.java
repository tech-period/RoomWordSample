package com.example.roomwordsample.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    //コンストラクタ
    public Word(String word){
        this.mWord = word;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;


    @NonNull
    public String getWord(){
        return this.mWord;
    }
}
