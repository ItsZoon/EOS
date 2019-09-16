package com.myapp.eos_todo.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Todo")
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "due")
    private String due;

    @ColumnInfo(name = "checked")
    private Boolean checked;

    public TodoItem(String title){
        this.title = title;
        due = null;
        checked = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
