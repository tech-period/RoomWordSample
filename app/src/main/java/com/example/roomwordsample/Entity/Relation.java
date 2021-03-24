package com.example.roomwordsample.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"itemId","tagId"})
public class Relation {

    //コンストラクタ
    public Relation(int itemId, int tagId){
        this.itemId = itemId;
        this.tagId = tagId;
    }

    @NonNull
    private int itemId;

    @NonNull
    private int tagId;

}
