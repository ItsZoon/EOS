package com.myapp.eos_todo.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myapp.eos_todo.data.dao.TodoDao;
import com.myapp.eos_todo.data.entity.TodoItem;

@Database(version = 2, entities = {TodoItem.class})

public abstract class MyDatabase extends RoomDatabase {
    abstract public TodoDao todoDao();

    private static MyDatabase myDatabase;

    public static MyDatabase getInstance(Context context) {
        if (myDatabase == null) {
            myDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "myDatabase.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return myDatabase;
    }
}


