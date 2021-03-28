package com.example.roomwordsample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwordsample.Entity.Relation;
import com.example.roomwordsample.Entity.Tag;
import com.example.roomwordsample.roomdao.RelationDao;
import com.example.roomwordsample.roomdao.TagDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Relation.class},version = 1,exportSchema = false)
public abstract class RelationRoomDatabase extends RoomDatabase {

    public abstract RelationDao relationDao();

    private static volatile RelationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RelationRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (RelationRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RelationRoomDatabase.class,"relation_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
