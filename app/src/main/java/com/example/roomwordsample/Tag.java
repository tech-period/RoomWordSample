package com.example.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tag_table")
public class Tag {

    //コンストラクタ
    public Tag(String tag){
        this.mTag = tag;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tag")
    private String mTag;


    @NonNull
    public String getTag(){
        return this.mTag;
    }
}
