package com.myapp.eos_todo.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myapp.eos_todo.data.entity.TodoItem;

@Database(version = 1, entities = {TodoItem.class})
public class MyDatabase {
    public abstract class MyDatabase extends RoomDatabase{
        abstract public TodoDao todoDao();

        private static Mydatabase myDatabase;

        public static Mydatabase getInstance(Context context){
            if(myDatabase = null){
                myDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        Mydatabase.class, "myDatabase.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return myDatabase;
        }
    }

}
