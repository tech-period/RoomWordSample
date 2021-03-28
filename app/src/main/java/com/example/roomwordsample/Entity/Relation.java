package com.example.roomwordsample.Entity;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    public Relation getRelation(int itemId){
        return this;
    }

//    @Override
//    public boolean equals(Object obj){
//        boolean result = false;
//        if (this == obj)
//            result = true;
//        if (!(obj instanceof Relation))
//            result = false;
//        return result;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(itemId, tagId);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;
        Relation relation = (Relation) o;
        return itemId == relation.itemId &&
                tagId == relation.tagId;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(itemId, tagId);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
